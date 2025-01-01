package com.jojoldu.book.springboot.repository;

import com.jojoldu.book.springboot.entity.UserCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConditionRepository extends JpaRepository<UserCondition, String> {

}
