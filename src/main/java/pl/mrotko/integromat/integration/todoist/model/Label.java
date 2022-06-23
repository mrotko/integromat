package pl.mrotko.integromat.integration.todoist.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Label {

    private Long id;

    private String name;

    private Long color;

    private Long order;

    private boolean favorite;

}
