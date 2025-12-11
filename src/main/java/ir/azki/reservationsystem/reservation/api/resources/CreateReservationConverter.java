package ir.azki.reservationsystem.reservation.api.resources;

import ir.azki.reservationsystem.reservation.application.command.CreateReservationCommand;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationConverter implements Converter<CreateReservationRequest, CreateReservationCommand> {
    @Override
    public @Nullable CreateReservationCommand convert(CreateReservationRequest source) {
        return new CreateReservationCommand(source.slotId());
    }
}
