package com._02server.com02backendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Builder
@Table(name = "capacity_of_user")
@AllArgsConstructor
@NoArgsConstructor
public class CapacityOfUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_capacity_id")
    private Long userCapacityId; // 사용자-배터리 ID

    @Column(name = "now_capacity")
    private Double nowCapacity; // 현재 배터리 용량

    @Column(name = "average_days")
    private Double averageDays; // 평균 이용 시간

    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user; // 사용자 ID

    // 부모 정의
    @JoinColumn(name = "parent_device")
    private Long parentDevice; // 부모기기 ID

    @JoinColumn(name = "device_name")
    private String deviceName;

    @JoinColumn(name = "category")
    private String category;
    //cascade 설정
}
