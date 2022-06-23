package pl.mrotko.integromat.core.webclient;

import lombok.SneakyThrows;
import org.apache.commons.lang3.Range;
import pl.mrotko.integromat.core.webclient.request.Request;
import pl.mrotko.integromat.core.webclient.request.RequestBody;
import pl.mrotko.integromat.core.webclient.response.Response;
import pl.mrotko.integromat.core.webclient.response.ResponseErrorException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
        var response = httpClient.send(createHttpRequest(request), request.getResponseBodyMapper().bodyHandler());
        return createResponse(request, response);
    }

    @SneakyThrows
    @Override
    public <REQ extends RequestBody, RES, T> CompletableFuture<Response<RES>> sendAsync(Request<REQ, RES, T> request) {
        return httpClient.sendAsync(createHttpRequest(request), request.getResponseBodyMapper().bodyHandler())
                .thenApply(response -> createResponse(request, response));
    }

    private <REQ extends RequestBody, RES, T> Response<RES> createResponse(Request<REQ, RES, T> request, HttpResponse<T> response) {
        if (Range.between(400, 599).contains(response.statusCode())) {
            throw new ResponseErrorException(response.statusCode(), response.body());
        }

        RES responseBody = request.getResponseBodyMapper().map(response.body());
        return new Response<>(responseBody);
    }

    private <REQ extends RequestBody, RES, T> HttpRequest createHttpRequest(Request<REQ, RES, T> request) {
        if (authentication != null) {
            authentication.configure(request);
        }
        if (request.getHeaderValues("Content-Type").filter(l -> !l.isEmpty()).isEmpty()) {
            request.addHeader("Content-Type", "application/json");
        }

        request.addHeader("X-Request-Id", UUID.randomUUID().toString());

        return request.toHttpRequest();
    }
}
