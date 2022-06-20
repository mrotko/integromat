package pl.mrotko.integromat.integration.spotify.service.shows;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import pl.mrotko.integromat.core.webclient.WebClient;
import pl.mrotko.integromat.core.webclient.request.GetRequest;
import pl.mrotko.integromat.core.webclient.response.JsonBodyMapper;
import pl.mrotko.integromat.integration.spotify.core.SpotifyConfig;
import pl.mrotko.integromat.integration.spotify.service.model.Episode;
import pl.mrotko.integromat.integration.spotify.service.model.ShowItem;
import pl.mrotko.integromat.integration.spotify.tools.JsonMapper;

@RequiredArgsConstructor
//https://developer.spotify.com/documentation/web-api/reference/#/operations/get-users-saved-shows
public class ShowsService implements IShowsService {

    private final WebClient client;

    private final SpotifyConfig config;

    @Override
    @SneakyThrows
    public SearchResponse<ShowItem> getUserSavedShowsResponse(SearchQueryParams query) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, new TypeReference<SearchResponse<ShowItem>>() {});
        var request = new GetRequest<>(config.getBasePath(), "/me/shows", mapper);

        request.addQueryParams(query);

        return client.send(request).body();
    }

    @Override
    public SearchResponse<Episode> getShowEpisodes(String episodeId, SearchQueryParams query) {
        var mapper = new JsonBodyMapper<>(JsonMapper.JSON_MAPPER, new TypeReference<SearchResponse<Episode>>() {});
        var request = new GetRequest<>(config.getBasePath(), "/shows/%s/episodes".formatted(episodeId), mapper);

        request.addQueryParams(query);

        return client.send(request).body();
    }
}
