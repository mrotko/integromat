package pl.mrotko.integromat.integration.spotify.service.shows;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponse<T> {

    private String href;

    private Long limit;

    private List<T> items;

    private String next;

    private Long offset;

    private String previous;

    private Long total;
}
