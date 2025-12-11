package ir.azki.reservationsystem.slot.api.resources;

import ir.azki.reservationsystem.slot.application.CreateSlotCommand;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateSlotConverter implements Converter<CreateSlotRequest, CreateSlotCommand> {
    @Override
    public @Nullable CreateSlotCommand convert(CreateSlotRequest source) {
        return new CreateSlotCommand(source.start(), source.end());
    }
}
