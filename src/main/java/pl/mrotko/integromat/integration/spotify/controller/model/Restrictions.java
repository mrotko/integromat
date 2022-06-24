package pl.mrotko.integromat.integration.spotify.controller.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Restrictions {

    private String reason;
}
