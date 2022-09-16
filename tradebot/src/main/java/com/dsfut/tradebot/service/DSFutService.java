package com.dsfut.tradebot.service;

import com.dsfut.tradebot.client.DSFutClient;
import com.dsfut.tradebot.model.DSFutConstant;

import com.dsfut.tradebot.model.DSFutResponse;
import com.dsfut.tradebot.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class DSFutService {

  private final DSFutClient dsFutClient;

  private final Producer producer;

  public Integer getUnixTime(){
    return (int) Instant.now().getEpochSecond();
  }

  public String calculateMD5(){

    String timestamp = getUnixTime().toString();
    String signature = DSFutConstant.PARTNER_ID+DSFutConstant.SECRET_KEY+timestamp;
    return DigestUtils.md5Hex(signature);
  }

  @Scheduled(fixedDelay = 880)
  public void selectPlayer() throws JsonProcessingException {
    DSFutResponse dsFutResponse = dsFutClient.getResponse(DSFutConstant.FIFA, DSFutConstant.CONSOLE, DSFutConstant.PARTNER_ID, getUnixTime(),
                                                          calculateMD5());
    log.info(dsFutResponse.getMessage() + " " + dsFutResponse.getError());

    if(dsFutResponse.getPlayer() != null){
      producer.sendMessage(dsFutResponse.getPlayer());
      log.warn(dsFutResponse.getPlayer().getName() + " " + dsFutResponse.getPlayer().getBuyNowPrice() + " " +  dsFutResponse.getPlayer().getStartPrice() + " " + dsFutResponse.getPlayer().getExpires());
    }
  }
}
