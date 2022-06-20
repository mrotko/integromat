package pl.mrotko.integromat.core.webclient;

import java.util.Collection;
import java.util.Optional;

public interface HeadersContainer {

    void addHeader(String key, String value);

    void addHeader(String key, Collection<String> values);

    Optional<Collection<String>> getHeaderValues(String key);
}
