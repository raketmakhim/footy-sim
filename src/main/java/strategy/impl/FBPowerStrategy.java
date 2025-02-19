package strategy.impl;

import constants.PowerConstants;
import objects.Player;
import strategy.PositionPowerStrategy;

public class FBPowerStrategy implements PositionPowerStrategy {
    @Override
    public byte calculate(Player player) {
            return (byte) (
                    (player.getSpeed() + player.getStamina() + player.getPass() + player.getTackle())         * PowerConstants.TOP_STAT_MULTIPLIER   /PowerConstants.FB_WEIGHT +
                            (player.getDribble() + player.getSense() + player.getPositioning() + player.getTeamWork())*PowerConstants.MID_STAT_MULTIPLIER   /PowerConstants.FB_WEIGHT +
                            (player.getStrength() + player.getShoot() + player.getComposure())                 *PowerConstants.BOTTOM_STAT_MULTIPLIER/PowerConstants.FB_WEIGHT
            );
    }
}
