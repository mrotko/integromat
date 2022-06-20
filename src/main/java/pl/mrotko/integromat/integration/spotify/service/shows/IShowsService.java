package pl.mrotko.integromat.integration.spotify.service.shows;

import pl.mrotko.integromat.integration.spotify.service.common.ISpotifyService;
import pl.mrotko.integromat.integration.spotify.service.model.Episode;
import pl.mrotko.integromat.integration.spotify.service.model.ShowItem;

public interface IShowsService extends ISpotifyService {

    SearchResponse<ShowItem> getUserSavedShowsResponse(SearchQueryParams query);

    SearchResponse<Episode> getShowEpisodes(String episodeId, SearchQueryParams query);

}
