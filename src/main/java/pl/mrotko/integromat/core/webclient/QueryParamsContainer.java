package pl.mrotko.integromat.core.webclient;

import java.util.Collection;

public interface QueryParamsContainer {

    void addQueryParam(String key, String value);

    void addQueryParams(String key, Collection<String> values);
}
