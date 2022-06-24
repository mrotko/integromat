package pl.mrotko.integromat.integration.spotify.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Episode {

    private String audioPreviewUrl;

    private String description;

    private String html_description;

    private Long durationMs;

    private boolean explicit;

    private ExternalUrls externalUrls;

    private String href;

    private String id;

    private List<Image> images;

    @JsonProperty(value="is_externally_hosted")
    private boolean isExternallyHosted;

    @JsonProperty(value="is_playable")
    private boolean isPlayable;

    @Deprecated
    private String language;

    private List<String> languages;

    private String name;

    private String releaseDate;

    private ReleaseDatePrecision releaseDatePrecision;

    private ResumePoint resumePoint;

    private String type;

    private String uri;

    private Restrictions restrictions;

    private Show show;
}
