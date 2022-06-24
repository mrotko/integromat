package pl.mrotko.integromat.integration.spotify.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ReleaseDatePrecision {
    @JsonProperty("year")
    YEAR,
    @JsonProperty("month")
    MONTH,
    @JsonProperty("day")
    DAY
}
