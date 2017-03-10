package de.schakko.samples.aws.environment;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

/**
 * Update the central table with the last (master) commit on startup
 * 
 * @author Schakko
 */
@AllArgsConstructor
@Component
public class LastCommitRegistrationHandler implements ApplicationListener<ApplicationStartingEvent> {
	private CommitRepository commitRepository;
	private RuntimeEnvironment runtimeEnvironment;

	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		commitRepository.deleteAll();

		commitRepository.save(new Commit(runtimeEnvironment.getGitInfo().getCommitId(),
				runtimeEnvironment.getGitInfo().getCommitMessage()));
	}
}
