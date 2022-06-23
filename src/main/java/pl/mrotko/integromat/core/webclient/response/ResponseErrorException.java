package pl.mrotko.integromat.core.webclient.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ResponseErrorException extends RuntimeException {

    private final int statusCode;

    private final Object body;

}
