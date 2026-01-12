package com.submersible.probecontrol.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProbeTest {
    @Test
    void probeStartsAtInitialPositionAndDirection() {
        // Given a probe at (0,0) facing NORTH
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);

        // Then its position is (0,0), direction is NORTH, and visited includes start
        assertThat(probe.getPosition()).isEqualTo(new Position(0, 0));
        assertThat(probe.getDirection()).isEqualTo(Direction.NORTH);
        assertThat(probe.getVisitedPositions()).containsExactly(new Position(0, 0));
    }

    @Test
    void probeMovesAndTracksVisitedPositions() {
        // Given a probe
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);

        // When it moves to (0,1)
        probe.moveTo(new Position(0, 1));

        // Then visited includes both positions
        assertThat(probe.getVisitedPositions()).containsExactly(
                new Position(0, 0), new Position(0, 1));
    }


}