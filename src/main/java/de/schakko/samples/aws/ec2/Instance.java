package de.schakko.samples.aws.ec2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "instance")
public class Instance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "ip", nullable = true)
	private String privateIp;

	@Column(name = "last_contact", nullable = false)
	private Date lastContact;

	@Column(name = "type", nullable = true)
	private String type;

	@Column(name = "port", nullable = true)
	private String port;

	@Column(name = "commit_id", nullable = true)
	private String commitId;

	public Instance() {
		LocalDateTime ldt = LocalDateTime.now();
		lastContact = Date.from(ldt.toInstant(ZoneOffset.UTC));
	}

	public Instance(String name) {
		this();
		this.name = name;
	}
}
