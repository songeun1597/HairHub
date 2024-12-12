package com.jojoldu.book.springboot.repository;


import com.jojoldu.book.springboot.entity.Salon;
import com.jojoldu.book.springboot.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, String> {
}
