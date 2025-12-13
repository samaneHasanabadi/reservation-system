package ir.azki.reservationsystem.slot.domain;

import ir.azki.reservationsystem.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table(name = "available_slots", indexes = @Index(columnList = "isReserved, start_time"))
@Entity
public class Slot extends BaseEntity {

    @Column(name = "start_time", nullable = false)
    private Date start;
    @Column(name = "end_time", nullable = false)
    private Date end;
    @Column(nullable = false)
    private Boolean isReserved;
    @Version
    @Column(nullable = false)
    private Long version;

    protected void onCreate() {
        super.onCreate();
        this.isReserved = false;
    }
}
