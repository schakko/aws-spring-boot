package de.schakko.samples.aws.ec2;

import java.util.Date;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;

import de.schakko.samples.aws.RuntimeEnvironment;

public class InstanceRegistrationHandler implements ApplicationListener<ApplicationStartingEvent>{
	private InstanceRepository repository;
	private RuntimeEnvironment runtimeEnvironment;
	
	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		Instance instance = new Instance(runtimeEnvironment.getInstanceName());
		
		instance.setPort(runtimeEnvironment.getPort());
		
		if (runtimeEnvironment.isInAws()) {
			instance.setPrivateIp(runtimeEnvironment.getInstanceInfo().getPrivateIp());
			instance.setType(runtimeEnvironment.getInstanceInfo().getInstanceType());
		}
		
		repository.save(instance);
	}
	
	@Scheduled(fixedRate = 60000)
	public void updateLastContacts() {
		Instance instance = repository.findByName(runtimeEnvironment.getInstanceName());
		
		if (instance !=null) {
			instance.setLastContact(new Date());
		}
		
		repository.save(instance);
		repository.discardOutdatedInstances();
	}
}
