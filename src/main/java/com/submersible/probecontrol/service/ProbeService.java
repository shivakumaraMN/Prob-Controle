package com.submersible.probecontrol.service;

import com.submersible.probecontrol.command.Command;
import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Probe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProbeService {
    private final Map<String, Probe> probes = new HashMap<>(); // In-memory storage
    private final Grid grid;

    public ProbeService(Grid grid) {
        this.grid = grid;
    }

    public Probe executeCommands(String probeId, List<Command> commands) {
        Probe probe = probes.get(probeId);
        if (probe == null) {
            throw new IllegalArgumentException("Probe not found: " + probeId);
        }
        commands.forEach(command -> command.execute(probe, grid));
        return probe; // No explicit save needed; changes are in-memory
    }

    public void saveProbe(String id, Probe probe) {
        probes.put(id, probe);
    }
    public Probe getProbe(String id) {
        return probes.get(id);
    }

}
