# Footy-Sim

A football (soccer) simulation engine that simulates matches and leagues based on team and player power ratings.

## Overview

Footy-Sim is a Java application that simulates football matches and leagues. It uses a power-based system to determine match outcomes where teams with higher power ratings have better chances of winning.

## Features

- Team and player management with power ratings
- Match simulation based on team power
- Full league simulation with fixtures generation
- League table sorting by points, goal difference, and goals scored
- Probability-based match outcome determination

## Classes

### Main Classes

- **Game**: Represents a football match between two teams with methods to simulate match results.
- **Team**: Represents a football team with players, power rating, and match statistics.
- **Player**: Represents a football player with a name and power rating.
- **League**: Manages a collection of teams, fixtures, and the simulation of match days.

### Utility Classes

- **LeagueUtils**: Provides methods for generating fixtures and sorting teams in a league table.
- **MatchSimulator**: Contains the logic for simulating match results based on team power.
- **OutcomeCalculator**: Calculates win and draw probabilities based on team power ratings.
- **PowerUtils**: Utilities for power rating calculations.

## How It Works

1. Teams are created with players, each having a power rating
2. A league is created with a list of teams
3. Fixtures are generated for all teams to play each other
4. Match days are simulated, with each match determined by team power ratings
5. Results are calculated based on probabilistic outcomes
6. The league table is updated with points and goals

## Usage

Example usage from the `Main` class:

```java
// Create players
Player player1 = new Player("Player 1", (byte) 80);
Player player2 = new Player("Player 2", (byte) 75);
// ...

// Create teams
List<Player> teamAPlayers = Arrays.asList(player1, player2, ...);
Team teamA = new Team("Team A", teamAPlayers);
// ...

// Create and simulate league
List<Team> teams = Arrays.asList(teamA, teamB, ...);
League league = new League(teams);

// Simulate all match days
while (league.getCurrentMatchDay() <= league.getTotalMatchDays()) {
    league.simulateMatchDay();
}

// Display final league table
System.out.println(league);
```

## Simulation Logic

Match outcomes are determined based on team power ratings using probability calculations. The system considers:

1. Absolute power difference between teams
2. Home advantage
3. Random elements to create realistic unpredictability

The higher a team's power compared to their opponent, the higher their chances of winning.

