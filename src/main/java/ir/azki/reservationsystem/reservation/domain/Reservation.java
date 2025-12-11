package ir.azki.reservationsystem.reservation.domain;

import ir.azki.reservationsystem.common.domain.BaseEntity;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "slotId", nullable = false)
    private Slot slot;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @Column(nullable = false)
    private Boolean isCanceled;

    @Override
    protected void onCreate() {
        super.onCreate();
        isCanceled = false;
    }
}
