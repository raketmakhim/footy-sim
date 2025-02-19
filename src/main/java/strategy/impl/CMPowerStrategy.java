package strategy.impl;

import constants.PowerConstants;
import objects.Player;
import strategy.PositionPowerStrategy;

public class CMPowerStrategy implements PositionPowerStrategy {
    @Override
    public byte calculate(Player player) {
        return (byte) (
                (player.getPass() + player.getTeamWork()  + player.getSense() + player.getPositioning())                                        * PowerConstants.TOP_STAT_MULTIPLIER/PowerConstants.CM_WEIGHT +
                        (player.getSpeed() + player.getStamina() + player.getShoot() + player.getDribble() + player.getStrength() + player.getTackle() + player.getComposure())*PowerConstants.MID_STAT_MULTIPLIER/PowerConstants.CM_WEIGHT
        );
    }
}
