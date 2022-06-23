package pl.mrotko.integromat.core.webclient;

import pl.mrotko.integromat.core.webclient.request.Request;
import pl.mrotko.integromat.core.webclient.request.RequestBody;
import pl.mrotko.integromat.core.webclient.response.Response;

import java.util.concurrent.CompletableFuture;

public interface WebClient {

    <REQ extends RequestBody, RES, T> Response<RES> send(Request<REQ, RES, T> request);

    <REQ extends RequestBody, RES, T> CompletableFuture<Response<RES>> sendAsync(Request<REQ, RES, T> request);
}
