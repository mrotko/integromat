package pl.mrotko.integromat.integration.spotify.service.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowItem {

    private LocalDateTime addedAt;

    private Show show;

}
