package de.schakko.samples.aws.ec2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepository extends CrudRepository<Instance, Long>, InstanceRepositoryCustom {
	public Instance findByName(String name);
}
