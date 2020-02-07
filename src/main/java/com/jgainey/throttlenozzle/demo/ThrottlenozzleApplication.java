package com.jgainey.throttlenozzle.demo;

import com.jgainey.throttlenozzle.demo.objects.Nozzle;
import com.jgainey.throttlenozzle.demo.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThrottlenozzleApplication {

    public static void main(String[] args) {
        Utils.initLogger();
        Nozzle.getInstance();
        SpringApplication.run(ThrottlenozzleApplication.class, args);
    }

}
