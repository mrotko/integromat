package pl.mrotko.integromat.integration.todoist.controller.lables;

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
import pl.mrotko.integromat.integration.todoist.model.Label;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class LabelsController implements ILabelsController {

    private final WebClient webClient;

    private final TodoistConfig config;

    @Override
    public CompletableFuture<List<Label>> getAllLabels() {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, new TypeReference<List<Label>>() {});
        var request = new GetRequest<>(config.getBasePath(), "/labels", mapper);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Label> createLabel(LabelRequestBody body) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, Label.class);
        var request = new PostRequest<>(config.getBasePath(), "/labels", body, mapper);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Label> getLabel(long id) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, Label.class);
        var request = new GetRequest<>(config.getBasePath(), "/labels/" + id, mapper);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Void> updateLabel(long id, LabelRequestBody body) {
        var request = new PostRequest<>(config.getBasePath(), "/labels/" + id, body, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }

    @Override
    public CompletableFuture<Void> deleteLabel(long id) {
        var request = new DeleteRequest<>(config.getBasePath(), "/labels/" + id, ResponseBodyMapper.EMPTY_RESPONSE);
        return webClient.sendAsync(request).thenApply(Response::body);
    }
}
