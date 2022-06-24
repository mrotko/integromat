package pl.mrotko.integromat.integration.spotify.controller.shows;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import pl.mrotko.integromat.webclient.WebClient;
import pl.mrotko.integromat.webclient.request.GetRequest;
import pl.mrotko.integromat.webclient.response.JsonBodyMapper;
import pl.mrotko.integromat.integration.spotify.core.SpotifyConfig;
import pl.mrotko.integromat.integration.spotify.controller.model.Episode;
import pl.mrotko.integromat.integration.spotify.controller.model.ShowItem;
import pl.mrotko.integromat.integration.spotify.tools.JsonMapper;

@RequiredArgsConstructor
//https://developer.spotify.com/documentation/web-api/reference/#/operations/get-users-saved-shows
public class ShowsController implements IShowsController {

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
