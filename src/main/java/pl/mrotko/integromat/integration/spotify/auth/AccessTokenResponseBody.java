package pl.mrotko.integromat.integration.spotify.auth;

import lombok.Data;

@Data
public class AccessTokenResponseBody {

    private String accessToken;

    private String tokenType;

    private Long expiresIn;

    private String scope;
}
