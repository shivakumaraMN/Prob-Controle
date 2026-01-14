package com.submersible.probecontrol.service;

import com.submersible.probecontrol.model.Direction;
import com.submersible.probecontrol.model.Position;
import com.submersible.probecontrol.model.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProbeManager {
    private static final Logger log = LoggerFactory.getLogger(ProbeManager.class);

    private final ProbeService probeService;

    public ProbeManager(ProbeService probeService) {
        this.probeService = probeService;
        log.debug("ProbeManager instantiated with ProbeService={}", probeService);
    }

    public void initializeProbe(String id, Position start, Direction direction) {
        log.info("Initializing probe {} at position={} direction={}", id, start, direction);
        Probe probe = new Probe(start, direction);
        probeService.saveProbe(id, probe);
        log.debug("Probe {} saved", id);
    }

    public Probe getProbe(String id) {
        log.debug("Fetching probe {}", id);
        Probe probe = probeService.getProbe(id);
        log.info("Probe {} retrieved: {}", id, probe);
        return probe;
    }
}
