package service;

import enums.Position;
import objects.Player;
import strategy.PositionPowerStrategy;
import strategy.impl.*;

import java.util.Map;

public class PowerCalculator {
    private final Map<Position, PositionPowerStrategy> strategies;

    public PowerCalculator() {
        strategies = Map.of(
                Position.CB, new CBPowerStrategy(),
                Position.LB, new FBPowerStrategy(),
                Position.RB, new FBPowerStrategy(),
                Position.CM, new CMPowerStrategy(),
                Position.LM, new WMPowerStrategy(),
                Position.RM, new WMPowerStrategy(),
                Position.ST, new STPowerStrategy()
        );
    }

    public byte calculatePower(Player player) {
        PositionPowerStrategy strategy = strategies.get(player.getPosition());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown position: " + player.getPosition());
        }
        return strategy.calculate(player);
    }
}

