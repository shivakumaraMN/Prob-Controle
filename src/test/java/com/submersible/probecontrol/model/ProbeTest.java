package com.submersible.probecontrol.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProbeTest {
    @Test
    void probeStartsAtInitialPositionAndDirection() {
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);
        assertThat(probe.getPosition()).isEqualTo(new Position(0, 0));
        assertThat(probe.getDirection()).isEqualTo(Direction.NORTH);
        assertThat(probe.getVisitedPositions()).containsExactly(new Position(0, 0));
    }

    @Test
    void probeMovesAndTracksVisitedPositions() {
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);
        probe.moveTo(new Position(0, 1));
        assertThat(probe.getVisitedPositions()).containsExactly(
                new Position(0, 0), new Position(0, 1));
    }
}