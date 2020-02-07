package com.jgainey.throttlenozzle.demo.controllers;


import com.jgainey.throttlenozzle.demo.objects.Nozzle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("set-throttle")
    public String setThrottleSetting(int throttleSetting){
        Nozzle.getInstance().setThrottleSetting(throttleSetting);
        return "";
    }

    @ResponseBody
    @GetMapping("consuming-rate")
    public String getConsumingRateString() {
        String consumingRateString = Nozzle.getInstance().getConsumingRateString();
        return consumingRateString;
    }
}
