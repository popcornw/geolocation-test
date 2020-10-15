package com.spring.geolocationtest.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.spring.geolocationtest.model.GeoIP;
import com.spring.geolocationtest.model.Visits;

public interface GeoIPLocationService {
    GeoIP getIpLocation(String ip , HttpServletRequest request) throws IOException, GeoIp2Exception;
    void visitCount(String city);
    Visits getCityCount(String city) ;
}
