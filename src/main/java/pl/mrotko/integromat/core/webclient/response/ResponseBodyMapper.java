package pl.mrotko.integromat.core.webclient.response;

import java.net.http.HttpResponse;

public interface ResponseBodyMapper<T, R> {

    ResponseBodyMapper<Void, Void> EMPTY_RESPONSE = new ResponseBodyMapper<>() {
        @Override
        public HttpResponse.BodyHandler<Void> bodyHandler() {
            return HttpResponse.BodyHandlers.discarding();
        }

        @Override
        public Void map(Void responseBody) {
            return null;
        }
    };

    HttpResponse.BodyHandler<T> bodyHandler();

    R map(T responseBody);
}
