package pl.mrotko.integromat.integration.spotify.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Image {

    private String url;

    private Long height;

    private Long width;
}
