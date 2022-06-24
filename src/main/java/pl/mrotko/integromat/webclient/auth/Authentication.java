package pl.mrotko.integromat.webclient.auth;

import pl.mrotko.integromat.webclient.request.HeadersContainer;

public interface Authentication {

    void configure(HeadersContainer headersContainer);
}
