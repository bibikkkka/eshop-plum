package org.tsp.eshopplum.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tsp.eshopplum.entities.Product;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/error")
public class ErrorController {

    @GetMapping()
    public String error() {
        return "This is an error - you win!";
    }


}

