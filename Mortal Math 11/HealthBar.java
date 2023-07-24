import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private String[] imagesBar = {"Bar100.png","Bar80.png","Bar60.png","Bar40.png","Bar20.png","Bar0.png"};
    private int damage;
    
    public void act()
    {
        
    }
    
    public void damage(){
        damage++;
        
        if(damage < imagesBar.length)
        {
            GreenfootImage img = new GreenfootImage(imagesBar[damage]);
            this.setImage(img);
        }
   
    }
}
