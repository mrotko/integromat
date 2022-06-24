package pl.mrotko.integromat.integration.todoist.controller.projects;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import pl.mrotko.integromat.webclient.WebClient;
import pl.mrotko.integromat.webclient.request.DeleteRequest;
import pl.mrotko.integromat.webclient.request.GetRequest;
import pl.mrotko.integromat.webclient.request.PostRequest;
import pl.mrotko.integromat.webclient.response.JsonBodyMapper;
import pl.mrotko.integromat.webclient.response.Response;
import pl.mrotko.integromat.webclient.request.ResponseBodyMapper;
import pl.mrotko.integromat.integration.todoist.core.TodoistConfig;
import pl.mrotko.integromat.integration.todoist.model.Project;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class ProjectsController implements IProjectController {

    private final WebClient webClient;

    private final TodoistConfig config;


    public CompletableFuture<List<Project>> getAllProjects() {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, new TypeReference<List<Project>>() {});

        var request = new GetRequest<>(config.getBasePath(), "/projects", mapper);

        return webClient.sendAsync(request).thenApply(Response::body);
    }

    public CompletableFuture<Project> createProject(CreateProjectBody body) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, Project.class);

        var request = new PostRequest<>(config.getBasePath(), "/projects", body, mapper);

        return webClient.sendAsync(request).thenApply(Response::body);
    }

    public CompletableFuture<Project> getProject(long id) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, Project.class);

        var request = new GetRequest<>(config.getBasePath(), "/projects/" + id, mapper);

        return webClient.sendAsync(request).thenApply(Response::body);
    }

    public CompletableFuture<Void> updateProject(long id, UpdateProjectBody body) {
        var request = new PostRequest<>(config.getBasePath(), "/projects/" + id, body, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Void> deleteProject(long id) {
        var request = new DeleteRequest<>(config.getBasePath(), "/projects/" + id, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }
}
