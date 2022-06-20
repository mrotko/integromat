package pl.mrotko.integromat.core.webclient.request;

import pl.mrotko.integromat.core.webclient.HttpMethod;
import pl.mrotko.integromat.core.webclient.response.ResponseBodyMapper;

import java.net.http.HttpRequest;

public class GetRequest<REQ extends RequestBody, RES, T> extends Request<REQ, RES, T> {

    public GetRequest(String basePath, String path, ResponseBodyMapper<T, RES> responseBodyMapper) {
        super(HttpMethod.GET, basePath, path, null, responseBodyMapper);
    }

    @Override
    public HttpRequest toHttpRequest() {
        return createBuilder().GET().build();
    }
}
