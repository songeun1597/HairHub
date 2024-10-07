package com.jojoldu.book.springboot.repository;

import com.jojoldu.book.springboot.entity.Designer;
import com.jojoldu.book.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findByEmail(String email);  // Optional은 값이 없을 수도 있는 경우를 안전하게 처리
    // (이는 메서드가 User 객체를 반환할 수도 있고, 이메일이 일치하는 사용자가 없는 경우 Optional.empty()를 반환할 수도 있음)
}
