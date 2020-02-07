package com.jgainey.throttlenozzle.demo.objects;

import com.jgainey.throttlenozzle.demo.configs.CFProperties;
import com.jgainey.throttlenozzle.demo.configs.Configuration;
import com.jgainey.throttlenozzle.demo.utils.Utils;
import org.cloudfoundry.doppler.Envelope;
import org.cloudfoundry.doppler.FirehoseRequest;
import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Flux;

public class Nozzle {

    //private singleton instance of nozzle to ensure only one per application.
    private static Nozzle nozzle;

    //variables to track nozzle statistics/settings
    private int consumingRate = 0;
    private int throttleSetting = 0;

    private Nozzle(){
        Utils.logInfo("Initializing Nozzle");
        //cf auth and access
        CFProperties props = new CFProperties();
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        DefaultCloudFoundryOperations ops = (DefaultCloudFoundryOperations) context.getAutowireCapableBeanFactory().getBean("CFOperations");
        Utils.logInfo("Successfully obtained CF credentials and access...requesting firehose stream.");
        Flux<Envelope> stream = ops.getDopplerClient().firehose(
                FirehoseRequest
                        .builder()
                        .subscriptionId(props.getSubscriptionID()).build());
        Utils.logInfo("Stream obtained, starting up consumer.");
        stream.subscribe(envelope -> {
            consumingRate++;
            if(getThrottleSettingAbs() > 0){
                try {
                    Thread.sleep(getThrottleSettingAbs());
                } catch (InterruptedException e) {
                    Utils.logError("Something went wrong sleeping in the nozzle");
                    e.printStackTrace();
                }
            }
        });
        Utils.logInfo("Nozzle Initialization is complete and now consuming.");
    }

    public synchronized static Nozzle getInstance(){
        if(nozzle == null){
            nozzle =  new Nozzle();
        }
        return nozzle;
    }


    private int getConsumingRate() {
        Utils.logInfo("Current consuming Rate: " + consumingRate);
        int currentConsumingRate = consumingRate;
        consumingRate = 0;
        return currentConsumingRate;
    }

    public String getConsumingRateString() {
        return "Consuming Rate:<br>" + getConsumingRate() +"/second.";
    }

    public int getThrottleSettingAbs() {
        return Math.abs(getThrottleSetting());
    }

    private int getThrottleSetting() {
        return throttleSetting;
    }

    public void setThrottleSetting(int throttleSetting) {
        this.throttleSetting = throttleSetting;
    }
}