package pl.mrotko.integromat.integration.todoist.auth;

import pl.mrotko.integromat.core.webclient.Authentication;
import pl.mrotko.integromat.core.webclient.HeadersContainer;
import pl.mrotko.integromat.core.webclient.auth.BearerAuth;
import pl.mrotko.integromat.integration.todoist.core.TodoistConfig;

public class TodoistTokenAuth implements Authentication {

    private final BearerAuth bearerAuth;

    public TodoistTokenAuth(TodoistConfig config) {
        this.bearerAuth = new BearerAuth(config.getAuthToken());
    }

    @Override
    public void configure(HeadersContainer headersContainer) {
        bearerAuth.configure(headersContainer);
    }
}
