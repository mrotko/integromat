package pl.mrotko.integromat.integration.spotify.auth;

import lombok.Data;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.core.webclient.request.RequestBody;

import java.net.http.HttpRequest;
import java.util.StringJoiner;

@Data
@Accessors(chain = true)
public class AccessTokenRequestBody implements RequestBody {

    private String grantType;

    private String code;

    private String refreshToken;

    private String redirectUri;

    @Override
    public void validate() {

    }

    @Override
    public HttpRequest.BodyPublisher toBodyPublisher() {
        var joiner = new StringJoiner("&");

        addIfPresent(joiner, "grant_type", grantType);
        addIfPresent(joiner, "code", code);
        addIfPresent(joiner, "redirect_uri", redirectUri);
        addIfPresent(joiner, "refresh_token", refreshToken);

        return HttpRequest.BodyPublishers.ofString(joiner.toString());
    }

    private static void addIfPresent(StringJoiner joiner, String name, String value) {
        if (value != null) {
            joiner.add(name + "=" + value);
        }
    }
}
