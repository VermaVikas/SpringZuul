package com.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("zuul-server")
@RequestMapping(value="/api/inv-app")
public interface InvApp {
	
	@GetMapping(value = "/inv_home")
    public String home();
	
}
