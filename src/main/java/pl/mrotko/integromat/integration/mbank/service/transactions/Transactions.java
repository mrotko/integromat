package pl.mrotko.integromat.integration.mbank.service.transactions;

import lombok.RequiredArgsConstructor;
import pl.mrotko.integromat.webclient.WebClient;
import pl.mrotko.integromat.webclient.request.GetRequest;
import pl.mrotko.integromat.webclient.response.JsonBodyMapper;
import pl.mrotko.integromat.integration.mbank.core.MbankConfig;
import pl.mrotko.integromat.integration.mbank.core.MbankJsonMapper;

@RequiredArgsConstructor
public class Transactions implements ITransactions {

    private final WebClient client;

    private final MbankConfig config;

    public TransactionsSearchResponse searchTransactions(TransactionsSearchQuery query) {
        var mapper = new JsonBodyMapper<>(MbankJsonMapper.JSON_MAPPER, TransactionsSearchResponse.class);
        var request = new GetRequest<>(config.getBasePath(), "/Pfm/HistoryApi/GetOperationsPfm", mapper);

        request.addQueryParams(query);

        return client.send(request).body();
    }

}
