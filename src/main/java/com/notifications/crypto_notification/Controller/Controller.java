package com.notifications.crypto_notification.Controller;

import com.notifications.crypto_notification.Entity.Timetable;
import com.notifications.crypto_notification.Repository.TimetableRepository;
import com.notifications.crypto_notification.Service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private TimetableRepository timetableRepository;

    @CrossOrigin//
    @RequestMapping(
            method = RequestMethod.GET,
            path ="/timetable",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Timetable> getAllTimetableData(){
        return timetableRepository.findAll();
    }
}
