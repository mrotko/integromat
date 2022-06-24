package pl.mrotko.integromat.integration.spotify.auth;

import pl.mrotko.integromat.webclient.auth.Authentication;
import pl.mrotko.integromat.webclient.request.HeadersContainer;
import pl.mrotko.integromat.webclient.WebClient;
import pl.mrotko.integromat.integration.spotify.core.SpotifyConfig;

public class AuthorizationCodeFlowAuth implements Authentication {

    private final AccessTokenAuth accessTokenAuth;

    public AuthorizationCodeFlowAuth(WebClient client, SpotifyConfig config) {
        this.accessTokenAuth = new AccessTokenAuth(client, createAccessTokenRequestBody(config), config);
    }

    private static AccessTokenRequestBody createAccessTokenRequestBody(SpotifyConfig config) {
        var requestBody = new AccessTokenRequestBody();

        requestBody.setGrantType("refresh_token");
        requestBody.setRefreshToken(config.getRefreshToken());

        return requestBody;
    }

    @Override
    public void configure(HeadersContainer headersContainer) {
        accessTokenAuth.configure(headersContainer);
    }
}
