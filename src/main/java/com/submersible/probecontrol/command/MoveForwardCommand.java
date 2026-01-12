package com.submersible.probecontrol.command;

import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Position;
import com.submersible.probecontrol.model.Probe;

public class MoveForwardCommand implements Command {
    @Override
    public void execute(Probe probe, Grid grid) {
        Position current = probe.getPosition();
        Position next = switch (probe.getDirection()) {
            case NORTH -> current.move(0, 1);
            case SOUTH -> current.move(0, -1);
            case EAST -> current.move(1, 0);
            case WEST -> current.move(-1, 0);
        };
        if (grid.canMoveTo(next)) {
            probe.moveTo(next);
        }
        // If can't move, stay put (edge case: obstacles or boundaries)
    }
}