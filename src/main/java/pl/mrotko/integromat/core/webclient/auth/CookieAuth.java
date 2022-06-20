package pl.mrotko.integromat.core.webclient.auth;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import pl.mrotko.integromat.core.webclient.Authentication;
import pl.mrotko.integromat.core.webclient.HeadersContainer;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CookieAuth implements Authentication {

    private final Supplier<Pair<String, String>> cookieAuthValueProvider;

    @Override
    public void configure(HeadersContainer headersContainer) {
        var cookieValues = headersContainer.getHeaderValues("Cookie")
                .orElse(Collections.emptyList()).stream()
                .map(v -> {
                    var split = v.split("=");
                    if (split.length == 2) {
                        return Pair.of(split[0], split[1]);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        var cookieAuthValue = cookieAuthValueProvider.get();
        cookieValues.put(cookieAuthValue.getKey(), cookieAuthValue.getRight());

        var resultCookieValues = cookieValues.entrySet().stream()
                .map(pair -> pair.getKey() + "=" + pair.getValue())
                .collect(Collectors.toList());

        headersContainer.addHeader("Cookie", resultCookieValues);

    }
}
