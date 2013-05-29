package mining;

public class UTIL {
	//returns x per hour (ie. exp or ores mines)
	public static int getPerHour(final int value){
		return (int)(value * (3600000 / VARS.timeRunning));
	}
}
