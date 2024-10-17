package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter @Setter @Entity
public class Service {
    @Id
    private String serviceId;
    private String serviceGroup;
    private String serviceName;
    private String thumbnail;
    private int price;
    private String servicePictureId;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;  //Service에 연결된 Reservation 리스트

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setService(this);
    }

    @ManyToOne
    @JoinColumn(name ="designerId")  //Designer의 designerId와 매핑
    private Designer designer;  //해당 서비스가 속한 디자이너


    // ID 자동 생성 로직 추가 (UUID 사용 예시)
    @PrePersist
    public void prePersist() {
        if (this.serviceId == null) {
            this.serviceId = UUID.randomUUID().toString();
        }
    }
}