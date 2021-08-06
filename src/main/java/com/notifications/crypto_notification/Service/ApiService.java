package com.notifications.crypto_notification.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.notifications.crypto_notification.entity.CoinbaseApiPriceModel;
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
    private CoinbaseApiPriceModel[] prices = new CoinbaseApiPriceModel[Assets.size()]; //json model for asset

    public void updateTimetable(String timeOption) throws FirebaseMessagingException {
        int index = 1;
        for (int i = 0; i < Assets.size(); i++) {
            prices[i] = restTemplate.getForObject("https://api.pro.coinbase.com/products/" + Assets.get(i) + "-USD/stats", CoinbaseApiPriceModel.class);
            CoinbaseApiPriceModel priceModel = prices[i];
            switch(timeOption){
                case "current": timetableService.putCurrentPrice(priceModel, index); //send current price to current attribute
                break;
                case "fifteen":timetableService.putFifteen(priceModel,index); //send current price to 15 minute attribute
                break;
                case "hour": timetableService.putHour(priceModel,index); //send current price to 1 hour attribute
                break;
            }
            index++;
        }
    }

    @Scheduled(fixedRate = 60000) //Executes every 60 seconds
    public void callUpdateTimetableForCurrent() throws FirebaseMessagingException {
        updateTimetable("current");
    }
    @Scheduled(fixedRate = 900000)//Executes every 15 minutes
    public void callUpdateTimetableForFifteen() throws FirebaseMessagingException {
        updateTimetable("fifteen");
    }
    @Scheduled(fixedRate = 3600000)//Executes every hour
    public void callUpdateTimetableForHour() throws FirebaseMessagingException {
        updateTimetable("hour");
    }
}
