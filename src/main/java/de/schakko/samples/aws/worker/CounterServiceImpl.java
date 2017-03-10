package de.schakko.samples.aws.worker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import de.schakko.samples.aws.environment.RuntimeEnvironment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is named {@link CounterServiceImpl} and not {@link CounterService}
 * because it would interfer with spring-boot-actuator.
 * 
 * @author Schakko
 *
 */
@AllArgsConstructor
@Service
@Slf4j
public class CounterServiceImpl {
	private CounterRepository repository;
	private RuntimeEnvironment runtimeEnvironment;

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