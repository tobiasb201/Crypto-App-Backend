package com.notifications.crypto_notification.Entity;

import java.time.LocalDateTime;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table
@Getter
@Setter
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String asset;

    private Double currentprice;

    private Double fifteen;

    private Double hour;

    private LocalDateTime lastupdatefifteen;

    private LocalDateTime lastupdatehour;


    public Timetable(){

    }
    public Timetable(@NotNull  String asset,Double currentprice,Double fifteen, Double hour){
        this.asset=asset;
        this.currentprice=currentprice;
        this.fifteen=fifteen;
        this.hour=hour;
    }
}
