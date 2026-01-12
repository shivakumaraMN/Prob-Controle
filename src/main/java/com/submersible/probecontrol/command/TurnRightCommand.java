package com.submersible.probecontrol.command;

import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Probe;

public class TurnRightCommand implements Command {
    @Override
    public void execute(Probe probe, Grid grid) {
        probe.turnRight();
    }
}