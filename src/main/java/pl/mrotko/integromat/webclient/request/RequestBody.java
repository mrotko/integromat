package pl.mrotko.integromat.webclient.request;

import java.net.http.HttpRequest;

public interface RequestBody {

    void validate();

    HttpRequest.BodyPublisher toBodyPublisher();
}
