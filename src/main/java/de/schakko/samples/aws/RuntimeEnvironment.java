package de.schakko.samples.aws;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.stereotype.Component;

import com.amazonaws.util.EC2MetadataUtils;
import com.amazonaws.util.EC2MetadataUtils.InstanceInfo;

@Component
public class RuntimeEnvironment {
	private static final Logger log = LoggerFactory.getLogger(RuntimeEnvironment.class);

	private boolean inAws = false;
	private String instanceName = UUID.randomUUID().toString();
	private InstanceInfo instanceInfo;

	/**
	 * You can also use <em>@Value("${local.server.port}")</em>.
	 */
	@LocalServerPort
	private int port;

	@PostConstruct
	void init() {
		try {
			instanceInfo = EC2MetadataUtils.getInstanceInfo();
			inAws = true;
			instanceName = instanceInfo.getInstanceId();
		} catch (Exception e) {
			log.warn("Failed to retrieve EC2 information, maybe not running in AWS?", e);
		}
	}

	public boolean isInAws() {
		return inAws;
	}

	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * @return null
	 */
	public InstanceInfo getInstanceInfo() {
		return instanceInfo;
	}

	public int getPort() {
		return port;
	}
}
