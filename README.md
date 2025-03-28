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



---

🏁 Script executed:
```
#!/bin/bash
# Find all Java files in the src directory
find src -name "*.java" | sort
```

Length of output: 1015

---

🏁 Script executed:
```
#!/bin/bash
# Read the content of each Java file to understand class structure
for file in $(find src -name "*.java" | sort); do
  echo "===== $file ====="
  cat "$file"
  echo ""
done
```

Length of output: 32706

---

🏁 Script executed:
```
#!/bin/bash
# Get additional project information like build files
if [ -f "pom.xml" ]; then
  echo "===== pom.xml ====="
  cat "pom.xml"
fi

if [ -f "build.gradle" ]; then
  echo "===== build.gradle ====="
  cat "build.gradle"
fi

# Check for existing README files
if [ -f "README.md" ]; then
  echo "===== README.md ====="
  cat "README.md"
fi
```

Length of output: 1286

---



</details>

@raketmakhim Based on my analysis of your codebase, I've created a class diagram and a comprehensive README for your footy-sim project:

```mermaid
classDiagram
    class Main {
        +static void main(String[] args)
        -static void playGames(int games)
        -static void playPremierLeague()
    }
    
    class Match {
        -Team homeTeam
        -Team awayTeam
        -byte homeTeamGoals
        -byte awayTeamGoals
        -MatchOutcomesGenerator matchOutcomesGenerator
        +Match(Team homeTeam, Team awayTeam, MatchOutcomesGenerator matchOutcomesGenerator)
        +MatchOutcomes getMatchOutcome()
        -void addGoalsToTeams(Team team, byte goalsScored, byte goalsConceded)
    }
    
    class MatchOutcomesGenerator {
        -ProbabilityCalculator probabilityCalculator
        +MatchOutcomesGenerator(ProbabilityCalculator probabilityCalculator)
        +MatchOutcomes determineOutcome(Team homeTeam, Team awayTeam)
    }
    
    class Team {
        -String teamName
        -List~Player~ startingEleven
        -int points
        -byte win
        -byte draw
        -byte lose
        -int goalsScored
        -int goalsConceded
        -byte power
        -byte defensivePower
        -byte offensivePower
        +Team(String teamName, List~Player~ startingEleven)
        +Team(String teamName, byte power, byte offensivePower, byte defensivePower)
        +void recordWin()
        +void recordDraw()
        +void recordLoss()
    }
    
    class Player {
        +String name
        +Position position
        +byte age
        +byte power
        -byte strength, speed, stamina
        -byte pass, shoot, dribble, tackle
        -byte composure, sense, positioning, teamWork
        +Player(String name, Position position, byte age, byte[] stats, PowerCalculator powerCalculator)
    }
    
    class League {
        -List~Team~ teams
        -Match leagueMatch
        -MatchOutcomesGenerator leagueMatchOutcomeGenerator
        +League()
        +void addTeam(Team team)
        +void playLeague()
        +void playLeagueMatch(Team homeTeam, Team awayTeam)
    }
    
    class Table {
        +static void sortTeams(List~Team~ teams)
        +static void displayTable(League league)
    }
    
    interface ProbabilityCalculator {
        +byte calculateDrawThreshold(byte power1, byte power2)
        +byte calculateWinOrLoseThreshold(byte power1, byte power2)
        +int calculateWinThreshold(byte drawThreshold, byte winOrLoseThreshold)
    }
    
    class LeagueMatchProbabilityCalculator {
        +byte calculateDrawThreshold(byte power1, byte power2)
        +byte calculateWinOrLoseThreshold(byte power1, byte power2)
        +int calculateWinThreshold(byte drawThreshold, byte winOrLoseThreshold)
    }
    
    class GoalsCalculator {
        +static void calculateGoalScored(Match match, MatchOutcomes outcome)
        -static void calculateDrawGoals(Match match)
        -static void calculateGoalsScored(Match match, Team winningTeam, Team losingTeam, MatchOutcomes outcome)
        -static void setTeamGoals(Match match, byte homeGoals, byte awayGoals)
    }
    
    class PowerCalculator {
        -Map~Position, PositionPowerStrategy~ strategies
        +PowerCalculator()
        +byte calculatePower(Player player)
    }
    
    class TeamPowerCalculator {
        +static byte calculateTeamPower(Team team)
        +static byte calculateTotalDefensivePower(Team team)
        +static byte calculateTotalOffensivePower(Team team)
    }
    
    interface PositionPowerStrategy {
        +byte calculate(Player player)
    }
    
    class CBPowerStrategy {
        +byte calculate(Player player)
    }
    
    class FBPowerStrategy {
        +byte calculate(Player player)
    }
    
    class CMPowerStrategy {
        +byte calculate(Player player)
    }
    
    class WMPowerStrategy {
        +byte calculate(Player player)
    }
    
    class STPowerStrategy {
        +byte calculate(Player player)
    }
    
    enum Position {
        GK, LB, CB, RB, RM, CM, LM, ST
        +boolean isDefensive()
        +boolean isOffensive()
        +boolean isMidfield()
    }
    
    enum MatchOutcomes {
        DRAW, HOME_WIN, AWAY_WIN
    }
    
    class PowerUtils {
        +static byte getAbsolutePower(byte power1, byte power2)
    }
    
    class RandomNumberGenerator {
        +static byte generateRandomNumber()
    }
    
    class PowerConstants {
        +static byte CB_TOP_STATS, CB_MID_STATS, CB_BOTTOM_STATS
        +static byte FB_TOP_STATS, FB_MID_STATS, FB_BOTTOM_STATS
        +static byte CM_TOP_STATS, CM_MID_STATS, CM_BOTTOM_STATS
        +static byte WM_TOP_STATS, WM_MID_STATS, WM_BOTTOM_STATS
        +static byte ST_TOP_STATS, ST_MID_STATS, ST_BOTTOM_STATS
        +static byte TOP_STAT_MULTIPLIER, MID_STAT_MULTIPLIER, BOTTOM_STAT_MULTIPLIER
        +static byte CB_WEIGHT, FB_WEIGHT, CM_WEIGHT, WM_WEIGHT, ST_WEIGHT
        +static int calculateDefensivePower(List~Player~ startingEleven)
        +static int calculateOffensivePower(List~Player~ startingEleven)
        +static int calculateMidfieldPower(List~Player~ startingEleven)
    }
    
    Main --> League
    Main --> Match
    Match --> Team
    Match --> MatchOutcomesGenerator
    MatchOutcomesGenerator --> ProbabilityCalculator
    League --> Team
    League --> Match
    League --> MatchOutcomesGenerator
    Team *-- Player
    ProbabilityCalculator <|.. LeagueMatchProbabilityCalculator
    PowerCalculator *-- PositionPowerStrategy
    PositionPowerStrategy <|.. CBPowerStrategy
    PositionPowerStrategy <|.. FBPowerStrategy
    PositionPowerStrategy <|.. CMPowerStrategy
    PositionPowerStrategy <|.. WMPowerStrategy
    PositionPowerStrategy <|.. STPowerStrategy
    Player --> Position
    GoalsCalculator --> Match
```

