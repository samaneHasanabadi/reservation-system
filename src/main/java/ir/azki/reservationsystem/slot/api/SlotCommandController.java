package ir.azki.reservationsystem.slot.api;

import ir.azki.reservationsystem.slot.api.resources.CreateSlotRequest;
import ir.azki.reservationsystem.slot.application.CreateSlotCommand;
import ir.azki.reservationsystem.slot.application.handler.CreateCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slot")
@RequiredArgsConstructor
public class SlotCommandController {

    private final CreateCommandHandler createCommandHandler;
    private final ConversionService conversionService;

    @PostMapping("/create")
    public ResponseEntity<String> register(@Valid @RequestBody CreateSlotRequest request) {
        CreateSlotCommand convert = conversionService.convert(request, CreateSlotCommand.class);
        createCommandHandler.handle(convert);
        return ResponseEntity.status(HttpStatus.CREATED).body("Slot is successfully created");
    }
}
