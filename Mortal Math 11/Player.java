import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int health = 5;
    private int animDuration = 100;
    private boolean isGettingHit = false;
    private boolean isAttacking = false;
    private boolean defeated = false;
    
    public void act()
    {
        if(defeated){
            loseAnimation();
            return;
        }
        
        if(isGettingHit)
        damage();
        
        if(isAttacking)
        attack();
    }
    
    public void toggleDamage(){
        isGettingHit = true;
        health--;
    }
    
    public void damage(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage("Hitted.png");
                setImage(hitImage);
                break;
            case 50:
                GreenfootImage recoverImage = new GreenfootImage("Recovering.png");
                setImage(recoverImage);
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            GreenfootImage idleImage = new GreenfootImage("Character1.png");
            setImage(idleImage);
            isGettingHit = false;
            animDuration = 100;
        }        
    }
    
    public void toggleAttack(){
        isAttacking = true;
    }
    
    public void attack(){
        switch(animDuration){
            case 100:
                GreenfootImage punchImage = new GreenfootImage("Punching.png");
                setLocation(510, 496);
                setImage(punchImage);
                break;
            case 50:
                GreenfootImage idleImage = new GreenfootImage("Character1.png");
                setLocation(488, 496);
                setImage(idleImage);
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            isAttacking = false;
            animDuration = 100;
        } 
    }
    
    public void lose(){
        defeated = true;
    }
    
    public void loseAnimation(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage("Hitted.png");
                setImage(hitImage);
                break;
            case 50:
                GreenfootImage defeatImage = new GreenfootImage("Defeated.png");
                setImage(defeatImage);
                break;
        }
        
        animDuration--;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public int getHealth(){
        return health;
    }
}
