package com.emailservice.email.service;

import com.emailservice.email.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlayerEmailSenderService {

  private final JavaMailSender javaMailSender;

  public void sendEmail(Player player){
    log.info(player.getName());
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("xxxxx");
    message.setTo("xxxxxx");
    message.setSubject("DSFUT-PLAYER");
    message.setText(player.toString());
    javaMailSender.send(message);
  }

}
