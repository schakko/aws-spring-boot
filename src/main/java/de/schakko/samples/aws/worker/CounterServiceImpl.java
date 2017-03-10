package de.schakko.samples.aws.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import de.schakko.samples.aws.RuntimeEnvironment;

/**
 * This class is named {@link CounterServiceImpl} and not {@link CounterService}
 * because it would interfer with spring-boot-actuator.
 * 
 * @author Schakko
 *
 */
@Service
public class CounterServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(CounterServiceImpl.class);

	private CounterRepository repository;
	private RuntimeEnvironment runtimeEnvironment;

	public CounterServiceImpl(CounterRepository repository, RuntimeEnvironment runtimeEnvironment) {
		this.repository = repository;
		this.runtimeEnvironment = runtimeEnvironment;
	}

	public void incrementCounter(String type) {
		Counter counter = find(type);

		log.debug("Update counter {} in {}", type, runtimeEnvironment.getInstanceName());

		counter.increment();

		repository.save(counter);
	}

	public Counter find(String type) {
		Counter counter = repository.findByInstanceNameAndType(runtimeEnvironment.getInstanceName(), type);

		if (counter == null) {
			log.info("Creating new counter for type {} in {}", type, runtimeEnvironment.getInstanceName());
			counter = new Counter();
			counter.setType(type);
			counter.setInstanceName(runtimeEnvironment.getInstanceName());
		}

		return counter;
	}
}