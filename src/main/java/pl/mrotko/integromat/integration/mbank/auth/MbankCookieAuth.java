package pl.mrotko.integromat.integration.mbank.auth;

import org.apache.commons.lang3.tuple.Pair;
import pl.mrotko.integromat.webclient.auth.Authentication;
import pl.mrotko.integromat.webclient.request.HeadersContainer;
import pl.mrotko.integromat.webclient.auth.CookieAuth;
import pl.mrotko.integromat.integration.mbank.core.MbankConfig;

public class MbankCookieAuth implements Authentication {

    private final CookieAuth auth;

    public MbankCookieAuth(MbankConfig config) {
        this.auth = new CookieAuth(() -> Pair.of("mBank2", config.getAuthCookie()));
    }

    @Override
    public void configure(HeadersContainer headersContainer) {
        auth.configure(headersContainer);
    }
}
