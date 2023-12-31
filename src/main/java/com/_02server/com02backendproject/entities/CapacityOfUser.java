package com._02server.com02backendproject.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.antlr.v4.runtime.misc.NotNull;

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

    @NonNull
    @Column(name = "now_capacity")
    private Double nowCapacity; // 현재 배터리 용량

    @Column(name = "average_days")
    private Double averageDays; // 평균 이용 시간

    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user; // 사용자 ID


    // 부모 정의
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_device")
    private Device parentDevice; // 부모기기 ID

    //cascade 설정
}
