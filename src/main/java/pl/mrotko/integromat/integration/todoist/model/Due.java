package pl.mrotko.integromat.integration.todoist.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Due {

    private String string;

    private LocalDate date;

    private Boolean recurring;

    private LocalDateTime datetime;

    private String timezone;

    private String lang;
}
