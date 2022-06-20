package pl.mrotko.integromat.integration.spotify.auth;

import lombok.RequiredArgsConstructor;
import pl.mrotko.integromat.core.webclient.Authentication;
import pl.mrotko.integromat.core.webclient.HeadersContainer;
import pl.mrotko.integromat.core.webclient.WebClient;
import pl.mrotko.integromat.core.webclient.auth.BasicAuth;
import pl.mrotko.integromat.core.webclient.auth.BearerAuth;
import pl.mrotko.integromat.core.webclient.request.PostRequest;
import pl.mrotko.integromat.core.webclient.response.JsonBodyMapper;
import pl.mrotko.integromat.integration.spotify.core.SpotifyConfig;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.time.LocalDateTime;

@RequiredArgsConstructor
class AccessTokenAuth implements Authentication {

    private final WebClient client;

    private final AccessTokenRequestBody accessTokenRequestBody;

    private final SpotifyConfig config;

    private AccessToken accessToken;

    @Override
    public void configure(HeadersContainer headersContainer) {
        synchronized (this) {
            if (accessToken == null || !accessToken.isValid(LocalDateTime.now())) {
                accessToken = refreshToken();
            }
        }
        new BearerAuth(accessToken.token()).configure(headersContainer);
    }

    private AccessToken refreshToken() {
        var responseMapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, AccessTokenResponseBody.class);

        var request = new PostRequest<>(config.getAuthBasePath(), "/api/token", accessTokenRequestBody, responseMapper);
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");

        new BasicAuth(config.getClientId(), config.getClientSecret()).configure(request);

        var response = client.send(request);
        var validity = LocalDateTime.now().plusSeconds((long) (response.body().getExpiresIn() * 0.7));
        return new AccessToken(response.body().getAccessToken(), validity);
    }

    private record AccessToken(String token, LocalDateTime validity) {

        public boolean isValid(LocalDateTime time) {
            return validity.isBefore(time);
        }
    }
}
