package pl.mrotko.integromat.integration.spotify.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ShowItem {

    private LocalDateTime addedAt;

    private Show show;

}
