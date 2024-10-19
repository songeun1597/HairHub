package com.jojoldu.book.springboot.repository;


import com.jojoldu.book.springboot.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, String> {
    List<Salon> findAll(); // 기본적으로 모든 디자이너를 가져옴

}
