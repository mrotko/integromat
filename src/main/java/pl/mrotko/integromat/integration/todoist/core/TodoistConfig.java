package pl.mrotko.integromat.integration.todoist.core;

import lombok.Getter;
import org.apache.commons.configuration2.Configuration;

import java.util.List;

@Getter
public class TodoistConfig {

    private final String basePath;

    private final String authBasePath;

    private final String clientId;

    private final String clientSecret;

    private final String authToken;

    private final List<String> authScopes;

    public TodoistConfig(Configuration config) {
        this.basePath = config.getString("todoist.base_path");
        this.authBasePath = config.getString("todoist.auth_base_path");
        this.clientId = config.getString("todoist.client_id");
        this.clientSecret = config.getString("todoist.client_secret");
        this.authToken = config.getString("todoist.auth_token");
        this.authScopes = config.getList(String.class, "todoist.auth_scopes");
    }
}
