package pl.mrotko.integromat.integration.todoist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"parent"})
public class Project {

    private Long id;

    private String name;

    private Long color;

    private Long parentId;

    private Long order;

    private Long commentCount;

    private boolean shared;

    private boolean favorite;

    private boolean inboxProject;

    private boolean teamInbox;

    private Long syncId;

    private String url;
}
