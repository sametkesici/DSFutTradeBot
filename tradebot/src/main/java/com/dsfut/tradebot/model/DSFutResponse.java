package com.dsfut.tradebot.model;

import lombok.Data;

@Data
public class DSFutResponse {

  private String error;

  private String message;

  private Player player;

}
