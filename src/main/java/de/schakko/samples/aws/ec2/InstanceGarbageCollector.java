package de.schakko.samples.aws.ec2;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.schakko.samples.aws.environment.RuntimeEnvironment;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InstanceGarbageCollector {
	private InstanceRepository repository;
	private RuntimeEnvironment runtimeEnvironment;

	@Scheduled(fixedRate = 60000)
	public void updateLastContacts() {
		Instance instance = repository.findByName(runtimeEnvironment.getInstanceName());

		if (instance != null) {
			instance.setLastContact(new Date());
			repository.save(instance);
		}

		repository.discardOutdatedInstances();
	}
}
