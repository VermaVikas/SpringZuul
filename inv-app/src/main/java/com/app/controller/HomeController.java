package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	
	@RequestMapping(value = "/inv_home", method = RequestMethod.GET)
    public String home() {
        System.out.println("Home called");
        
        Map<String, String> temp = new HashMap<String, String>();
        for(int i = 0; i<15000; i++) {
        	System.out.println("i: "+i);
        	temp.put("key"+i, "value"+i);
        }
        return "Hi from inv-app...";
    }	
	
}
