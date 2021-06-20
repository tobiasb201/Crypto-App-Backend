package com.notifications.crypto_notification.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiModel {

    private double open;
    private double high;
    private double low;
    private double volume;
    private double last;
    private double volume_30day;

    public ApiModel(){
    }
}
