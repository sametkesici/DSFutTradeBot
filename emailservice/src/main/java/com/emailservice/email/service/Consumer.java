package com.emailservice.email.service;

import com.emailservice.email.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Consumer {

  private static final String orderTopic = "${topic.name}";

  private final ObjectMapper objectMapper;
  private final PlayerEmailSenderService emailService;


  @KafkaListener(topics = orderTopic)
  public void consumeMessage(String message) throws JsonProcessingException {
    log.info("message consumed {}", message);
    Player player = objectMapper.readValue(message, Player.class);
    emailService.sendEmail(player);
  }
}
