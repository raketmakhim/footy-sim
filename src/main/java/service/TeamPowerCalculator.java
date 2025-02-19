package service;

import constants.PowerConstants;
import objects.Player;
import objects.Team;

public class TeamPowerCalculator {
    public static byte calculateTeamPower(Team team) {
        return (byte) Math.round(team.getStartingEleven().stream()
                .mapToInt(Player::getPower)
                .average()
                .orElse(0));
    }

    public static byte calculateTotalDefensivePower(Team team) {
        return (byte) ((PowerConstants.calculateDefensivePower(team.getStartingEleven()) * 6 +
                PowerConstants.calculateMidfieldPower(team.getStartingEleven()) * 3 +
                PowerConstants.calculateOffensivePower(team.getStartingEleven())) / 10);
    }

    public static byte calculateTotalOffensivePower(Team team) {
        return (byte) ((PowerConstants.calculateDefensivePower(team.getStartingEleven()) +
                PowerConstants.calculateMidfieldPower(team.getStartingEleven()) * 3 +
                PowerConstants.calculateOffensivePower(team.getStartingEleven()) * 6) / 10);
    }
}

