package pl.mrotko.integromat.core.webclient.auth;

import lombok.RequiredArgsConstructor;
import pl.mrotko.integromat.core.webclient.Authentication;
import pl.mrotko.integromat.core.webclient.HeadersContainer;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
public class BasicAuth implements Authentication {

    private final String username;

    private final String password;

    @Override
    public void configure(HeadersContainer headersContainer) {
        headersContainer.addHeader("Authorization", "Basic " + getEncodedCredentials());
    }

    private String getEncodedCredentials() {
        var credentials = username + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }
}
