package pl.mrotko.integromat.integration.todoist.service.projects;

import pl.mrotko.integromat.integration.todoist.model.Project;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IProjectController {

    CompletableFuture<List<Project>> getAllProjects();

    CompletableFuture<Project> createProject(CreateProjectBody body);

    CompletableFuture<Project> getProject(long id);

    CompletableFuture<Void> updateProject(long id, UpdateProjectBody body);

    CompletableFuture<Void> deleteProject(long id);
}
