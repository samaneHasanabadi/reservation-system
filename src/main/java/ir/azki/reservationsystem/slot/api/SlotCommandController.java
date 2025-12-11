package ir.azki.reservationsystem.slot.api;

import ir.azki.reservationsystem.slot.api.resources.CreateSlotRequest;
import ir.azki.reservationsystem.slot.application.CreateSlotCommand;
import ir.azki.reservationsystem.slot.application.handler.CreateSlotCommandHandler;
import ir.azki.reservationsystem.slot.application.handler.DeleteSlotCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slot")
@RequiredArgsConstructor
public class SlotCommandController {

    private final CreateSlotCommandHandler createSlotCommandHandler;
    private final DeleteSlotCommandHandler deleteSlotCommandHandler;
    private final ConversionService conversionService;

    @PostMapping("/create")
    public ResponseEntity<String> register(@Valid @RequestBody CreateSlotRequest request) {
        CreateSlotCommand convert = conversionService.convert(request, CreateSlotCommand.class);
        createSlotCommandHandler.handle(convert);
        return ResponseEntity.status(HttpStatus.CREATED).body("Slot is successfully created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id) {
        deleteSlotCommandHandler.handle(id);
        return ResponseEntity.ok().body("Slot is deleted successfully!");
    }
}
