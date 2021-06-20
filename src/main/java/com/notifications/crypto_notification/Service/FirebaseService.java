package com.notifications.crypto_notification.Service;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;


@Service
public class FirebaseService {

    private String percentage4="";
    private String percentage5="";

    public void sendToTopic(String asset, String time, double percentage, String increase_decrease) throws FirebaseMessagingException {
        checkpercentage(percentage);

        List<Message> message = Arrays.asList(
                Message.builder()
                        .setNotification(Notification.builder()
                                .setTitle("Asset:"+asset)
                                .setBody(asset+" "+increase_decrease+" by "+percentage+"% in the past "+time)
                                .build())
                        .setTopic(asset+time+"3%").build(),
                Message.builder()
                        .setNotification(Notification.builder()
                                .setTitle("Asset:"+asset)
                                .setBody(asset+" "+increase_decrease+" by "+percentage+"% in the past "+time)
                                .build())
                        .setTopic(asset+time+percentage4).build(),
                Message.builder()
                        .setNotification(Notification.builder()
                                .setTitle("Asset:"+asset)
                                .setBody(asset+" "+increase_decrease+" by "+percentage+"% in the past "+time)
                                .build())
                        .setTopic(asset+time+percentage5).build()
        );
        BatchResponse response = FirebaseMessaging.getInstance().sendAll(message);
        System.out.println("Successfully sent message to"+response.getSuccessCount());
        percentage4="";
        percentage5="";
    }

    void checkpercentage(double percentage){
        if(percentage>=4){
            percentage4="4%";
            if(percentage>=5){
                percentage5="5%";
            }
        }
    }
}
