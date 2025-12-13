package ir.azki.reservationsystem.slot.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.azki.reservationsystem.slot.api.resources.CreateSlotRequest;
import ir.azki.reservationsystem.slot.application.command.CreateSlotCommand;
import ir.azki.reservationsystem.slot.application.command.handler.CreateSlotCommandHandler;
import ir.azki.reservationsystem.slot.application.command.handler.DeleteSlotCommandHandler;
import ir.azki.reservationsystem.slot.application.command.handler.UpdateSlotCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/slot")
@RequiredArgsConstructor
@Tag(name = "Slot API")
public class SlotCommandController {

    private final CreateSlotCommandHandler createSlotCommandHandler;
    private final UpdateSlotCommandHandler updateSlotCommandHandler;
    private final DeleteSlotCommandHandler deleteSlotCommandHandler;
    private final ConversionService conversionService;

    @PostMapping
    @Operation(summary = "create a slot")
    public ResponseEntity<String> create(@Valid @RequestBody CreateSlotRequest request) {
        CreateSlotCommand convert = conversionService.convert(request, CreateSlotCommand.class);
        createSlotCommandHandler.handle(convert);
        return ResponseEntity.status(HttpStatus.CREATED).body("Slot is successfully created");
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a slot")
    public ResponseEntity<String> updateBudget(@PathVariable Long id, @Valid @RequestBody CreateSlotRequest request) throws AccessDeniedException {
        CreateSlotCommand command = conversionService.convert(request, CreateSlotCommand.class);
        updateSlotCommandHandler.handle(id, command);
        return ResponseEntity.ok().body("Slot is updated successfully!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a slot")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id) {
        deleteSlotCommandHandler.handle(id);
        return ResponseEntity.ok().body("Slot is deleted successfully!");
    }
}
