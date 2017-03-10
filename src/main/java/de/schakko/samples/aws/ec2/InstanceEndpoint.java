package de.schakko.samples.aws.ec2;

import java.util.List;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import de.schakko.samples.aws.ec2.InstanceEndpoint.InstanceReport;
import de.schakko.samples.aws.environment.RuntimeEnvironment;
import lombok.AllArgsConstructor;
import lombok.Value;

@Component
public class InstanceEndpoint extends AbstractEndpoint<InstanceReport> {
	private InstanceRepository repository;
	private RuntimeEnvironment runtimeEnvironment;

	public InstanceEndpoint(InstanceRepository repository, RuntimeEnvironment runtimeEnvironment) {
		super("ec2");
		this.repository = repository;
		this.runtimeEnvironment = runtimeEnvironment;
	}

	@AllArgsConstructor
	@Value
	public static class InstanceReport {
		private Instance active;
		private Iterable<Instance> all;
	}

	@Override
	public InstanceReport invoke() {
		return new InstanceReport(repository.findByName(runtimeEnvironment.getInstanceName()), repository.findAll());
	}
}
