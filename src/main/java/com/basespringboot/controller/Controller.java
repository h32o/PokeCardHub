package com.basespringboot.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
        @PostMapping("/")
        public String index(){
            System.out.println("Index");
            return "index.html";

        }



}
