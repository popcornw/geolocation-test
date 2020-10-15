package com.spring.geolocationtest.DataInit;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.geolocationtest.model.CityType;
import com.spring.geolocationtest.model.Visits;
import com.spring.geolocationtest.repository.VisitRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Component
@AllArgsConstructor
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final VisitRepository visitRepository;
   
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	savevisitscities();
    }

    private void savevisitscities() {

//       Visits visit1 = new Visits() ; 
//       visit1.setCity("Casablanca");
//       Visits visit2 = new Visits() ; 
//       visit2.setCity("Rabat");
//       
//       Visits visit3 = new Visits() ; 
//       visit3.setCity("Fes");
//       visitRepository.save(visit1);
//       visitRepository.save(visit2);
//       visitRepository.save(visit3);

       
    }
}
