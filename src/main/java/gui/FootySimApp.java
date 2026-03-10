package gui;

import engine.Match;
import engine.MatchOutcomesGenerator;
import enums.MatchOutcomes;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import objects.League;
import objects.Team;
import probability.LeagueMatchProbabilityCalculator;
import repository.TeamRepository;
import service.GoalsCalculator;
import utils.RandomNumberGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FootySimApp extends Application {

    private final List<Team> allTeams = new TeamRepository().loadTeams();

    // Match tab
    private ComboBox<String> homeTeamCombo;
    private ComboBox<String> awayTeamCombo;
    private Label matchResultLabel;

    // League tab
    private TextArea leagueResultsArea;
    private TextArea leagueTableArea;
    private Button simulateButton;

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab matchTab = new Tab("Single Match", buildMatchTab());
        Tab leagueTab = new Tab("League Simulation", buildLeagueTab());
        tabPane.getTabs().addAll(matchTab, leagueTab);

        Scene scene = new Scene(tabPane, 960, 720);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("FootySim");
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------
    // Match Tab
    // -------------------------------------------------------------------------

    private VBox buildMatchTab() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);

        Label header = new Label("Play a Match");
        header.getStyleClass().add("label-header");

        List<String> teamNames = allTeams.stream()
                .map(Team::getTeamName)
                .collect(Collectors.toList());

        homeTeamCombo = new ComboBox<>(FXCollections.observableArrayList(teamNames));
        awayTeamCombo = new ComboBox<>(FXCollections.observableArrayList(teamNames));
        homeTeamCombo.getSelectionModel().selectFirst();
        if (teamNames.size() > 1) awayTeamCombo.getSelectionModel().select(1);

        Label vsLabel = new Label("vs");
        vsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #64748b;");

        HBox selectionRow = new HBox(16,
                new Label("Home:"), homeTeamCombo,
                vsLabel,
                new Label("Away:"), awayTeamCombo);
        selectionRow.setAlignment(Pos.CENTER);

        matchResultLabel = new Label("Select teams and click Play");
        matchResultLabel.getStyleClass().add("result-label");
        matchResultLabel.setMaxWidth(Double.MAX_VALUE);
        matchResultLabel.setAlignment(Pos.CENTER);
        VBox.setVgrow(matchResultLabel, Priority.ALWAYS);

        Button playButton = new Button("Play Match");
        playButton.setOnAction(e -> playMatch());

        root.getChildren().addAll(header, selectionRow, matchResultLabel, playButton);
        return root;
    }

    private void playMatch() {
        int homeIdx = homeTeamCombo.getSelectionModel().getSelectedIndex();
        int awayIdx = awayTeamCombo.getSelectionModel().getSelectedIndex();

        if (homeIdx == awayIdx) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select two different teams.", ButtonType.OK);
            alert.setHeaderText("Invalid Selection");
            alert.showAndWait();
            return;
        }

        Team home = freshCopy(allTeams.get(homeIdx));
        Team away = freshCopy(allTeams.get(awayIdx));

        RandomNumberGenerator rng = new RandomNumberGenerator();
        Match match = new Match(home, away,
                new MatchOutcomesGenerator(new LeagueMatchProbabilityCalculator(), rng),
                new GoalsCalculator(rng));

        MatchOutcomes outcome = match.getMatchOutcome();

        String winner;
        if (outcome == MatchOutcomes.HOME_WIN) {
            winner = home.getTeamName() + " Win";
        } else if (outcome == MatchOutcomes.AWAY_WIN) {
            winner = away.getTeamName() + " Win";
        } else {
            winner = "Draw";
        }

        matchResultLabel.setText(String.format("%s  %d - %d  %s     (%s)",
                home.getTeamName(), match.getHomeTeamGoals(),
                match.getAwayTeamGoals(), away.getTeamName(), winner));
    }

    // -------------------------------------------------------------------------
    // League Tab
    // -------------------------------------------------------------------------

    private VBox buildLeagueTab() {
        VBox root = new VBox(12);
        root.setPadding(new Insets(20));

        Label header = new Label("League Simulation");
        header.getStyleClass().add("label-header");

        leagueResultsArea = new TextArea();
        leagueResultsArea.setEditable(false);
        leagueResultsArea.setWrapText(false);
        leagueResultsArea.setPromptText("Match results will appear here...");

        leagueTableArea = new TextArea();
        leagueTableArea.setEditable(false);
        leagueTableArea.setWrapText(false);
        leagueTableArea.setPromptText("Final table will appear here...");

        Label resultsTitle = new Label("MATCH RESULTS");
        resultsTitle.getStyleClass().add("section-title");
        Label tableTitle = new Label("FINAL TABLE");
        tableTitle.getStyleClass().add("section-title");

        VBox resultsBox = new VBox(4, resultsTitle, leagueResultsArea);
        VBox tableBox = new VBox(4, tableTitle, leagueTableArea);
        VBox.setVgrow(leagueResultsArea, Priority.ALWAYS);
        VBox.setVgrow(leagueTableArea, Priority.ALWAYS);

        SplitPane splitPane = new SplitPane(resultsBox, tableBox);
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPositions(0.6);
        VBox.setVgrow(splitPane, Priority.ALWAYS);

        simulateButton = new Button("Simulate League");
        simulateButton.setOnAction(e -> startLeagueSimulation());

        HBox buttonRow = new HBox(simulateButton);
        buttonRow.setAlignment(Pos.CENTER);

        root.getChildren().addAll(header, splitPane, buttonRow);
        return root;
    }

    private void startLeagueSimulation() {
        leagueResultsArea.clear();
        leagueTableArea.clear();
        simulateButton.setDisable(true);

        Task<League> task = new Task<League>() {
            @Override
            protected League call() {
                List<Team> freshTeams = allTeams.stream()
                        .map(FootySimApp.this::freshCopy)
                        .collect(Collectors.toList());

                League league = new League();
                freshTeams.forEach(league::addTeam);

                league.setMatchResultListener(result ->
                        Platform.runLater(() ->
                                leagueResultsArea.appendText(result.toDisplayString() + "\n")
                        )
                );

                league.playLeague();
                return league;
            }
        };

        task.setOnSucceeded(e -> {
            renderFinalTable(task.getValue());
            simulateButton.setDisable(false);
        });

        task.setOnFailed(e -> {
            new Alert(Alert.AlertType.ERROR, "Simulation failed: " + task.getException().getMessage()).show();
            simulateButton.setDisable(false);
        });

        new Thread(task).start();
    }

    private void renderFinalTable(League league) {
        List<Team> sorted = league.getTeams().stream()
                .sorted(Comparator.comparingInt(Team::getPoints).reversed())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s %-22s %-6s %-5s %-5s %-5s %-6s %-6s%n",
                "Pos", "Team", "Pts", "W", "D", "L", "GF", "GA"));
        sb.append("-".repeat(65)).append("\n");

        for (int i = 0; i < sorted.size(); i++) {
            Team t = sorted.get(i);
            sb.append(String.format("%-4d %-22s %-6d %-5d %-5d %-5d %-6d %-6d%n",
                    i + 1, t.getTeamName(), t.getPoints(),
                    t.getWins(), t.getDraws(), t.getLosses(),
                    t.getGoalsScored(), t.getGoalsConceded()));
        }

        leagueTableArea.setText(sb.toString());
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private Team freshCopy(Team source) {
        return new Team(
                source.getTeamName(),
                source.getPower(),
                (byte) source.getOffensivePower(),
                (byte) source.getDefensivePower()
        );
    }
}
