package com.notifications.crypto_notification.Service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.notifications.crypto_notification.Entity.ApiModel;
import com.notifications.crypto_notification.Entity.Timetable;
import com.notifications.crypto_notification.Repository.TimetableRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;
import java.time.LocalDateTime;

@Service
public class TimetableService {

    @Autowired
    public TimetableRepository timetableRepository;
    @Autowired
    public EntityManager em;
    @Autowired
    public FirebaseService firebaseService;

    private double minute_fifteen;
    private double second_fifteen;
    private double minute_hour;
    private double second_hour;

    public Timetable putcurrentprice(ApiModel updatetimetable, Integer id) throws FirebaseMessagingException {
        Timetable timetable =timetableRepository.findById(id).orElse(null);
        timetable.setCurrentprice(updatetimetable.getLast());

        //calculate Time
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fifteen = timetable.getLastupdatefifteen();
        minute_fifteen = (double) ChronoUnit.MINUTES.between(fifteen,now); //minutes between last 15 minute update
        second_fifteen = (double) ChronoUnit.SECONDS.between(fifteen,now)/60; //seconds between last 15 minute update
        LocalDateTime hour = timetable.getLastupdatehour();
        minute_hour = (double) ChronoUnit.MINUTES.between(hour,now); //minutes between last 1h update
        second_hour = (double) ChronoUnit.SECONDS.between(hour,now)/60; //seconds between last 1h update

        //check for 15Min time difference to send out notification
        if(minute_fifteen+second_fifteen>13.5&&minute_fifteen+second_fifteen<=15.5){
            String time_fifteen= "15Min";
            double percentage;
            if(timetable.getCurrentprice()<timetable.getFifteen()){ // decrease
                percentage = (timetable.getFifteen() - timetable.getCurrentprice()) / timetable.getFifteen() *100;
                if(percentage>=3){
                    firebaseService.sendToTopic(timetable.getAsset(),time_fifteen,percentage,"decreased");
                }
            }else if(timetable.getCurrentprice()>timetable.getFifteen()){ //increase
                percentage =  (timetable.getCurrentprice() - timetable.getFifteen()) / timetable.getFifteen() *100;
                if(percentage>=3) {
                    firebaseService.sendToTopic(timetable.getAsset(), time_fifteen, percentage, "increased");
                }
            }
        }

        //check for 1h time difference to send out notification
        if(minute_hour+second_hour>=58.5&&minute_hour+second_hour<=60.5){
            String time_hour="1h";
            double percentage;
            if(timetable.getCurrentprice()<timetable.getHour()){//decrease
                percentage = (timetable.getHour() - timetable.getCurrentprice()) / timetable.getHour() * 100;
                if(percentage>=3){
                    firebaseService.sendToTopic(timetable.getAsset(),time_hour,percentage,"decreased");
                }
            }else if(timetable.getCurrentprice()>timetable.getHour()){//increase
                percentage = (timetable.getCurrentprice() - timetable.getHour()) / timetable.getHour() * 100;
                if(percentage>=3){
                    firebaseService.sendToTopic(timetable.getAsset(),time_hour,percentage,"increased");
                }
            }
        }
        return timetableRepository.save(timetable);
    }

    public Timetable putfifteen(ApiModel updatetimetable, Integer id){
        Timetable timetable =timetableRepository.findById(id).orElse(null);
        timetable.setFifteen(updatetimetable.getLast());
        timetable.setLastupdatefifteen(LocalDateTime.now());
        return timetableRepository.save(timetable);
    }

    public Timetable puthour(ApiModel updatetimetable, Integer id){
        Timetable timetable =timetableRepository.findById(id).orElse(null);
        timetable.setHour(updatetimetable.getLast());
        timetable.setLastupdatehour(LocalDateTime.now());
        return timetableRepository.save(timetable);
    }

}