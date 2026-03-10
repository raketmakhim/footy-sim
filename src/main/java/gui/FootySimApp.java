package gui;

import engine.Match;
import engine.MatchOutcomesGenerator;
import enums.MatchOutcomes;
import objects.League;
import objects.Team;
import probability.LeagueMatchProbabilityCalculator;
import repository.TeamRepository;
import service.GoalsCalculator;
import utils.RandomNumberGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FootySimApp extends JFrame {

    private final List<Team> allTeams;

    // Match tab
    private JComboBox<String> homeTeamCombo;
    private JComboBox<String> awayTeamCombo;
    private JLabel matchResultLabel;

    // League tab
    private JTextArea leagueResultsArea;
    private JTextArea leagueTableArea;
    private JButton simulateButton;

    public FootySimApp() {
        super("FootySim");
        allTeams = new TeamRepository().loadTeams();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Single Match", buildMatchTab());
        tabs.addTab("League Simulation", buildLeagueTab());

        add(tabs);
        setVisible(true);
    }

    // -------------------------------------------------------------------------
    // Match Tab
    // -------------------------------------------------------------------------

    private JPanel buildMatchTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] teamNames = allTeams.stream()
                .map(Team::getTeamName)
                .toArray(String[]::new);

        homeTeamCombo = new JComboBox<>(teamNames);
        awayTeamCombo = new JComboBox<>(teamNames);
        if (teamNames.length > 1) awayTeamCombo.setSelectedIndex(1);

        JPanel selectionRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        selectionRow.add(new JLabel("Home:"));
        selectionRow.add(homeTeamCombo);
        selectionRow.add(new JLabel("  vs  "));
        selectionRow.add(new JLabel("Away:"));
        selectionRow.add(awayTeamCombo);

        matchResultLabel = new JLabel("Select teams and click Play", SwingConstants.CENTER);
        matchResultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        matchResultLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
        ));

        JButton playButton = new JButton("Play Match");
        playButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        playButton.addActionListener(e -> playMatch());

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(playButton);

        panel.add(selectionRow, BorderLayout.NORTH);
        panel.add(matchResultLabel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void playMatch() {
        int homeIdx = homeTeamCombo.getSelectedIndex();
        int awayIdx = awayTeamCombo.getSelectedIndex();

        if (homeIdx == awayIdx) {
            JOptionPane.showMessageDialog(this, "Please select two different teams.",
                    "Invalid Selection", JOptionPane.WARNING_MESSAGE);
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

    private JPanel buildLeagueTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        leagueResultsArea = new JTextArea();
        leagueResultsArea.setEditable(false);
        leagueResultsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane resultsScroll = new JScrollPane(leagueResultsArea);
        resultsScroll.setBorder(BorderFactory.createTitledBorder("Match Results"));

        leagueTableArea = new JTextArea();
        leagueTableArea.setEditable(false);
        leagueTableArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane tableScroll = new JScrollPane(leagueTableArea);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Final Table"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, resultsScroll, tableScroll);
        splitPane.setResizeWeight(0.65);

        simulateButton = new JButton("Simulate League");
        simulateButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        simulateButton.addActionListener(e -> startLeagueSimulation());

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(simulateButton);

        panel.add(splitPane, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void startLeagueSimulation() {
        leagueResultsArea.setText("");
        leagueTableArea.setText("");
        simulateButton.setEnabled(false);

        SwingWorker<League, String> worker = new SwingWorker<League, String>() {
            @Override
            protected League doInBackground() {
                List<Team> freshTeams = allTeams.stream()
                        .map(FootySimApp.this::freshCopy)
                        .collect(Collectors.toList());

                League league = new League();
                freshTeams.forEach(league::addTeam);

                league.setMatchResultListener(result -> publish(result.toDisplayString()));

                league.playLeague();
                return league;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String line : chunks) {
                    leagueResultsArea.append(line + "\n");
                }
                leagueResultsArea.setCaretPosition(leagueResultsArea.getDocument().getLength());
            }

            @Override
            protected void done() {
                try {
                    renderFinalTable(get());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FootySimApp.this,
                            "Simulation failed: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    simulateButton.setEnabled(true);
                }
            }
        };

        worker.execute();
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

    // -------------------------------------------------------------------------
    // Entry Point
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FootySimApp::new);
    }
}
