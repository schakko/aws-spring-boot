package de.schakko.samples.aws.ec2;

public interface InstanceRepositoryCustom {
	/**
	 * Delete every instance which has not been in contact with the backend in
	 * the last hour.
	 */
	public void discardOutdatedInstances();
}
