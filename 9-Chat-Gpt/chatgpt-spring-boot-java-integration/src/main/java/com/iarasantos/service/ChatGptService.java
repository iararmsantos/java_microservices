package com.iarasantos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iarasantos.vo.request.ChatGptRequest;
import com.iarasantos.vo.response.ChatGptResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptService {
    private Logger logger = LoggerFactory.getLogger(ChatGptService.class.getName());

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public Object chat(String prompt) throws JsonProcessingException {
        logger.info("Starting prompt...");

        ChatGptRequest request = new ChatGptRequest(model, prompt);

        String json = new ObjectMapper().writeValueAsString(request);

        logger.info(json);
        logger.info("Processing prompt...");
        ChatGptResponse response = restTemplate.postForObject(url, request, ChatGptResponse.class);

        return response;
    }
}
