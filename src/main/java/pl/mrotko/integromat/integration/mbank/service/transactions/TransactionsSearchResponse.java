package pl.mrotko.integromat.integration.mbank.service.transactions;

import lombok.Data;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.integration.mbank.service.model.Transaction;

import java.util.List;

@Data
@Accessors(chain = true)
public class TransactionsSearchResponse {

    private List<Transaction> transactions;

    private String nextPageUrl;

    private long totalOperationsCount;
}
