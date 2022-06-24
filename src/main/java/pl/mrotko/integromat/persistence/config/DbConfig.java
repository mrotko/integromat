package pl.mrotko.integromat.persistence.config;

import lombok.Getter;
import org.apache.commons.configuration2.Configuration;

@Getter
public class DbConfig {

    private final String username;

    private final String password;

    private final String url;

    public DbConfig(Configuration config) {
        this.username = config.getString("db.username");
        this.password = config.getString("db.password");
        this.url = config.getString("db.url");
    }
}
