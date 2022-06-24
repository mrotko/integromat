package pl.mrotko.integromat.integration.todoist.controller.lables;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.webclient.request.RequestBody;
import pl.mrotko.integromat.integration.todoist.tools.JsonMapper;

import java.net.http.HttpRequest;

@Data
@Accessors(chain = true)
public class LabelRequestBody implements RequestBody {

    private String name;

    private Long order;

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
