package de.schakko.samples.aws.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PausableWorker {
	static final Logger log = LoggerFactory.getLogger(PausableWorker.class);

	private boolean paused = true;

	private CounterServiceImpl counterService;

	public PausableWorker(CounterServiceImpl counterService) {
		this.counterService = counterService;
	}

	@Scheduled(fixedRate = 10000)
	public void increment() {
		if (!isPaused()) {
			counterService.incrementCounter("pausable");
		}
	}

	public synchronized boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
