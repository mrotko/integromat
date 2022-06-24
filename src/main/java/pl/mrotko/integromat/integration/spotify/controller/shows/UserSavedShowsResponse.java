package pl.mrotko.integromat.integration.spotify.controller.shows;

import lombok.Data;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.integration.spotify.controller.model.ShowItem;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserSavedShowsResponse {

    private String href;

    private Long limit;

    private List<ShowItem> items;

    private String next;

    private Long offset;

    private String previous;

    private Long total;
}
