package pl.mrotko.integromat.integration.spotify.service.shows;

import lombok.Data;
import pl.mrotko.integromat.integration.spotify.service.model.ShowItem;

import java.util.List;

@Data
public class UserSavedShowsResponse {

    private String href;

    private Long limit;

    private List<ShowItem> items;

    private String next;

    private Long offset;

    private String previous;

    private Long total;
}
