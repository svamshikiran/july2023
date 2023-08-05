package com.example.july2023.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service //belongs to IOC
public class CalculatorService {

    private static Logger log = LoggerFactory.getLogger(CalculatorService.class);

    public double divide(double firstNumber, double secondNumber){
        log.info("INSIDE THE CALCULATOR SERVICE");
        return firstNumber / secondNumber;
    }


}
