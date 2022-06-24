package pl.mrotko.integromat.webclient.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.mrotko.integromat.webclient.request.ResponseBodyMapper;

import java.net.http.HttpResponse;
import java.util.function.Function;

public class JsonBodyMapper<T> implements ResponseBodyMapper<String, T> {

    private final Function<String, T> mapper;

    public JsonBodyMapper(ObjectMapper objectMapper, Class<T> clazz) {
        this.mapper = body -> catchException(() -> objectMapper.readValue(body, clazz));
    }

    public JsonBodyMapper(ObjectMapper objectMapper, TypeReference<T> typeReference) {
        this.mapper = body -> catchException(() -> objectMapper.readValue(body, typeReference));
    }

    private T catchException(SupplierWithException<T> jsonMapper) {
        try {
            return jsonMapper.get();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private interface SupplierWithException<T> {
        T get() throws JsonProcessingException;
    }

    @Override
    public HttpResponse.BodyHandler<String> bodyHandler() {
        return HttpResponse.BodyHandlers.ofString();
    }

    @Override
    public T map(String responseBody) {
        return mapper.apply(responseBody);
    }
}
