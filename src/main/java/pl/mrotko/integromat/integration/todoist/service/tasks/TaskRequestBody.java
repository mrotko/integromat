package pl.mrotko.integromat.integration.todoist.service.tasks;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.core.webclient.request.RequestBody;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Data
@Accessors(chain = true)
public class TaskRequestBody implements RequestBody {

    private String content;

    private String description;

    private Long projectId;

    private Long sectionId;

    private Long parentId;

    private Long order;

    private List<Long> labelIds;

    private Long priority;

    private String dueString;

    private LocalDate dueDate;

    private LocalDateTime dueDatetime;

    private String dueLang;

    private Long assignee;

    @SneakyThrows
    @Override
    public HttpRequest.BodyPublisher toBodyPublisher() {
        return HttpRequest.BodyPublishers.ofString(JsonMapper.JSON_MAPPER.writeValueAsString(this));
    }

    @Override
    public void validate() {
        onlyOneDueFieldNonNull();
    }

    private void onlyOneDueFieldNonNull() {
        long count = Stream.of(dueDate != null, dueDatetime != null, dueString != null)
                .filter(v -> v)
                .count();
        if (count > 1) {
            throw new IllegalStateException("Only one due field can be non used at the same time [dueDate: %s, dueDatetime: %s, dueString: %s]"
                    .formatted(dueDate, dueDatetime, dueString));
        }
    }
}
