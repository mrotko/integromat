package pl.mrotko.integromat.integration.todoist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"parent"})
public class Task {

    private Long id;

    private Long projectId;

    private Long sectionId;

    private String content;

    private String description;

    private Boolean completed;

    private List<Long> labelIds;

    private Long parentId;

    private Long order;

    private Long priority;

    private Due due;

    private String url;

    private Long commentCount;

    private Long assignee;

    private Long assigner;

    private Long creator;

    private OffsetDateTime created;
}
