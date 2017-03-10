package de.schakko.samples.aws.worker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends CrudRepository<Counter, Long> {
	Counter findByInstanceNameAndType(String instanceName, String type);
}
