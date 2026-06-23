package cn.edu.whut.sept.zuul;

public class Enemy
{
    private String name;
    private int hp;
    private int attack;

    public Enemy(String name, int hp, int attack)
    {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public String getName()
    {
        return name;
    }

    public int getHp()
    {
        return hp;
    }

    public void damage(int d)
    {
        hp -= d;
    }

    public boolean isDead()
    {
        return hp <= 0;
    }

    public int getAttack()
    {
        return attack;
    }
}