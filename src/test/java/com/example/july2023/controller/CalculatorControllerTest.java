package com.example.july2023.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CalculatorControllerTest {

    @Autowired
    CalculatorController controller;

    @Test
    void divide() {
        double firstNumber = 20;
        double secondNumber = 30;
        ResponseEntity<Object> response = controller.divide(firstNumber, secondNumber);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals((firstNumber/secondNumber), response.getBody());
    }

    @Test
    void divide_400BadRequest() {
        double firstNumber = 20;
        double secondNumber = 0;
        ResponseEntity<Object> response = controller.divide(firstNumber, secondNumber);
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void add() {
    }
}