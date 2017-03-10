package de.schakko.samples.aws.worker;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PausableWorker {
	private boolean paused = true;

	@NonNull
	private CounterServiceImpl counterService;

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
