package strategy.impl;

import constants.PowerConstants;
import objects.Player;
import strategy.PositionPowerStrategy;

public class CBPowerStrategy implements PositionPowerStrategy {
    @Override
    public byte calculate(Player player) {
        return (byte) (
                (player.getStrength() + player.getTackle() + player.getComposure() + player.getSense() + player.getPositioning()) * PowerConstants.TOP_STAT_MULTIPLIER / PowerConstants.CB_WEIGHT +
                        (player.getPass() + player.getTeamWork()) * PowerConstants.MID_STAT_MULTIPLIER / PowerConstants.CB_WEIGHT +
                        (player.getSpeed() + player.getStamina() + player.getShoot() + player.getDribble()) * PowerConstants.BOTTOM_STAT_MULTIPLIER / PowerConstants.CB_WEIGHT
        );
    }
}
