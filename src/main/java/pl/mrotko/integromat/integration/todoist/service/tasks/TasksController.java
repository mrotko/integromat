package pl.mrotko.integromat.integration.todoist.service.tasks;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import pl.mrotko.integromat.core.webclient.WebClient;
import pl.mrotko.integromat.core.webclient.request.DeleteRequest;
import pl.mrotko.integromat.core.webclient.request.GetRequest;
import pl.mrotko.integromat.core.webclient.request.PostRequest;
import pl.mrotko.integromat.core.webclient.response.JsonBodyMapper;
import pl.mrotko.integromat.core.webclient.response.Response;
import pl.mrotko.integromat.core.webclient.response.ResponseBodyMapper;
import pl.mrotko.integromat.integration.todoist.core.TodoistConfig;
import pl.mrotko.integromat.integration.todoist.model.Task;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class TasksController implements ITasksController {

    private final WebClient webClient;

    private final TodoistConfig config;

    @Override
    public CompletableFuture<List<Task>> getActiveTasks(GetActiveTasksQuery query) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, new TypeReference<List<Task>>() {});

        var request = new GetRequest<>(config.getBasePath(), "/tasks", mapper);
        request.addQueryParams(query);

        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Task> createTask(TaskRequestBody body) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, Task.class);

        var request = new PostRequest<>(config.getBasePath(), "/tasks", body, mapper);

        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Task> getActiveTask(long id) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, Task.class);

        var request = new GetRequest<>(config.getBasePath(), "/tasks/" + id, mapper);

        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Void> updateTask(long id, TaskRequestBody body) {
        var request = new PostRequest<>(config.getBasePath(), "/tasks/" + id, body, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Void> closeTask(long id) {
        var request = new PostRequest<>(config.getBasePath(), "/tasks/" + id + "/close", null, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Void> reopenTask(long id) {
        var request = new PostRequest<>(config.getBasePath(), "/tasks/" + id + "/reopen", null, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Void> deleteTask(long id) {
        var request = new DeleteRequest<>(config.getBasePath(), "/tasks/" + id, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }
}
