package pl.mrotko.integromat.integration.mbank.service.transactions;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import lombok.experimental.Accessors;
import pl.mrotko.integromat.core.webclient.request.RequestQueryParams;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
public class TransactionsSearchQuery implements RequestQueryParams {

    private List<Long> productIds = Collections.emptyList();

    private long pageNumber = 0;

    private Boolean showCreditTransactionTypes = true;

    private Boolean showDebitTransactionTypes = true;

    private Boolean showIrrelevantTransactions = true;

    private Boolean showSavingsAndInvestments = true;

    private Boolean saveShowIrrelevantTransactions = true;

    private Boolean saveShowSavingsAndInvestments = true;

    private Boolean showUncategorizedTransactions = false;

    private Boolean showOkIcon = false;

    private Boolean showAcceptedIcon = false;

    private Boolean useAbsoluteSearch = false;

    private Boolean showBalance = false;

    private SortingOrder sortingOrder = SortingOrder.BY_DATE;

    private LocalDate dateFrom = LocalDate.now().minusMonths(1);

    private LocalDate dateTo = LocalDate.now();

    @Override
    public void validate() {

    }

    @Override
    public Multimap<String, Object> getQueryParams() {
        var multimap = HashMultimap.<String, Object>create();

        multimap.putAll("ProductIds", productIds);
        multimap.put("PageNumber", pageNumber);
        multimap.put("ShowCreditTransactionTypes", showCreditTransactionTypes ? "True" : "False");
        multimap.put("ShowDebitTransactionTypes", showDebitTransactionTypes ? "True" : "False");
        multimap.put("ShowIrrelevantTransactions", showIrrelevantTransactions ? "True" : "False");
        multimap.put("ShowSavingsAndInvestments", showSavingsAndInvestments ? "True" : "False");
        multimap.put("SaveShowIrrelevantTransactions", saveShowIrrelevantTransactions ? "True" : "False");
        multimap.put("SaveShowSavingsAndInvestments", saveShowSavingsAndInvestments ? "True" : "False");
        multimap.put("ShowUncategorizedTransactions", showUncategorizedTransactions ? "True" : "False");
        multimap.put("ShowOkIcon", showOkIcon ? "True" : "False");
        multimap.put("ShowAcceptedIcon", showAcceptedIcon ? "True" : "False");
        multimap.put("UseAbsoluteSearch", useAbsoluteSearch ? "True" : "False");
        multimap.put("ShowBalance", showBalance ? "True" : "False");
        multimap.put("SortingOrder", sortingOrder.getCode());
        multimap.put("DateFrom", dateFrom);
        multimap.put("DateTo", dateTo);

        return multimap;
    }
}
