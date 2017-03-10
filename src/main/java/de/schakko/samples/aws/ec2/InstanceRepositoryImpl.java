package de.schakko.samples.aws.ec2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class InstanceRepositoryImpl implements InstanceRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void discardOutdatedInstances() {
		LocalDateTime date = LocalDateTime.now();
		LocalDateTime r = date.minusSeconds(6000);

		Query q = em.createNativeQuery("DELETE FROM instance WHERE last_contact < ?", Instance.class);
		q.setParameter(1, Date.from(r.toInstant(ZoneOffset.UTC)));
		q.executeUpdate();
	}

}
