package com.notifications.crypto_notification.Service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.notifications.crypto_notification.Entity.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ApiService {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public TimetableService timetableService;

    private List<String> assets = Arrays.asList("BTC","BAT","ETH","LTC","ADA","LINK","XLM","EOS","XTZ");

    public int index=0;

    ApiModel[] prices = new ApiModel[assets.size()];

    //Scheduled for every minute to execute
    @Scheduled(fixedRate = 60000)
    public List<ApiModel> updatetimetable() throws FirebaseMessagingException {
        for(int i=0;i<assets.size();i++){
            index=index+1;
            prices[i]=restTemplate.getForObject("https://api.pro.coinbase.com/products/"+ assets.get(i) +"-USD/stats",ApiModel.class);
            ApiModel model= prices[i];
            timetableService.putcurrentprice(model,index);
            log.info(String.valueOf(index));
        }
        index=0;
        return Arrays.asList(prices.clone());
    }

    //Scheduled for every 15 minutes to execute
    @Scheduled(fixedRate = 900000)
    public List<ApiModel> updatetimetablefifteen() throws ExecutionException, InterruptedException {
        for(int i=0;i<assets.size();i++){
            index=index+1;
            prices[i]=restTemplate.getForObject("https://api.pro.coinbase.com/products/"+ assets.get(i) +"-USD/stats",ApiModel.class);
            ApiModel model= prices[i];
            timetableService.putfifteen(model,index);
            log.info(String.valueOf(index));
        }
        index=0;
        return Arrays.asList(prices.clone());
    }

    //Scheduled for every hour to execute
    @Scheduled(fixedRate = 3600000)
    public List<ApiModel> updatetimetablehour() throws ExecutionException, InterruptedException {
        for(int i=0;i<assets.size();i++){
            index=index+1;
            prices[i]=restTemplate.getForObject("https://api.pro.coinbase.com/products/"+ assets.get(i) +"-USD/stats",ApiModel.class);
            ApiModel model= prices[i];
            timetableService.puthour(model,index);
            log.info(String.valueOf(index));
        }
        index=0;
        return Arrays.asList(prices.clone());
    }

}
