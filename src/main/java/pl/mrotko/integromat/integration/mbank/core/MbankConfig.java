package pl.mrotko.integromat.integration.mbank.core;

import lombok.Getter;
import org.apache.commons.configuration2.Configuration;

// todo session keep alive
@Getter
public class MbankConfig {

    private final String basePath;

    private final String authCookie;

    public MbankConfig(Configuration config) {
        this.basePath = config.getString("mbank.base_path");
        this.authCookie = config.getString("mbank.auth_cookie");
    }
}
