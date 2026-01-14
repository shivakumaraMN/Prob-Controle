package com.submersible.probecontrol.controller;

import com.submersible.probecontrol.command.*;
import com.submersible.probecontrol.model.Probe;
import com.submersible.probecontrol.service.ProbeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/probe")
public class ProbeController {

    private static final Logger log = LoggerFactory.getLogger(ProbeController.class);

    private final ProbeService probeService;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/{id}/commands")
    public ResponseEntity<Map<String, Object>> executeCommands(
            @PathVariable String id,
            @RequestBody List<String> commandStrings) {

        log.info("Executing commands for probe {}: {}", id, commandStrings);

        List<Command> commands = commandStrings.stream()
                .map(this::parseCommand)
                .collect(Collectors.toList());

        Probe updatedProbe = probeService.executeCommands(id, commands);

        log.info("Probe {} updated: position={}, direction={}, visitedCount={}",
                id,
                updatedProbe.getPosition(),
                updatedProbe.getDirection(),
                updatedProbe.getVisitedPositions() != null ? updatedProbe.getVisitedPositions().size() : 0);

        return ResponseEntity.ok(Map.of(
                "position", updatedProbe.getPosition(),
                "direction", updatedProbe.getDirection(),
                "visited", updatedProbe.getVisitedPositions()
        ));
    }

    private Command parseCommand(String cmd) {
        return switch (cmd.toUpperCase()) {
            case "F" -> new MoveForwardCommand();
            case "B" -> new MoveBackwardCommand();
            case "L" -> new TurnLeftCommand();
            case "R" -> new TurnRightCommand();
            default -> throw new IllegalArgumentException("Unknown command: " + cmd);
        };
    }

}
