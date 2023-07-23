import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Equations extends World
{
    private int timer = 5;

    public Equations()
    {    
        super(600, 400, 1); 
        showText("Comenzando...", 200, 200);
    }
    
    public void act()
    {
        timer--;
        if(timer <= 0){
            showText("Ya paso el tiempo", 200, 200);
            timer = 5;
        }
    }
}
