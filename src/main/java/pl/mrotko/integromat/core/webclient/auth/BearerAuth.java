package pl.mrotko.integromat.core.webclient.auth;

import lombok.RequiredArgsConstructor;
import pl.mrotko.integromat.core.webclient.Authentication;
import pl.mrotko.integromat.core.webclient.HeadersContainer;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class BearerAuth implements Authentication {

    private final Supplier<String> token;

    public BearerAuth(String token) {
        this(() -> token);
    }

    @Override
    public void configure(HeadersContainer headersContainer) {
        headersContainer.addHeader("Authorization", "Bearer " + token.get());
    }
}
