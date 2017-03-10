package de.schakko.samples.aws.worker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "counter")
public class Counter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "instance_name", nullable = false)
	private String instanceName;

	@Column(name = "value")
	private long value = 0;

	@Column(name = "type", nullable = false)
	private String type;

	public Counter() {
	}

	public long increment() {
		return ++value;
	}
}
