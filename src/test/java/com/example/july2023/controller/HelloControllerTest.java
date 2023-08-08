package com.example.july2023.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    HelloController controller;

    @Test
    void sayHello() {
        String expectedString = "WELCOME TO SPRINGBOOT FRAMEWORK";
        String actualString = controller.sayHello();
        assertNotNull(actualString);
        assertEquals(expectedString, actualString);
    }

    @Test
    void testSayHello() {

        String inputName = "TEST";
        String actualString = controller.sayHello(inputName);
        assertNotNull(actualString);
        assertTrue(actualString.contains(inputName));
    }

    @Test
    void testSayHello1() {
    }

    @Test
    void sayHelloWithParameter() {
    }

    @Test
    void postMessage() {
    }
}