package pl.mrotko.integromat.core.webclient.request;

import pl.mrotko.integromat.core.webclient.HttpMethod;
import pl.mrotko.integromat.core.webclient.response.ResponseBodyMapper;

import java.net.http.HttpRequest;

public class DeleteRequest<REQ extends RequestBody, RES, T> extends Request<REQ, RES, T> {

    public DeleteRequest(String basePath, String path, ResponseBodyMapper<T, RES> responseBodyMapper) {
        super(HttpMethod.DELETE, basePath, path, null, responseBodyMapper);
    }

    @Override
    public HttpRequest toHttpRequest() {
        return createBuilder()
                .DELETE()
                .build();
    }
}
