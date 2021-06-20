package com.notifications.crypto_notification.Repository;

import com.notifications.crypto_notification.Entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable,Integer> {
}
