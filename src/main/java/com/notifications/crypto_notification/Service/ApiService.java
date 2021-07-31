package com.notifications.crypto_notification.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.notifications.crypto_notification.entity.PriceModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ApiService {
    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    public TimetableService timetableService;
    final static List<String> Assets = Arrays.asList("BTC","BAT","ETH","LTC","ADA","LINK","XLM","EOS","XTZ");
    private PriceModel[] prices = new PriceModel[Assets.size()];

    public void updateTimetable(String timeOption) throws FirebaseMessagingException {
        int index = 1;
        for (int i = 0; i < Assets.size(); i++) {
            prices[i] = restTemplate.getForObject("https://api.pro.coinbase.com/products/" + Assets.get(i) + "-USD/stats", PriceModel.class);
            PriceModel priceModel = prices[i];
            switch(timeOption){
                case "current": timetableService.putCurrentPrice(priceModel, index);
                break;
                case "fifteen":timetableService.putFifteen(priceModel,index);
                break;
                case "hour": timetableService.putHour(priceModel,index);
                break;
            }
            index = index + 1;
        }
    }

    @Scheduled(fixedRate = 60000)
    public void callUpdateTimetableForCurrent() throws FirebaseMessagingException {
        updateTimetable("current");
    }
    @Scheduled(fixedRate = 900000)
    public void callUpdateTimetableForFifteen() throws FirebaseMessagingException {
        updateTimetable("fifteen");
    }
    @Scheduled(fixedRate = 3600000)
    public void callUpdateTimetableForHour() throws FirebaseMessagingException {
        updateTimetable("hour");
    }
}
