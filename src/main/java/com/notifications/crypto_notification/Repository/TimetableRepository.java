package com.notifications.crypto_notification.repository;

import com.notifications.crypto_notification.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable,Integer> {
}
