package pl.mrotko.integromat.integration.todoist.service.projects;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.core.webclient.request.RequestBody;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.net.http.HttpRequest;

@Data
@Accessors(chain = true)
public class CreateProjectBody implements RequestBody {

    @NotBlank
    private String name;

    private Long parentId;

    private Long color;

    private boolean favorite;

    @Override
    public void validate() {

    }

    @Override
    @SneakyThrows
    public HttpRequest.BodyPublisher toBodyPublisher() {
        return HttpRequest.BodyPublishers.ofString(JsonMapper.JSON_MAPPER.writeValueAsString(this));
    }
}
