package strategy.impl;

import constants.PowerConstants;
import objects.Player;
import strategy.PositionPowerStrategy;

public class WMPowerStrategy implements PositionPowerStrategy {
    @Override
    public byte calculate(Player player) {
        return (byte) (
                (player.getSpeed() + player.getPass() + player.getDribble() + player.getTeamWork()) * PowerConstants.TOP_STAT_MULTIPLIER / PowerConstants.WM_WEIGHT +
                        (player.getStamina() + player.getShoot() + player.getTackle() + player.getSense() + player.getPositioning()) * PowerConstants.MID_STAT_MULTIPLIER / PowerConstants.WM_WEIGHT +
                        (player.getStrength() + player.getComposure()) * PowerConstants.BOTTOM_STAT_MULTIPLIER / PowerConstants.WM_WEIGHT
        );
    }
}

