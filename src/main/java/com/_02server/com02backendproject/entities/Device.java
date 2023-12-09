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

    @Column(name = "device_name")
    private String name; // 분류

    @Column(name = "entire_capacity")
    private Integer entireCapacity; // 배터리 용량

    @Column(name = "maximum_output")
    private Double maximum_output;

    @Column(name = "wattPerhour")
    private Integer wattPerhour; // 전력량

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

    private String contents;
    private double volt = 3.7;

    @JoinColumn(name = "contents")
    private String contents;

    @JoinColumn(name = "mAh")
    private Integer mAh;

    @JoinColumn(name = "maximumOutput")
    private Double maximumOutput;

    @JoinColumn(name = "volt")
    private Double volt;


//    @OneToMany(mappedBy = "parentId")
//    @Column(name = "subDevice")
//    private Device subDevice; // 부속기기 // List<>로 담는 것이 나은건지?
}
