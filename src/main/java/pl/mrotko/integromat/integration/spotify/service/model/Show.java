package pl.mrotko.integromat.integration.spotify.service.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Show {

    private List<String> availableMarkets;

    private List<Copyright> copyrights;

    private String description;

    private String htmlDescription;

    private boolean explicit;

    private ExternalUrls externalUrls;

    private String href;

    private String id;

    private List<Image> images;

    @JsonProperty(value="is_externally_hosted")
    private boolean isExternallyHosted;

    private List<String> languages;

    private String mediaType;

    private String name;

    private String publisher;

    private String type;

    private Long totalEpisodes;

    private String uri;
}
