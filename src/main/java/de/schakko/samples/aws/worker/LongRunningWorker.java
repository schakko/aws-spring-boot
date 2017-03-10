package de.schakko.samples.aws.worker;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@Profile("ltr")
@AllArgsConstructor
public class LongRunningWorker {
	private CounterServiceImpl counterService;

	@Scheduled(fixedRate = 10000)
	public void increment() {
		counterService.incrementCounter("long_running");
	}
}
