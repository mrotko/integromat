package pl.mrotko.integromat.webclient.request;

import java.net.http.HttpRequest;

public class PostRequest<REQ extends RequestBody, RES, T> extends Request<REQ, RES, T> {

    public PostRequest(String basePath, String path, REQ body, ResponseBodyMapper<T, RES> responseBodyMapper) {
        super(HttpMethod.POST, basePath, path, body, responseBodyMapper);
    }

    @Override
    public HttpRequest toHttpRequest() {
        var body = getBody();
        if (body == null) {
            return createBuilder().POST(HttpRequest.BodyPublishers.noBody()).build();
        }

        body.validate();
        return createBuilder().POST(body.toBodyPublisher()).build();
    }
}
