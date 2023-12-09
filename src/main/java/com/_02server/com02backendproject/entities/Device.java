package com._02server.com02backendproject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Getter
@Setter
@Builder
@Table(name = "device")
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parent_id")
    private Long parentId; // 부모 기기 id

    @Column(name = "entire_capacity")
    private Double entireCapacity; // 배터리 용량

    @Column(name = "using_time")
    private Time usingTime; // 최대 사용 시간

    @Column(name = "charging_time")
    private Time chargingTime; // 충전 시간

    @Column(name = "wattage_W")
    private Double wattageW; // 전력량

    @Column(name = "category")
    private String category; // 분류

    @Column(name = "imageURL")
    private String imageURL; // 이미지주소

    @Column(name = "is_registered")
    private String isRegistered; // 직접 등록 여부

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user; // 사용자 ID

    @JoinColumn(name = "contents")
    private String contents;

    @JoinColumn(name = "mAh")
    private Integer mAh;

    @JoinColumn(name = "maximumOutput")
    private Double maximumOutput;

    @JoinColumn(name = "deviceName")
    private String deviceName;

    @JoinColumn(name = "volt")
    private Double volt;

    @JoinColumn(name = "maximumOutput")
    private Integer wattPerhour;

//sy-gwak
//    @OneToMany(mappedBy = "parentId")
//    @Column(name = "subDevice")
//    private Device subDevice; // 부속기기 // List<>로 담는 것이 나은건지?
}
