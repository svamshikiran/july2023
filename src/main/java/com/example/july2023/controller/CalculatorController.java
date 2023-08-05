package com.example.july2023.controller;

import com.example.july2023.service.CalculateRestService;
import com.example.july2023.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    private static Logger log = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired //this annotation will request the spring container to give me an object
    CalculatorService calculatorService;
    //Dependency Injection

    @Autowired
    CalculateRestService calculateRestService;

    @GetMapping("/divide/{firstNumber}/{secondNumber}")
    public ResponseEntity<Object> divide(@PathVariable("firstNumber") double firstNumber,
                                 @PathVariable("secondNumber") double secondNumber){
        log.info("INSIDE THE CALCULATE CONTROLLER");
        log.info("FIRST NUMBER: "+firstNumber+" and SECOND NUMBER: "+secondNumber);
        if(secondNumber != 0)
            return new ResponseEntity<>(calculatorService.divide(firstNumber, secondNumber), HttpStatus.OK);
        else {
            log.error("DIVIDE BY 0 IS NOT POSSIBLE");
            return new ResponseEntity<>("DIVIDE BY 0 IS NOT POSSIBLE", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/add/{first}/{second}")
    public double add(@PathVariable("first") double first,
                      @PathVariable("second") double second){
        return calculateRestService.calculateAddtion(first, second);
    }

}

//IOC - inversion of control
//Dependency Injection - DI

//@RestController
//@Service
//@Repository
//@Component
//@Bean
