package de.schakko.samples.aws.ec2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private Date lastContact = new Date();

	@Column(name = "type", nullable = true)
	private String type;
	
	@Column(name = "port", nullable = true)
	private Integer port = 8080;

	public Instance() {

	}

	public Instance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrivateIp() {
		return privateIp;
	}

	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}

	public Date getLastContact() {
		return lastContact;
	}

	public void setLastContact(Date lastContact) {
		this.lastContact = lastContact;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
