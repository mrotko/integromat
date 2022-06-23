package pl.mrotko.integromat.integration.spotify.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccessTokenResponseBody {

    private String accessToken;

    private String tokenType;

    private Long expiresIn;

    private String scope;
}
