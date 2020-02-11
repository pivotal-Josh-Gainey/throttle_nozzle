package com.jgainey.throttlenozzle.demo.controllers;


import com.jgainey.throttlenozzle.demo.objects.Nozzle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("slider-set-throttle")
    public String setThrottleSetting(int throttleSetting){
        if(throttleSetting <= 0 && throttleSetting >= -500){
            Nozzle.getInstance().setThrottleSetting(throttleSetting);
        }
        return "";
    }

    @ResponseBody
    @GetMapping("get-throttle")
    public int getThrottleSetting(){
        int throttleSetting = Nozzle.getInstance().getThrottleSetting();
        return throttleSetting;
    }

    @ResponseBody
    @GetMapping("total-consumed")
    public double getTotalConsumed(){
        double throttleSetting = Nozzle.getInstance().getTotalConsumed();
        return throttleSetting;
    }

    @ResponseBody
    @GetMapping("consuming-rate")
    public String getConsumingRateString() {
        String consumingRateString = Nozzle.getInstance().getConsumingRateString();
        return consumingRateString;
    }

    @ResponseBody
    @GetMapping("set-throttle")
    public ResponseEntity<String> setThrottleSettingCurl(@RequestParam(value = "throttle-setting", defaultValue = "0") int throttleSetting){
        if(throttleSetting <= 0 && throttleSetting >= -500){
            Nozzle.getInstance().setThrottleSetting(throttleSetting);
            return new ResponseEntity<>("Accepted, setting throttle-setting to "+ throttleSetting, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("NO CHANGES - throttle-setting must be between -500(slowest) and 0 (fastest)", HttpStatus.OK);
        }

    }
}
