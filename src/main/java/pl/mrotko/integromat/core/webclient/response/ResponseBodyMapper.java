package pl.mrotko.integromat.core.webclient.response;

import java.net.http.HttpResponse;

public interface ResponseBodyMapper<T, R> {

    HttpResponse.BodyHandler<T> bodyHandler();

    R map(T responseBody);
}
