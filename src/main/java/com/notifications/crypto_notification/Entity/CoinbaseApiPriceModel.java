package com.notifications.crypto_notification.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinbaseApiPriceModel {

    private double open; //not relevant
    private double high; //not relevant
    private double low; //not relevant
    private double volume; //not relevant
    private double last; //current Price
    private double volume_30day; //not relevant

}