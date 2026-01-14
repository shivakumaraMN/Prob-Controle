// java
package com.submersible.probecontrol;

import com.submersible.probecontrol.model.Direction;
import com.submersible.probecontrol.model.Grid;
import com.submersible.probecontrol.model.Position;
import com.submersible.probecontrol.model.Probe;
import com.submersible.probecontrol.service.ProbeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class ProbeControlApplication {

	private static final Logger log = LoggerFactory.getLogger(ProbeControlApplication.class);

	public static void main(String[] args) {
		log.info("Starting ProbeControlApplication");
		SpringApplication.run(ProbeControlApplication.class, args);
	}

	@Bean
	public Grid grid() {
		Grid grid = new Grid(10, 10, Set.of());
		log.debug("Created Grid: {}", grid);
		return grid;
	}

	@Bean
	public CommandLineRunner initialize(ProbeService probeService) {
		return args -> {
			log.info("Initializing default probe");
			probeService.saveProbe("default", new Probe(new Position(0, 0), Direction.NORTH));
			log.debug("Default probe saved with id='default'");
		};
	}
}
