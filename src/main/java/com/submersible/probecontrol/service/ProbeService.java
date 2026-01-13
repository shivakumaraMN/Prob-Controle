package com.submersible.probecontrol.service;

import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Probe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProbeService {
    private final Map<String, Probe> probes = new HashMap<>(); // In-memory storage
    private final Grid grid;

    public ProbeService(Grid grid) {
        this.grid = grid;
    }

    public void saveProbe(String id, Probe probe) {
        probes.put(id, probe);
    }

}
