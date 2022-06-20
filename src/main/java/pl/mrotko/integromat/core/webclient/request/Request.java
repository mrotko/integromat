package pl.mrotko.integromat.core.webclient.request;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.hc.core5.net.URIBuilder;
import pl.mrotko.integromat.core.webclient.HeadersContainer;
import pl.mrotko.integromat.core.webclient.HttpMethod;
import pl.mrotko.integromat.core.webclient.QueryParamsContainer;
import pl.mrotko.integromat.core.webclient.response.ResponseBodyMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class Request<REQ extends RequestBody, RES, T>
        implements HeadersContainer, QueryParamsContainer {

    private final Multimap<String, String> headers = HashMultimap.create();

    private final Multimap<String, String> queryParams = HashMultimap.create();

    @Getter
    private final HttpMethod httpMethod;

    private final String basePath;

    private final String path;

    @Getter
    private final REQ body;

    @Getter
    private final ResponseBodyMapper<T, RES> responseBodyMapper;

    public abstract HttpRequest toHttpRequest();

    @SneakyThrows
    protected final HttpRequest.Builder createBuilder() {
        URIBuilder uriBuilder = new URIBuilder(new URI(basePath + path));
        queryParams.forEach(uriBuilder::addParameter);

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(uriBuilder.build());
        headers.forEach(requestBuilder::header);

        return requestBuilder;
    }

    @Override
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public void addHeader(String key, Collection<String> values) {
        headers.putAll(key, values);
    }

    @Override
    public Optional<Collection<String>> getHeaderValues(String key) {
        if (headers.containsKey(key)) {
            return Optional.of(headers.get(key));
        }
        return Optional.empty();
    }

    @Override
    public void addQueryParam(String key, String value) {
        queryParams.put(key, value);
    }

    @Override
    public void addQueryParams(String key, Collection<String> values) {
        queryParams.putAll(key, values);
    }

    public void addQueryParams(RequestQueryParams params) {
        params.getQueryParams().entries().stream()
                .filter(e -> e.getValue() != null)
                .forEach(e -> queryParams.put(e.getKey(), e.getValue().toString()));
    }
}
