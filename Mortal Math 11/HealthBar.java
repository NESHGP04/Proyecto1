import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private String[] imagesBar1 = {"Bar100.png","Bar80.png","Bar60.png","Bar40.png","Bar20.png","Bar0.png"};
    private String[] imagesBar2 = {"Bar100-P2.png","Bar80-P2.png","Bar60-P2.png","Bar40-P2.png","Bar20-P2.png","Bar0-P2.png"};
    private String[] imagesBar;
    private int damage;
    private int barIndex;
    
    public HealthBar(int index){
        this.barIndex = index;
        if(barIndex == 1)
        imagesBar = imagesBar1;
        else
        imagesBar = imagesBar2;
        
        GreenfootImage img = new GreenfootImage(imagesBar[0]);
        this.setImage(img);
    }
    
    public void damage(){
        damage++;
        
        if(damage < imagesBar.length)
        {
            GreenfootImage img = new GreenfootImage(imagesBar[damage]);
            this.setImage(img);
        }
    }
    
    public void setDamage(int damage)
    {
        this.damage = damage;
    }
}
