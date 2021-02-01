package com.atlaspharmacy.atlaspharmacy.notifications.repository;

import com.atlaspharmacy.atlaspharmacy.notifications.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
