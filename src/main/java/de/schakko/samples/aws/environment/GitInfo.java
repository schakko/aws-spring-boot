package de.schakko.samples.aws.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GitInfo {
	@Value("${git.commit.message.short}")
	private String commitMessage;

	@Value("${git.branch}")
	private String branch;

	@Value("${git.commit.id}")
	private String commitId;
}