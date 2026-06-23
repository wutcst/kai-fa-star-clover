package cn.edu.whut.sept.zuul;

public class Quest
{
    private String name;
    private String description;
    private boolean completed;
    private String rewardItem;

    public Quest(String name, String description, String rewardItem)
    {
        this.name = name;
        this.description = description;
        this.rewardItem = rewardItem;
        this.completed = false;
    }

    public String getName()
    {
        return name;
    }

    public void show()
    {
        System.out.println("Quest: " + name);
        System.out.println(description);
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public String getReward()
    {
        return rewardItem;
    }

    public void complete()
    {
        completed = true;
    }
}
