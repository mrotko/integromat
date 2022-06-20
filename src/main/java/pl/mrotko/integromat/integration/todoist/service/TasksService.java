package pl.mrotko.integromat.integration.todoist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import pl.mrotko.integromat.core.webclient.WebClient;
import pl.mrotko.integromat.core.webclient.request.GetRequest;
import pl.mrotko.integromat.core.webclient.response.JsonBodyMapper;
import pl.mrotko.integromat.integration.todoist.core.TodoistConfig;
import pl.mrotko.integromat.integration.todoist.model.GetActiveTasksQuery;
import pl.mrotko.integromat.integration.todoist.model.Task;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.util.List;

@RequiredArgsConstructor
// https://developer.todoist.com/rest/v1/?shell#tasks
public class TasksService {

    private final WebClient webClient;

    private final TodoistConfig config;

    @SneakyThrows
    public List<Task> getTasks(GetActiveTasksQuery query) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, new TypeReference<List<Task>>() {});

        var request = new GetRequest<>(config.getBasePath(), "/tasks", mapper);
        request.addQueryParams(query);

        var response = webClient.send(request);
        return response.body();
    }
}
