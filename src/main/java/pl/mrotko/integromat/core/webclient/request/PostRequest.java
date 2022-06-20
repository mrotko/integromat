package pl.mrotko.integromat.core.webclient.request;

import pl.mrotko.integromat.core.webclient.HttpMethod;
import pl.mrotko.integromat.core.webclient.response.ResponseBodyMapper;

import java.net.http.HttpRequest;

public class PostRequest<REQ extends RequestBody, RES, T> extends Request<REQ, RES, T> {

    public PostRequest(String basePath, String path, REQ body, ResponseBodyMapper<T, RES> responseBodyMapper) {
        super(HttpMethod.POST, basePath, path, body, responseBodyMapper);
    }

    @Override
    public HttpRequest toHttpRequest() {
        getBody().validate();
        return createBuilder().POST(getBody().toBodyPublisher()).build();
    }
}
