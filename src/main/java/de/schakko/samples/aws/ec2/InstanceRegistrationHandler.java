package de.schakko.samples.aws.ec2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.schakko.samples.aws.environment.RuntimeEnvironment;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InstanceRegistrationHandler {
	private InstanceRepository repository;
	private RuntimeEnvironment runtimeEnvironment;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Instance instance = new Instance(runtimeEnvironment.getInstanceName());

		instance.setPort(runtimeEnvironment.getPort());
		instance.setCommitId(runtimeEnvironment.getGitInfo().getCommitId());

		if (runtimeEnvironment.isInAws()) {
			instance.setPrivateIp(runtimeEnvironment.getInstanceInfo().getPrivateIp());
			instance.setType(runtimeEnvironment.getInstanceInfo().getInstanceType());
		}

		repository.save(instance);
	}
}
