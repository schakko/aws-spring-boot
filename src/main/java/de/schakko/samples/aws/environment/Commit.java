package de.schakko.samples.aws.environment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * The <em>last_commit<em> table contains one row with the last deployed Git
 * revision.
 * 
 * @author Schakko
 */
@Data
@Entity
@Table(name = "last_commit")
public class Commit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "hash")
	private String hash;

	@Column(name = "message")
	private String message;

	public Commit() {
	}

	public Commit(String hash, String message) {
		this.hash = hash;
		this.message = message;
	}
}
