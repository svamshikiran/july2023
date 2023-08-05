package com.example.july2023.controller;

import com.example.july2023.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
// Inversion Of Control - IOC
@RestController  //SpringContainer create an object/bean and keep it ready
@RequestMapping("/hello")
public class HelloController {

    private static Logger log = LoggerFactory.getLogger(HelloController.class);


    @GetMapping("/greet")
    public String sayHello() {
        return "WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    @GetMapping("/greet/{firstName}")
    public String sayHello(@PathVariable("firstName") String name) {
        return "HI "+name+", WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    //URL - http://localhost:9004/dbbatch5/hello/greet/VAMSHI/and/S
    @GetMapping("/greet/{firstName}/and/{lastName}")
    public String sayHello(@PathVariable("firstName") String firstName,
                           @PathVariable("lastName") String lastName) {
        return "HI "+firstName+" "+lastName+", WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    //URL - http://localhost:9004/dbbatch5/hello/greetWithParameter?name=VAMSHI
    @GetMapping("/greetWithParameter")
    public String sayHelloWithParameter(@RequestParam("name") String input) {
        return "HI "+input+", WELCOME TO SPRINGBOOT FRAMEWORK, WITH REQUEST PARAMETER";
    }

	@PostMapping("/post")
	public void postMessage(@RequestBody Message message) {
		log.info("Message Id: "+message.getMsgid());
		log.info("Message: "+message.getMsg());
	}
}
