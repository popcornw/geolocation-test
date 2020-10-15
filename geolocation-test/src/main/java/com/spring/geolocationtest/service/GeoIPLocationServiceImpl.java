	package com.spring.geolocationtest.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Optional;

import ua_parser.Client;
import ua_parser.Parser;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.spring.geolocationtest.model.CityType;
import com.spring.geolocationtest.model.GeoIP;
import com.spring.geolocationtest.model.Visits;
import com.spring.geolocationtest.repository.VisitRepository;

import lombok.AllArgsConstructor;

import static java.util.Objects.nonNull;
@Service
@AllArgsConstructor
public class GeoIPLocationServiceImpl implements GeoIPLocationService {

private final VisitRepository visitRepository ; 
    private final DatabaseReader databaseReader;
    private static final String UNKNOWN = "UNKNOWN";

  

    /**
     * Get device info by user agent
     *
     * @param userAgent user agent http device
     * @return Device info details
     * @throws IOException if not found
     */
    private String getDeviceDetails(String userAgent) throws IOException {
        String deviceDetails = UNKNOWN;

        Parser parser = new Parser();

        Client client = parser.parse(userAgent);
        if (nonNull(client)) {
            deviceDetails = client.userAgent.family + " " + client.userAgent.major + "." + client.userAgent.minor +
                    " - " + client.os.family + " " + client.os.major + "." + client.os.minor;
        }

        return deviceDetails;
    }

    /**
     * get user position by ip address
     *
     * @param ip String ip address
     * @return UserPositionDTO model
     * @throws IOException     if local database city not exist
     * @throws GeoIp2Exception if cannot get info by ip address
     */
    @Override
    public GeoIP getIpLocation( String ip,HttpServletRequest request) throws IOException, GeoIp2Exception {

        GeoIP position = new GeoIP();
        String location;
        InetAddress ipAddress = InetAddress.getByName(ip);
//       InetAddress ip =InetAddress.getByName(request.getRemoteAddr());
        CityResponse cityResponse = databaseReader.city(ipAddress);
        if (nonNull(cityResponse) && nonNull(cityResponse.getCity())) {

            String continent = (cityResponse.getContinent() != null) ? cityResponse.getContinent().getName() : "";
            String country = (cityResponse.getCountry() != null) ? cityResponse.getCountry().getName() : "";

            location = String.format("%s, %s, %s", continent, country, cityResponse.getCity().getName());
            position.setCity(cityResponse.getCity().getName());
            position.setFullLocation(location);
            position.setLatitude((cityResponse.getLocation() != null) ? cityResponse.getLocation().getLatitude() : 0);
            position.setLongitude((cityResponse.getLocation() != null) ? cityResponse.getLocation().getLongitude() : 0);          
            position.setIpAddress(ip);
            visitCount(position.getCity());
           
        }
        return position;
    }
    @Transactional
	public void visitCount(String  city) {
		Visits visits = visitRepository.findByCity(city)
				.orElseThrow(() -> new RuntimeException ("city not found "));
		
			visits.setVisitCount(visits.getVisitCount() + 1);
		
		visitRepository.save(visits);
		

	}

	@Override
	public Visits getCityCount(String city) {
		// TODO Auto-generated method stub
		Visits visits = visitRepository.findByCity(city)
				.orElseThrow(() -> new RuntimeException ("city not found "));
		return visits ;
	}
    
}