# Footy-Sim

A football (soccer) simulation engine that provides realistic match and league simulations based on a top-down approach.

## Overview

Footy-Sim is a Java application that uses a sophisticated power-based system to simulate football matches and leagues. The simulation follows a "top-down" approach, where the match outcome is determined first, and then the details (like goals) are calculated based on this outcome. This approach helps create more realistic league standings.

## Key Features

- **Team & Player Management**: Create teams with customizable power ratings or with individual players
- **Match Simulation**: Realistic match outcome calculation using power ratings and probability
- **League Simulation**: Complete league simulation with fixtures, results, and standings
- **Position-Specific Player Power**: Different calculation strategies for different player positions
- **Realistic Goal Distribution**: Goal calculations based on match outcome and team power

## Technical Architecture

### Core Components

#### Match Simulation
- `Match`: Represents a single match between two teams
- `MatchOutcomesGenerator`: Determines match outcomes based on team powers
- `GoalsCalculator`: Calculates realistic goal distributions

#### Team & Player System
- `Team`: Represents a football team with power ratings and statistics
- `Player`: Individual player with position and various attributes
- `Position`: Enum representing football positions (GK, CB, ST, etc.)

#### League Management
- `League`: Manages teams and fixtures, simulates match days
- `Table`: Displays and sorts league standings

