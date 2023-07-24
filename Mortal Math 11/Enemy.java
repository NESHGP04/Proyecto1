import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int animDuration = 100;
    private boolean isGettingHit = false;
    private boolean isAttacking = false;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private boolean victory = false;
    private int speed = 20;
    
    public void act()
    {
        if(victory){
            winAnimation();
            return;
        }
        
        if(isGettingHit)
        damage();
        
        if(isAttacking)
        attack();
        
        moveLeftRight();
    }
    
    public void moveLeftRight(){
        if(getX() <= 1280 && moveRight == true)
            setLocation(getX()+speed,getY());
        
        if(getX() == 1280)
            setImage("empty.png");
        
        if(getX() >= 768 && moveLeft == true)
            setLocation(getX()-speed,getY());
    }
    
    public void toggleDamage(){
        isGettingHit = true;
    }
    
    public void damage(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage("EHitted.png");
                this.setImage(hitImage);
                moveRight = true;
                break;
            case 50:
                moveRight = false;
                GreenfootImage idleImage = new GreenfootImage("Character2.png");
                this.setImage(idleImage);
                moveLeft = true;
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            isGettingHit = false;
            moveLeft = false;
            animDuration = 100;
        }        
    }
    
    public void toggleAttack(){
        isAttacking = true;
    }
    
    public void attack(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage("EPunching.png");
                this.setLocation(681, 496);
                this.setImage(hitImage);
                break;
            case 50:
                GreenfootImage idleImage = new GreenfootImage("Character2.png");
                this.setLocation(768, 496);
                this.setImage(idleImage);
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            isAttacking = false;
            animDuration = 100;
        } 
    }
    
    public void win(){
        victory = true;
    }
    
    public void winAnimation(){
        switch(animDuration){
            case 100:
                GreenfootImage punchImage = new GreenfootImage("EPunching.png");
                setImage(punchImage);
                break;
            case 50:
                GreenfootImage winImage = new GreenfootImage("EVictory.png");
                setImage(winImage);
                break;
        }
        
        animDuration--;
    }
}
