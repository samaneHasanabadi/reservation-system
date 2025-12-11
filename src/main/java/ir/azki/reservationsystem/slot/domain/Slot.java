package ir.azki.reservationsystem.slot.domain;

import ir.azki.reservationsystem.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table(name = "available_slots")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Slot extends BaseEntity {

    @Column(nullable = false)
    private Date start;
    @Column(nullable = false)
    private Date end;
    private Boolean isReserved;
    @Version
    @Column(nullable = false)
    private Long version;

    protected void onCreate() {
        super.onCreate();
        this.isReserved = false;
    }
}
