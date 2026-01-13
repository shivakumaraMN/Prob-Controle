package com.submersible.probecontrol.command;

import com.submersible.probecontrol.model.Direction;
import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Position;
import com.submersible.probecontrol.model.Probe;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MoveForwardCommandTest {
    @Test
    void movesForwardWhenPossible() {
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);
        Grid grid = new Grid(10, 10, Set.of());
        Command command = new MoveForwardCommand();
        command.execute(probe, grid);
        assertThat(probe.getPosition()).isEqualTo(new Position(0, 1));
        assertThat(probe.getVisitedPositions()).contains(new Position(0, 1));
    }

    @Test
    void doesNotMoveIntoObstacle() {
        Probe probe = new Probe(new Position(0, 0), Direction.EAST);
        Grid grid = new Grid(10, 10, Set.of(new Position(1, 0)));
        Command command = new MoveForwardCommand();
        command.execute(probe, grid);
        assertThat(probe.getPosition()).isEqualTo(new Position(0, 0));
    }
}
