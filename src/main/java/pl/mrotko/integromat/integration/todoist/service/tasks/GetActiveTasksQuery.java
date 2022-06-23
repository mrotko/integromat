package pl.mrotko.integromat.integration.todoist.service.tasks;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.core.webclient.request.RequestQueryParams;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class GetActiveTasksQuery implements RequestQueryParams {

    private Long projectId;

    private Long sectionId;

    private Long labelId;

    private String filter;

    private String lang;

    private List<Long> ids = new ArrayList<>();

    @Override
    public void validate() {

    }

    @Override
    public Multimap<String, Object> getQueryParams() {
        HashMultimap<String, Object> multimap = HashMultimap.create();

        multimap.put("project_id", projectId);
        multimap.put("section_id", sectionId);
        multimap.put("label_id", labelId);
        multimap.put("filter", filter);
        multimap.put("lang", lang);

        return multimap;
    }
}
