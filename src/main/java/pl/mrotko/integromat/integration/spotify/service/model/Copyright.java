package pl.mrotko.integromat.integration.spotify.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Copyright {

    private String text;

    private String type;

}
