package com.submersible.probecontrol;

import com.submersible.probecontrol.model.Direction;
import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Position;
import com.submersible.probecontrol.model.Probe;
import com.submersible.probecontrol.service.ProbeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class ProbeControlApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProbeControlApplication.class, args);
	}

	@Bean
	public Grid grid() {
		return new Grid(10, 10, Set.of());
	}

	@Bean
	public CommandLineRunner initialize(ProbeService probeService) {
		return args -> probeService.saveProbe("default", new Probe(new Position(0, 0), Direction.NORTH));
	}
}

