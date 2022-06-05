package com.dsfut.tradebot.client;

import com.dsfut.tradebot.model.DSFutConstant;
import com.dsfut.tradebot.model.DSFutResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DSFutClient" , url = DSFutConstant.URL)
public interface DSFutClient {

  @GetMapping(value = "/api/{fifa}/{console}/{partner_id}/{timestamp}/{signature}" , consumes = MediaType.APPLICATION_JSON_VALUE)
  DSFutResponse getResponse(@PathVariable(value = "fifa") String fifa ,
                            @PathVariable(value = "console") String console ,
                            @PathVariable(value = "partner_id") String partner_id ,
                            @PathVariable(value = "timestamp") Integer timestamp ,
                            @PathVariable(value = "signature") String signature );

}
