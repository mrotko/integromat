package pl.mrotko.integromat.integration.mbank.service.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SortingOrder {
    @JsonProperty("ByDate")
    BY_DATE("ByDate");

    private final String code;
}
