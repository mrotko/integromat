package pl.mrotko.integromat.integration.todoist.controller.lables;

import pl.mrotko.integromat.integration.todoist.model.Label;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ILabelsController {

    CompletableFuture<List<Label>> getAllLabels();

    CompletableFuture<Label> createLabel(LabelRequestBody body);

    CompletableFuture<Label> getLabel(long id);

    CompletableFuture<Void> updateLabel(long id, LabelRequestBody body);

    CompletableFuture<Void> deleteLabel(long id);

}
