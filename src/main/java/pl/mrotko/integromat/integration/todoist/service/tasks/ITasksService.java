package pl.mrotko.integromat.integration.todoist.service.tasks;

import pl.mrotko.integromat.integration.todoist.model.Task;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITasksService {

    CompletableFuture<List<Task>> getActiveTasks(GetActiveTasksQuery query);

    CompletableFuture<Task> createTask(TaskRequestBody body);

    CompletableFuture<Task> getActiveTask(long id);

    CompletableFuture<Void> updateTask(long id, TaskRequestBody body);

    CompletableFuture<Void> closeTask(long id);

    CompletableFuture<Void> reopenTask(long id);

    CompletableFuture<Void> deleteTask(long id);
}
