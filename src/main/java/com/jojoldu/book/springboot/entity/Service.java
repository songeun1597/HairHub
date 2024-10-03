package com.jojoldu.book.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Getter @Setter @Entity
public class Service {
    @Id
    private String serviceId;
    private String serviceGroup;
    private String serviceName;
    private String thumbnail;
    private int price;
    private String servicePictureId;

@ManyToOne
@JoinColumn(name = "designerId", insertable = false, updatable = false) // Designer의 designerId와 매핑
private Designer designer; // 해당 서비스가 가능한 디자이너

}
