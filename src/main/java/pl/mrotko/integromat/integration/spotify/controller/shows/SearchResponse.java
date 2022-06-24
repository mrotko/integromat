package pl.mrotko.integromat.integration.spotify.controller.shows;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SearchResponse<T> {

    private String href;

    private Long limit;

    private List<T> items;

    private String next;

    private Long offset;

    private String previous;

    private Long total;
}
