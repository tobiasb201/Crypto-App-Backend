package com.notifications.crypto_notification.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceModel {

    private double open;
    private double high;
    private double low;
    private double volume;
    private double last;
    private double volume_30day;

}