package com.spring.geolocationtest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.spring.geolocationtest.model.GeoIP;
import com.spring.geolocationtest.service.GeoIPLocationService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GeoIPController {
	private final GeoIPLocationService geoIPLocationService;
	
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public void processData( HttpServletRequest request) {

	       
	   
	     
	    }
	
	

	@GetMapping("/{ipAddress}")
	@ResponseStatus(HttpStatus.OK)
	public String getLocation(@PathVariable String ipAddress, HttpServletRequest request)
			throws IOException, GeoIp2Exception {

		GeoIP geoIp = geoIPLocationService.getIpLocation(ipAddress, request);
		
		// when the project gets deployed 
		 //    System.out.println(request.getRemoteAddr());
		if (geoIp.getCity().matches("Casablanca") || geoIp.getCity() == "Rabat" || geoIp.getCity() == "Fes")

		{
			return geoIp.getCity();
		} else

			return "index";

	}

	@RequestMapping({ "/Rabat.html" })
	public String rabatCity(Model model) {
		geoIPLocationService.visitCount("Rabat");
		model.addAttribute("visit", geoIPLocationService.getCityCount("Rabat"));

		return ("Rabat");

	}

	@RequestMapping({ "/Casablanca.html","Casablanca" })
	public String casaCity(Model model) {
		geoIPLocationService.visitCount("Casablanca");
		model.addAttribute("visit", geoIPLocationService.getCityCount("Casablanca"));
		return ("Casablanca");

	}

	@RequestMapping({ "/Fes.html" })
	public String fesCity(Model model) {
		geoIPLocationService.visitCount("Fes");
		model.addAttribute("visit", geoIPLocationService.getCityCount("Fes"));

		return ("Fes");

	}

}
