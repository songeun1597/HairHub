package com.jojoldu.book.springboot.repository;


import com.jojoldu.book.springboot.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepository extends JpaRepository<Salon, String> {

}
