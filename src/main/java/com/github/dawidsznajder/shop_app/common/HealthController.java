package com.github.dawidsznajder.shop_app.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("health")
    public String checkHealth(){
        return "OKK4444";
    }
}
