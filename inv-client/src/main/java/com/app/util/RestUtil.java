package com.app.util;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class RestUtil {

	@Autowired
	private static DiscoveryClient discoveryClient;

	public final static String INT_INV_APP = "inv-app";
	public static String INT_INV_APP_BASE_URL = "";
	
	public final static String INT_INV_APP_HOME = "inv_home";
	
	
	@PostConstruct
	public void init(){
		initInvApp();
	}

	public static void initInvApp() {
		List<ServiceInstance> instances = discoveryClient.getInstances(INT_INV_APP);
		ServiceInstance serviceInstance = instances.get(0);
		INT_INV_APP_BASE_URL = serviceInstance.getUri().toString() + "/";
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	public static ResponseEntity<Object> restGetRequest(String uri) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = null;
		try {
			response = restTemplate.exchange(uri, HttpMethod.GET, getHeaders(), Object.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response;
	}
	
	public static ResponseEntity<Object> getRequest(String endPoint) {
		String url = INT_INV_APP_BASE_URL + endPoint;
		return restGetRequest(url);
	}
}
