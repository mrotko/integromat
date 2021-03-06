package pl.mrotko.integromat.integration.spotify.controller.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResumePoint {

    private boolean fullyPlayed;

    private Long resumePositionMs;
}
