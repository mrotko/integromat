package pl.mrotko.integromat.integration.spotify.service.model;

import lombok.Data;

@Data
public class ResumePoint {

    private boolean fullyPlayed;

    private Long resumePositionMs;
}
