package strategy.impl;

import constants.PowerConstants;
import objects.Player;
import strategy.PositionPowerStrategy;

public class STPowerStrategy implements PositionPowerStrategy {
    @Override
    public byte calculate(Player player) {
        return (byte) (
                (player.getStrength() + player.getSpeed() + player.getShoot() + player.getComposure() + player.getSense() + player.getPositioning()) * PowerConstants.TOP_STAT_MULTIPLIER / PowerConstants.ST_WEIGHT +
                        (player.getDribble()) * PowerConstants.MID_STAT_MULTIPLIER / PowerConstants.ST_WEIGHT +
                        (player.getStamina() + player.getPass() + player.getTackle() + player.getTeamWork()) * PowerConstants.BOTTOM_STAT_MULTIPLIER / PowerConstants.ST_WEIGHT
        );
    }
}
