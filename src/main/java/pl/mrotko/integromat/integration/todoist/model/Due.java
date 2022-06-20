package pl.mrotko.integromat.integration.todoist.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Due {

    private String string;

    private LocalDate date;

    private Boolean recurring;

    private LocalDateTime datetime;

    private String timezone;

    private String lang;
}
