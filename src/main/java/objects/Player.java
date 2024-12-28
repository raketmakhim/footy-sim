package objects;

import constants.PowerConstants;
import enums.Position;

public class Player {
    public String name;
    public Position position;
    public byte age;
    public byte power;

    //physical
    public byte strength;
    public byte speed;
    public byte stamina;

    //technical
    public byte pass;
    public byte shoot;
    public byte dribble;
    public byte tackle;

    //mental
    public byte composure;
    public byte sense;
    public byte positioning;
    public byte teamWork;

    public Player(String name, Position position, byte age, byte[] stats){
        this.name = name;
        this.position = position;
        this.age = age;
        this.power = calculatePower(this.position);

        //physical
        this.strength = stats[0];
        this.speed = stats[1];
        this.stamina = stats[2];

        //technical
        this.pass = stats[3];
        this.shoot= stats[4];
        this.dribble= stats[5];
        this.tackle= stats[6];

        //mental
        this.composure= stats[7];
        this.sense= stats[8];
        this.positioning= stats[9];
        this.teamWork= stats[10];
    }

    public byte getPower() {
        return power;
    }

    public byte calculatePower(Position position){
        return switch (position) {
            case CB -> sumOfCBPower();
            case LB, RB -> sumOfFBPower();
            case CM -> sumOfCMPower();
            case LM,RM -> sumOfWMPower();
            case ST -> sumOfSTPower();
            default -> throw new IllegalArgumentException("Unknown position: " + position);
        };
    }

    public byte sumOfCBPower(){

        return (byte) (
            (this.strength + this.tackle + this.composure + this.sense + this.positioning)*PowerConstants.TOP_STAT_MULTIPLIER   /PowerConstants.CB_WEIGHT +
            (this.pass + this.teamWork)                                                   *PowerConstants.MID_STAT_MULTIPLIER   /PowerConstants.CB_WEIGHT +
            (this.speed + this.stamina + this.shoot + this.dribble)                       *PowerConstants.BOTTOM_STAT_MULTIPLIER/PowerConstants.CB_WEIGHT
        );
    }

    public byte sumOfFBPower(){
        return (byte) (
            (this.speed + this.stamina + this.pass + this.tackle)         *PowerConstants.TOP_STAT_MULTIPLIER   /PowerConstants.FB_WEIGHT +
            (this.dribble + this.sense + this.positioning + this.teamWork)*PowerConstants.MID_STAT_MULTIPLIER   /PowerConstants.FB_WEIGHT +
            (this.strength + this.shoot + this.composure)                 *PowerConstants.BOTTOM_STAT_MULTIPLIER/PowerConstants.FB_WEIGHT
        );
    }

    public byte sumOfCMPower(){
        return (byte) (
            (this.pass + this.teamWork  + this.sense + this.positioning)                                        *PowerConstants.TOP_STAT_MULTIPLIER/PowerConstants.CM_WEIGHT +
            (this.speed + this.stamina + this.shoot + this.dribble+this.strength + this.tackle + this.composure)*PowerConstants.MID_STAT_MULTIPLIER/PowerConstants.CM_WEIGHT
        );
    }

    public byte sumOfWMPower(){
        return (byte) (
            (this.speed + this.pass + this.dribble + this.teamWork)                   *PowerConstants.TOP_STAT_MULTIPLIER   /PowerConstants.WM_WEIGHT +
            ( this.stamina + this.shoot + this.tackle + this.sense + this.positioning)*PowerConstants.MID_STAT_MULTIPLIER   /PowerConstants.WM_WEIGHT +
            (this.strength + this.composure)                                          *PowerConstants.BOTTOM_STAT_MULTIPLIER/PowerConstants.WM_WEIGHT
        );
    }

    public byte sumOfSTPower(){
        return (byte) (
            (this.strength + this.speed + this.shoot + this.composure + this.sense + this.positioning)*PowerConstants.TOP_STAT_MULTIPLIER   /PowerConstants.ST_WEIGHT +
            (this.dribble)                                                                            *PowerConstants.MID_STAT_MULTIPLIER   /PowerConstants.ST_WEIGHT +
            (this.stamina + this.pass + this.tackle + this.teamWork)                                  *PowerConstants.BOTTOM_STAT_MULTIPLIER/PowerConstants.ST_WEIGHT
        );
    }


}
