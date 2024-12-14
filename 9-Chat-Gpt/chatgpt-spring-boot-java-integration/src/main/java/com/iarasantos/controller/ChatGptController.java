package com.iarasantos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iarasantos.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class ChatGptController {
    @Autowired
    private ChatGptService service;

    @GetMapping("/chat")
    public Object chat(@RequestParam("prompt")String prompt) throws JsonProcessingException {
        return service.chat(prompt);
    }
}
