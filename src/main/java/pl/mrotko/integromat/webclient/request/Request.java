package pl.mrotko.integromat.webclient.request;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Accessors(chain = true)
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

    @Setter
    @Getter
    private ContentType contentType = ContentType.APPLICATION_JSON;

    @SneakyThrows
    protected final HttpRequest.Builder createBuilder() {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(createUri());
        headers.forEach(requestBuilder::header);
        requestBuilder.header("Content-Type", contentType.getMimeType());

        return requestBuilder;
    }

    private URI createUri() throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(new URI(basePath + path));
        queryParams.forEach(uriBuilder::addParameter);
        return uriBuilder.build();
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
