package com.tutorial.ecommercebackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoggingTestController {
    Logger log = LoggerFactory.getLogger(LoggingTestController.class);
    @GetMapping("/log")
    public ResponseEntity<HttpStatus> home(){
        log.error("ERRORRRRRRRRORRORROROROR");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
