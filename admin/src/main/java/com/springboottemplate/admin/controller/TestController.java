package com.springboottemplate.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="testAPI")
@RestController
public class TestController {


    @GetMapping("/")
    public String getConfig() {
        return "OK";
    }
}
