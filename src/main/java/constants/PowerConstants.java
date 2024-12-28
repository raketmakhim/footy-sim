package constants;

public class PowerConstants {
    public static byte CB_TOP_STATS = 5, CB_MID_STATS = 2, CB_BOTTOM_STATS = 4;
    public static byte FB_TOP_STATS = 4, FB_MID_STATS = 4, FB_BOTTOM_STATS = 3;
    public static byte CM_TOP_STATS = 4, CM_MID_STATS = 7, CM_BOTTOM_STATS = 0;
    public static byte WM_TOP_STATS = 4, WM_MID_STATS = 5, WM_BOTTOM_STATS = 2;
    public static byte ST_TOP_STATS = 6, ST_MID_STATS = 1, ST_BOTTOM_STATS = 4;

    //multiplyer
    public static byte TOP_STAT_MULTIPLIER = 6,MID_STAT_MULTIPLIER = 2,BOTTOM_STAT_MULTIPLIER = 1;

    //weight
    public static byte CB_WEIGHT = calculateWeight(CB_TOP_STATS, CB_MID_STATS, CB_BOTTOM_STATS);
    public static byte FB_WEIGHT = calculateWeight(FB_TOP_STATS, FB_MID_STATS, FB_BOTTOM_STATS);
    public static byte CM_WEIGHT = calculateWeight(CM_TOP_STATS, CM_MID_STATS, CM_BOTTOM_STATS);
    public static byte WM_WEIGHT = calculateWeight(WM_TOP_STATS, WM_MID_STATS, WM_BOTTOM_STATS);
    public static byte ST_WEIGHT = calculateWeight(ST_TOP_STATS, ST_MID_STATS, ST_BOTTOM_STATS);

    private static byte calculateWeight(byte topStats, byte midStats, byte bottomStats){
        return (byte) (topStats*TOP_STAT_MULTIPLIER + midStats*MID_STAT_MULTIPLIER + bottomStats*BOTTOM_STAT_MULTIPLIER);
    }
}
