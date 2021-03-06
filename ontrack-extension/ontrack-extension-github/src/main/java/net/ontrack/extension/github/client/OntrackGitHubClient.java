package net.ontrack.extension.github.client;

import net.ontrack.extension.github.model.GitHubCommit;
import net.ontrack.extension.github.model.GitHubIssue;

import java.util.List;

public interface OntrackGitHubClient {

    GitHubIssue getIssue(String project, GitHubClientConfigurator configurator, int id);

    List<GitHubCommit> getCommitsForIssue(String project, GitHubClientConfigurator configurator, int id);
}
