package pl.mrotko.integromat.integration.spotify.core;

import lombok.Getter;
import org.apache.commons.configuration2.Configuration;

import java.util.List;

@Getter
public class SpotifyConfig {

    private final String basePath;

    private final String authBasePath;

    private final String clientId;

    private final String clientSecret;

    private final String refreshToken;

    private final List<String> authScopes;

    public SpotifyConfig(Configuration config) {
        this.basePath = config.getString("spotify.base_path");
        this.authBasePath = config.getString("spotify.auth_base_path");
        this.clientId = config.getString("spotify.client_id");
        this.clientSecret = config.getString("spotify.client_secret");
        this.refreshToken = config.getString("spotify.refresh_token");
        this.authScopes = config.getList(String.class, "spotify.auth_scopes");
    }
}
