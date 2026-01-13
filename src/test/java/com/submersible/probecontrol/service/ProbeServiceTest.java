package com.submersible.probecontrol.service;

import com.submersible.probecontrol.command.MoveForwardCommand;
import com.submersible.probecontrol.model.Direction;
import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Position;
import com.submersible.probecontrol.model.Probe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProbeServiceTest {
    @Mock
    private Grid grid;

    private ProbeService probeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        probeService = new ProbeService(grid);
    }

    @Test
    void executesCommandsOnExistingProbe() {
        Probe probe = new Probe(new Position(0, 0), Direction.NORTH);
        probeService.saveProbe("test", probe);
        Probe updated = probeService.executeCommands("test", List.of(new MoveForwardCommand()));
        assertThat(updated.getPosition()).isEqualTo(new Position(0, 1));
    }

    @Test
    void throwsExceptionForNonExistentProbe() {
        assertThatThrownBy(() -> probeService.executeCommands("unknown", List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Probe not found: unknown");
    }
}
