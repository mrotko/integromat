package pl.mrotko.integromat;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import pl.mrotko.integromat.core.config.AppConfig;
import pl.mrotko.integromat.core.webclient.JavaWebClient;
import pl.mrotko.integromat.integration.mbank.auth.MbankCookieAuth;
import pl.mrotko.integromat.integration.mbank.core.MbankConfig;
import pl.mrotko.integromat.integration.mbank.service.transactions.Transactions;
import pl.mrotko.integromat.integration.mbank.service.transactions.TransactionsSearchQuery;
import pl.mrotko.integromat.integration.spotify.auth.AuthorizationCodeFlowAuth;
import pl.mrotko.integromat.integration.spotify.core.SpotifyConfig;
import pl.mrotko.integromat.integration.spotify.service.shows.SearchQueryParams;
import pl.mrotko.integromat.integration.spotify.service.shows.ShowsService;
import pl.mrotko.integromat.integration.todoist.auth.TodoistTokenAuth;
import pl.mrotko.integromat.integration.todoist.core.TodoistConfig;
import pl.mrotko.integromat.integration.todoist.service.lables.LabelRequestBody;
import pl.mrotko.integromat.integration.todoist.service.lables.LabelsController;
import pl.mrotko.integromat.integration.todoist.service.tasks.TaskRequestBody;
import pl.mrotko.integromat.integration.todoist.service.tasks.TasksController;

import java.net.http.HttpClient;

@Log4j2
public class App {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .build();

//        spotify(client);
//        mBank(client);
        todoist(client);
    }

    @SneakyThrows
    private static void todoist(HttpClient client) {
        var config = new TodoistConfig(AppConfig.CONFIG);

        var todoistTokenAuth = new TodoistTokenAuth(config);
        var todoistWebclient = new JavaWebClient(client, todoistTokenAuth);

        var tasksController = new TasksController(todoistWebclient, config);
        var labelsController = new LabelsController(todoistWebclient, config);
//        var projectsController = new ProjectsService(todoistWebclient, config);

        var label = labelsController.createLabel(new LabelRequestBody().setName("test 123333")).get();
        log.debug(label.toString());

        var getLabel = labelsController.getLabel(label.getId()).get();
        log.debug(getLabel.toString());

        var updateTask = labelsController.updateLabel(getLabel.getId(), new LabelRequestBody().setName("test 123333")).get();
//        labelsController.deleteLabel(getLabel.getId()).get();



//        var task = tasksController.createTask(new TaskRequestBody().setContent("test 123333")).get();
//        log.debug(task.toString());
//
//        var getTask = tasksController.getActiveTask(task.getId()).get();
//        log.debug(getTask.toString());
//
//        var updateTask = tasksController.updateTask(getTask.getId(), new TaskRequestBody().setContent("bla bla")).get();
//        tasksController.closeTask(getTask.getId()).get();
//        tasksController.reopenTask(getTask.getId()).get();
//        tasksController.deleteTask(getTask.getId()).get();

//        var task = tasksController.createTask(new TaskRequestBody().setContent("test 123333")).get();
//        log.debug(task.toString());
//
//        var getTask = tasksController.getActiveTask(task.getId()).get();
//        log.debug(getTask.toString());

//        var updateTask = tasksController.updateTask(getTask.getId(), new TaskRequestBody().setContent("bla bla")).get();
//        tasksController.closeTask(getTask.getId()).get();
//        tasksController.reopenTask(getTask.getId()).get();
//        tasksController.deleteTask(getTask.getId()).get();

//        var projects = projectsController.getAllProjects();
//        System.out.println("projects = " + projects);

//        var test = projectsController.createProject(new CreateProjectBody().setName("test"));
//        System.out.println("test = " + test);

//        var project = projectsController.getProject(2175626018L);

//        log.info(project.get().toString());
//        System.out.println("project = " + project.get().toString());

//        var unused = projectsController.deleteProject(2293806550L);

//        GetActiveTasksQuery query = new GetActiveTasksQuery();
//        query.setProjectId(2278775775L);
//        query.getIds().add(4926373587L);
//        query.getIds().add(4926373588L);
//        List<Task> activeTasks = todoistService.getTasks(query);
//        System.out.println("activeTasks = " + activeTasks);
    }

    private static void spotify(HttpClient client) {
        var spotifyAuthWebClient = new JavaWebClient(client);
        var spotifyConfig = new SpotifyConfig(AppConfig.CONFIG);
        var spotifyAuth = new AuthorizationCodeFlowAuth(spotifyAuthWebClient, spotifyConfig);

        var spotifyWebClient = new JavaWebClient(client, spotifyAuth);
        var service = new ShowsService(spotifyWebClient, spotifyConfig);
        var shows = service.getUserSavedShowsResponse(new SearchQueryParams());

        var show = shows.getItems().get(0);
        log.info(show.toString());
        System.out.println("show = " + show);

        var episodes = service.getShowEpisodes(show.getShow().getId(), new SearchQueryParams());
        var episode = episodes.getItems().get(0);
        System.out.println("episode = " + episode);
    }

    private static void mBank(HttpClient client) {
        MbankConfig config = new MbankConfig(AppConfig.CONFIG);

        var cookieAuth = new MbankCookieAuth(config);
        var mbankClient = new JavaWebClient(client, cookieAuth);
        var transactions = new Transactions(mbankClient, config);
        var response = transactions.searchTransactions(new TransactionsSearchQuery());
        System.out.println("response = " + response);
    }
}
