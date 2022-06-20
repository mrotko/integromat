package pl.mrotko.integromat.integration.mbank.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Transaction {

    private Long pfmId;

    private Long operationNumber;

    private String operationCode;

    private String accountNumber;

    private String accountName;

    private String operationType;

    private String description;

    private String subDescription;

    private String subAccountDescription;

    private String categoryId;

    private String comment;

    private String category;

    @JsonProperty("isIrrelevant")
    private boolean isIrrelevant;

    private double amount;

    private double balance;

    private Long merchant;

    private String currency;

    private LocalDate transactionDate;

    private LocalDate originalTransactionDate;

    private boolean showOkIcon;

    @JsonProperty("isSplit")
    private boolean isSplit;

    private boolean showAcceptedIcon;

    private List<Tag> tags;

    private String standingOrder;
}
