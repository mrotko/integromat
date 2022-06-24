package pl.mrotko.integromat.integration.spotify.controller.shows;

import pl.mrotko.integromat.integration.spotify.controller.model.Episode;
import pl.mrotko.integromat.integration.spotify.controller.model.ShowItem;

public interface IShowsController {

    SearchResponse<ShowItem> getUserSavedShowsResponse(SearchQueryParams query);

    SearchResponse<Episode> getShowEpisodes(String episodeId, SearchQueryParams query);
}
