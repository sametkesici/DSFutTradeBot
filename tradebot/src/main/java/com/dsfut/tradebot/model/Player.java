package com.dsfut.tradebot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

  private Long tradeID;

  private int startPrice;

  private int buyNowPrice;

  private int assetID;

  private int resourceID;

  private String name;

  private int rating;

  private String position;

  private int expires;

  private int transactionID;

}
