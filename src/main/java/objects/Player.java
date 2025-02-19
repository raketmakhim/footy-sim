package objects;

import enums.Position;
import service.PowerCalculator;

public class Player {
    public String name;
    public Position position;
    public byte age;
    public byte power;

    // Attributes
    private final byte strength, speed, stamina;  // Physical
    private final byte pass, shoot, dribble, tackle;  // Technical
    private final byte composure, sense, positioning, teamWork;  // Mental

    public Player(String name, Position position, byte age, byte[] stats, PowerCalculator powerCalculator) {
        this.name = name;
        this.position = position;
        this.age = age;

        // Assign attributes
        this.strength = stats[0];
        this.speed = stats[1];
        this.stamina = stats[2];
        this.pass = stats[3];
        this.shoot = stats[4];
        this.dribble = stats[5];
        this.tackle = stats[6];
        this.composure = stats[7];
        this.sense = stats[8];
        this.positioning = stats[9];
        this.teamWork = stats[10];

        // Power is now calculated externally
        this.power = powerCalculator.calculatePower(this);
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public byte getAge() {
        return age;
    }

    public byte getPower() {
        return power;
    }

    // Getters for stats (needed by the power calculation)
    public byte getStrength() { return strength; }
    public byte getSpeed() { return speed; }
    public byte getStamina() { return stamina; }
    public byte getPass() { return pass; }
    public byte getShoot() { return shoot; }
    public byte getDribble() { return dribble; }
    public byte getTackle() { return tackle; }
    public byte getComposure() { return composure; }
    public byte getSense() { return sense; }
    public byte getPositioning() { return positioning; }
    public byte getTeamWork() { return teamWork; }
}
