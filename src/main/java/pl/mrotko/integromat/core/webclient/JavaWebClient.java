package pl.mrotko.integromat.core.webclient;

import lombok.SneakyThrows;
import pl.mrotko.integromat.core.webclient.request.Request;
import pl.mrotko.integromat.core.webclient.request.RequestBody;
import pl.mrotko.integromat.core.webclient.response.Response;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class JavaWebClient implements WebClient {

    private final HttpClient httpClient;

    private final Authentication authentication;

    public JavaWebClient(HttpClient httpClient) {
        this(httpClient, null);
    }

    public JavaWebClient(
            HttpClient httpClient,
            Authentication authentication
    ) {
        this.httpClient = httpClient;
        this.authentication = authentication;
    }

    @SneakyThrows
    @Override
    public <REQ extends RequestBody, RES, T> Response<RES> send(Request<REQ, RES, T> request) {
        if (authentication != null) {
            authentication.configure(request);
        }

        request.addHeader("X-Request-Id", UUID.randomUUID().toString());
        HttpRequest httpRequest = request.toHttpRequest();

        HttpResponse<T> response = httpClient.send(httpRequest, request.getResponseBodyMapper().bodyHandler());
        RES responseBody = request.getResponseBodyMapper().map(response.body());
        return new Response<>(responseBody);
    }
}
