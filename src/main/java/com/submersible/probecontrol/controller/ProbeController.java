package com.submersible.probecontrol.controller;

import com.submersible.probecontrol.command.*;
import com.submersible.probecontrol.model.Probe;
import com.submersible.probecontrol.service.ProbeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/probe")
public class ProbeController {

    private final ProbeService probeService;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/{id}/commands")
    public ResponseEntity<Map<String, Object>> executeCommands(
            @PathVariable String id,
            @RequestBody List<String> commandStrings) {
        List<Command> commands = commandStrings.stream()
                .map(this::parseCommand)
                .collect(Collectors.toList());
        Probe updatedProbe = probeService.executeCommands(id, commands);
        return ResponseEntity.ok(Map.of(
                "position", updatedProbe.getPosition(),
                "direction", updatedProbe.getDirection(),
                "visited", updatedProbe.getVisitedPositions()
        ));
    }

    private Command parseCommand(String cmd) {
        // Enhanced with Java 21 pattern matching for switch (preview feature)
        return switch (cmd.toUpperCase()) {
            case "F" -> new MoveForwardCommand();
            case "B" -> new MoveBackwardCommand();
            case "L" -> new TurnLeftCommand();
            case "R" -> new TurnRightCommand();
            default -> throw new IllegalArgumentException("Unknown command: " + cmd);
        };
    }

}
