package com._02server.com02backendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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

    @Column(name = "device_name", nullable = false)
    private String deviceName; // 분류

    @Column(name = "entire_capacity")
    private Integer entireCapacity; // 배터리 용량

    @Column(name = "maximum_output")
    private Double maximumOutput;

    @Column(name = "watt_perhour")
    private Double wattPerhour; // 전력량

    @Column(name = "category",length = 10)
    private String category; // 분류

    @Column(name = "imageURL", length = 100)
    private String imageURL; // 이미지주소

    @Column(name = "is_registered",nullable = false, columnDefinition = "boolean default false")
    private String isRegistered; // 직접 등록 여부

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user; // 사용자 ID
    //private String contents;
    //private double volt = 3.7;
    //sy-gwak
//    @OneToMany(mappedBy = "parentId")
//    @Column(name = "subDevice")
//    private List<Device> subDevices; // 부속기기 // List<>로 담는 것이 나은건지?
}
