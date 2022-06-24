package pl.mrotko.integromat.webclient.request;

import java.util.Collection;

public interface QueryParamsContainer {

    void addQueryParam(String key, String value);

    void addQueryParams(String key, Collection<String> values);
}
