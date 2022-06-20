package pl.mrotko.integromat.integration.spotify.service.shows;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.mrotko.integromat.core.webclient.request.RequestQueryParams;
import pl.mrotko.integromat.integration.utils.ValidationUtils;

@Data
public class SearchQueryParams implements RequestQueryParams {

    @Size(min = 0, max = 50)
    private Long limit;

    private Long offset;

    @Length(max = 2)
    private String market;

    @Override
    public void validate() {
        ValidationUtils.validate(this);
    }

    @Override
    public Multimap<String, Object> getQueryParams() {
        HashMultimap<String, Object> multimap = HashMultimap.create();

        multimap.put("limit", limit != null ? limit : 20);
        multimap.put("offset", offset != null ? offset : 0);
        multimap.put("market", market);

        return multimap;
    }
}
