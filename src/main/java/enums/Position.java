package enums;

public enum Position  {
    GK, LB, CB, RB, RM, CM, LM, ST;

    public boolean isDefensive() {
        return switch (this) {
            case GK, LB, CB, RB -> true;
            default -> false;
        };
    }

    public boolean isOffensive() {
        return switch (this) {
            case RM, LM, ST -> true;
            default -> false;
        };
    }

    public boolean isMidfield() {
        return this == CM;
    }
}



