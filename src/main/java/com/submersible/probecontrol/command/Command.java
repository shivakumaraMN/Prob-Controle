package com.submersible.probecontrol.command;

import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Probe;

public interface Command {
    void execute(Probe probe, Grid grid);
}
