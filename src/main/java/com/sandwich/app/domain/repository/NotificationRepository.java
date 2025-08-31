package com.sandwich.app.domain.repository;

import com.sandwich.app.domain.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID>,
    JpaSpecificationExecutor<NotificationEntity>,
    QuerydslPredicateExecutor<NotificationEntity> {

}