package pl.mrotko.integromat.core.webclient.request;

import com.google.common.collect.Multimap;

public interface RequestQueryParams {

    void validate();

    Multimap<String, Object> getQueryParams();

}
