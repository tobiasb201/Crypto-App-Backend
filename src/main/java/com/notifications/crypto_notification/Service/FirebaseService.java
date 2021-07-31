package com.notifications.crypto_notification.service;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirebaseService {

    List<String> percentageList = new ArrayList<>();

    public void sendNotificationByTopic(String asset, String time, double percentage, String increase_decrease) throws FirebaseMessagingException {
        checkpercentage(percentage); //Calls function to check the percentage's that have to be sent
        Double roundedPercentage = BigDecimal.valueOf(percentage) //Rounds Percentage by 3digits after dot
                .setScale(3, RoundingMode.HALF_DOWN).doubleValue();

        for (int i=0;i<percentageList.size();i++) { //Goes trough the List of percentages
            Message message = Message.builder() //Sends each percentage Topic
                    .setNotification(Notification.builder()
                            .setTitle("Asset:"+asset) //Notification Title
                            .setBody(asset+" "+increase_decrease+" by "+roundedPercentage+"% in the past "+time) //Notification body
                            .build())
                    .setTopic(asset+time+percentageList.get(i)).build(); //Topic name

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message to"+response);
        }
        percentageList.clear(); //Clears List so next call will have it empty again
    }

    void checkpercentage(double percentage){
        percentageList.add("3%"); //Auto add because function only gets called when 3% is reached
        if(percentage>=4){ //Checks further for 4% and 5%
            percentageList.add("4%");
            if(percentage>=5){
                percentageList.add("5%");
            }
        }
    }
}
