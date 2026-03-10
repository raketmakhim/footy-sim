# Footy-Sim

A football (soccer) simulation engine that simulates matches and leagues based on team and player power ratings.

## Overview

Footy-Sim is a Java application that simulates football matches and leagues using a top-down approach — match outcomes are determined first based on team power ratings, then goal distributions are calculated accordingly. This ensures more realistic league standings driven by team strength rather than random luck.

## Features

- Team and player management with power ratings
- Match simulation based on team power
- Full league simulation with double round-robin fixtures
- League table sorting by points, goal difference, and goals scored
- Probability-based match outcome determination
- Swing GUI with single match and league simulation modes
- Live match results streamed during league simulation
- Team data loaded from `teams.json` — no hardcoded teams

## Running the App

```bash
mvn package
java -jar target/footy-sim-1.0.0.jar
```

The GUI launches with two tabs:
- **Single Match** — pick any two teams and play a one-off game
- **League Simulation** — simulate a full Premier League season with live results and a final table

## Project Structure

```
src/main/java/
├── gui/            # Swing GUI (FootySimApp)
├── engine/         # Match simulation (Match, MatchOutcomesGenerator)
├── objects/        # Domain models (Team, Player, League, Table)
├── model/          # Data carriers (MatchResult)
├── repository/     # JSON persistence (TeamRepository)
├── service/        # Business logic (GoalsCalculator, PowerCalculator)
├── probability/    # Outcome probability calculations
├── strategy/       # Position-based power calculation strategies
├── constants/      # Stat weights per position
├── enums/          # Position, MatchOutcomes
└── utils/          # RandomNumberGenerator, PowerUtils

src/main/resources/
└── teams.json      # Team data (name, power, offensivePower, defensivePower)
```

## Simulation Logic

Match outcomes are determined based on team power ratings using probability thresholds:

1. Power difference between teams influences draw probability
2. The stronger team has a higher chance of winning
3. Goals are calculated after the outcome is decided, based on offensive vs defensive power

## ⚠️ Known Limitations

- **Players not fully integrated** — `Player` entities exist but match simulation only uses team-level power ratings, not individual player stats.
- **Simplified probability model** — Linear thresholds for match outcomes are a simplification; real football has more variance.

## 💡 Ideas for Extension

- Wire `Player` stats into match simulation (injuries, form, fatigue, etc.)
- Add cup tournament mode alongside the league.
- Persist results to a database or file for season history.
- Add season-over-season progression with player development.
