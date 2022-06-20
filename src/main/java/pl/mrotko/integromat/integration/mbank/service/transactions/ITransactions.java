package pl.mrotko.integromat.integration.mbank.service.transactions;

public interface ITransactions {

    TransactionsSearchResponse searchTransactions(TransactionsSearchQuery query);
}

