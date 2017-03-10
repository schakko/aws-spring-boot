package de.schakko.samples.aws.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("ltr")
public class LongRunningWorker {

	static final Logger log = LoggerFactory.getLogger(LongRunningWorker.class);
	
	private CounterServiceImpl counterService;
	
	public LongRunningWorker(CounterServiceImpl counterService) {
		this.counterService  = counterService;
	}
	
	@Scheduled(fixedRate = 10000)
	public void increment() {
		counterService.incrementCounter("long_running");
	}
}
