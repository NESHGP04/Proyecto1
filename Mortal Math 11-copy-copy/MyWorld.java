import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class MyWorld extends World
{
    public MyWorld()
    {    
        super(600, 400, 1); 
        GreenfootImage text = new GreenfootImage("Hola mundo", 24, Color.BLACK, Color.WHITE);
        getBackground().drawImage(text, 100, 100);
    }
}

