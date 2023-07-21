import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Operations extends Actor
{
    private int timer = 250;
    
    //Imagenes del Timer.
    private GreenfootImage imageTimer1 = new GreenfootImage("Timer1.png");
    private GreenfootImage imageTimer2 = new GreenfootImage("Timer2.png");
    private GreenfootImage imageTimer3 = new GreenfootImage("Timer3.png");
    private GreenfootImage imageTimer4 = new GreenfootImage("Timer4.png");
    private GreenfootImage imageTimer5 = new GreenfootImage("Timer5.png");
    private GreenfootImage imageTimer6 = new GreenfootImage("Timer6.png");
    
    public void act()
    {
        //Temporizador de 5 segundos
        switch(timer)
        {
            case 200:
                setImage(imageTimer2);
                break;
            case 150:
                setImage(imageTimer3);
                break;
            case 100:
                setImage(imageTimer4);
                break;
            case 50:
                setImage(imageTimer5);
                break;
            case 0:
                setImage(imageTimer6);
                break;
        }
        
        timer--;
        
        if(timer <= -25){
            timer = 250;
            setImage(imageTimer1);
        }
    }
}
