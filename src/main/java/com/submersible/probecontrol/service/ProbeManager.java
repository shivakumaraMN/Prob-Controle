package com.submersible.probecontrol.service;

import com.submersible.probecontrol.model.Direction;
import com.submersible.probecontrol.model.Position;
import com.submersible.probecontrol.model.Probe;
import org.springframework.stereotype.Service;

@Service
public class ProbeManager {
    private final ProbeService probeService;
    public ProbeManager(ProbeService probeService) {
        this.probeService = probeService;
    }
    public void initializeProbe(String id, Position start, Direction direction) {
        Probe probe = new Probe(start, direction);
        probeService.saveProbe(id, probe);
    }

}
