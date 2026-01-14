package com.submersible.probecontrol.service;

import com.submersible.probecontrol.command.Command;
import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProbeService {
    private static final Logger log = LoggerFactory.getLogger(ProbeService.class);

    private final Map<String, Probe> probes = new HashMap<>(); // In-memory storage
    private final Grid grid;

    public ProbeService(Grid grid) {
        this.grid = grid;
        log.debug("ProbeService instantiated with grid={}", grid);
    }

    public Probe executeCommands(String probeId, List<Command> commands) {
        log.info("Executing {} command(s) for probe {}", commands != null ? commands.size() : 0, probeId);
        Probe probe = probes.get(probeId);
        if (probe == null) {
            log.warn("Probe not found: {}", probeId);
            throw new IllegalArgumentException("Probe not found: " + probeId);
        }
        commands.forEach(command -> {
            try {
                command.execute(probe, grid);
            } catch (Exception e) {
                log.error("Error executing command {} for probe {}: {}", command, probeId, e.getMessage(), e);
                throw e;
            }
        });
        log.debug("Probe {} state after execution: {}", probeId, probe);
        return probe; // No explicit save needed; changes are in-memory
    }

    public void saveProbe(String id, Probe probe) {
        log.info("Saving probe {} with state={}", id, probe);
        probes.put(id, probe);
        log.debug("Probe {} saved", id);
    }

    public Probe getProbe(String id) {
        log.debug("Fetching probe {}", id);
        Probe probe = probes.get(id);
        log.info("Probe {} retrieved: {}", id, probe);
        return probe;
    }

}
