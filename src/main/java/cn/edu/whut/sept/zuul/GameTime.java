package cn.edu.whut.sept.zuul;

public class GameTime
{
    private int day = 1;
    private int hour = 8;

    public void tick()
    {
        hour += 1;

        if (hour >= 24) {
            hour = 0;
            day++;
        }
    }

    public String getTime()
    {
        return "Day " + day + " - " + (hour < 12 ? "Morning" : "Evening") + " (" + hour + ":00)";
    }
}
