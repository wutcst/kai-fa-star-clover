package cn.edu.whut.sept.zuul;

public class Achievement
{
    private String name;
    private boolean unlocked;

    public Achievement(String name)
    {
        this.name = name;
        this.unlocked = false;
    }

    public void unlock()
    {
        if (!unlocked) {
            unlocked = true;
            System.out.println("🏆 Achievement unlocked: " + name);
        }
    }

    public boolean isUnlocked()
    {
        return unlocked;
    }
}