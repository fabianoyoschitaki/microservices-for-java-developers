package com.microservices.holaspringboot.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HolaRestController {
	
	@RequestMapping(method=RequestMethod.GET, value="/hola", produces="text/plain")
	public String hola() {
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			hostname = "unknown";
		}
		return "Hola Spring Boot de " + hostname;
	}
}
