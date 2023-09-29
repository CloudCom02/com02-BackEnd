package entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parent_id")
    private Long parentId;

    @Column(name = "entire_capacity")
    private Double entireCapacity; // 배터리 용량

    @Column(name = "using_time")
    private Time usingTime; // 최대 사용 시간

    @Column(name = "charging_time")
    private Time chargingTime; // 충전 시간

    @Column(name = "wattage_W")
    private Double wattage; // 전력량

    @Column(name = "subDevice")
    private Device subDevice; // 부속기기
}
