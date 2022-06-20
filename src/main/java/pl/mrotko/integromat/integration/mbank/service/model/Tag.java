package pl.mrotko.integromat.integration.mbank.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tag {

    private long id;

    private String name;

    @JsonProperty("isSelected")
    private boolean isSelected;
}