#### Power Calculation System
- `PowerCalculator`: Calculates player power based on position and attributes
- `PositionPowerStrategy`: Interface for position-specific power calculations
- Position-specific strategy implementations (CBPowerStrategy, CMPowerStrategy, etc.)

#### Probability System
- `ProbabilityCalculator`: Interface for threshold calculations
- `LeagueMatchProbabilityCalculator`: Implementation for league match probabilities

### How It Works

1. **Match Outcome Determination**:
   - Teams have power ratings (overall, offensive, defensive)
   - The probability of each outcome (home win, away win, draw) is calculated
   - A random number is generated and compared against thresholds to determine the outcome

2. **Goal Calculation**:
   - Based on the match outcome, goals are calculated
   - For wins, the goal difference depends on team power differences
   - For draws, goal distribution follows realistic patterns

3. **League Simulation**:
   - Fixtures are generated for all teams
   - Each match is simulated
   - Teams accumulate points, goals scored, and goals conceded
   - Table is sorted by points, goal difference, etc.

## Usage Examples

### Simulating Premier League

```java
// Create a league
League premierLeague = new League();

// Add teams with power ratings
premierLeague.addTeam(new Team("Manchester United", (byte) 80, (byte) 82, (byte) 75));
premierLeague.addTeam(new Team("Liverpool", (byte) 90, (byte)90, (byte)90));
// Add more teams...

// Play all matches in the league
premierLeague.playLeague();

// Display the final league table
Table.displayTable(premierLeague);
```

### Simulating Multiple Matches Between Two Teams

```java
Team homeTeam = new Team();
Team awayTeam = new Team();

homeTeam.setTeamName("Home Team");
awayTeam.setTeamName("Away Team");

homeTeam.setPower((byte) 90);
awayTeam.setPower((byte) 90);

LeagueMatchProbabilityCalculator probabilityCalculator = new LeagueMatchProbabilityCalculator();
MatchOutcomesGenerator outcomesGenerator = new MatchOutcomesGenerator(probabilityCalculator);
Match match = new Match(homeTeam, awayTeam, outcomesGenerator);

// Simulate match and get outcome
MatchOutcomes result = match.getMatchOutcome();
```

## Player Power Calculation

Players have position-specific power calculations using the Strategy pattern:

1. **CB (Center Back)**: Focus on strength, tackling, composure, sense, and positioning
2. **FB (Full Back)**: Focus on speed, stamina, passing, and tackling
3. **CM (Center Midfielder)**: Focus on passing, teamwork, sense, and positioning
4. **WM (Wide Midfielder)**: Focus on speed, passing, dribbling, and teamwork
5. **ST (Striker)**: Focus on strength, speed, shooting, composure, sense, and positioning

Each position has different weightings for different attribute categories.

## Future Development

As mentioned in the original README:

- Ability to calculate individual players' match statistics
- Add Cup tournaments with knockout stages
- Add a simple GUI interface
- Enhance the realism of simulations

## Setup and Requirements

- Java 23+ (based on pom.xml settings)
- Maven for building the project
- No external dependencies required

<details>
<summary>:white_check_mark: Actions performed</summary>

Review triggered.

> Note: CodeRabbit is an incremental review system and does not re-review already reviewed commits. This command is applicable only when automatic reviews are paused.

</details>
