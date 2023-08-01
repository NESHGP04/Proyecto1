import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private boolean isGettingHit = false;
    private boolean isAttacking = false;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private boolean victory = false;
    
    private int animDuration = 100;
    private int speed = 20;
    private int index;
    
    private float x,y;
    
    private String[] animImages1 = {"Character2.png","EPunching.png","EHitted.png","EVictory.png"}; 
    private String[] animImages2 = {"Character2-s.png","EPunching-s.png","EHitted-s.png","EVictory-s.png"};
    private String[] animImages3 = {"LCharacter2-s.png","LEPunching-s.png","LEHitted-s.png","EVictory-s.png"};
    private String[] anims;
    
    public Enemy(int index, int x, int y){
        this.index = index;
        
        switch(this.index){
            case 1:
                anims = animImages1;
                break;
            case 2:
                anims = animImages2;
                break;
            case 3:
                anims = animImages3;
                break;
        }
        
        this.x = x;
        this.y = y;
    }
    
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
        
        if(index == 1 || index == 2){
            if(getX() <= 1280 && moveRight == true)
            setLocation(getX()+speed,getY());
        
            if(getX() >= x && moveLeft == true)
            setLocation(getX()-speed,getY());
        }
        else{
            if(getX() >= 0 && moveLeft == true)
            setLocation(getX()-speed,getY());
        
            if(getX() <= x && moveRight == true)
            setLocation(getX()+speed,getY());
        }
        
    }
    
    public void toggleDamage(){
        isGettingHit = true;
    }
    
    public void damage(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(anims[2]);
                this.setImage(hitImage);
                if(index == 1 || index == 2)
                    moveRight = true;
                else
                    moveLeft = true;
                break;
            case 50:
                GreenfootImage idleImage = new GreenfootImage(anims[0]);
                this.setImage(idleImage);
                if(index == 1 || index == 2){
                    moveRight = false;
                    moveLeft = true;
                }
                else{
                    moveLeft = false;
                    moveRight = true;
                }
                    
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            isGettingHit = false;
            moveLeft = false;
            moveRight = false;
            animDuration = 100;
        }        
    }
    
    public void toggleAttack(){
        isAttacking = true;
    }
    
    public void attack(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(anims[1]);
                switch(index){
                    case 1:
                        this.setLocation(681, 496);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                this.setImage(hitImage);
                break;
            case 50:
                GreenfootImage idleImage = new GreenfootImage(anims[0]);
                switch(index){
                    case 1:
                        this.setLocation(768, 496);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
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
                GreenfootImage punchImage = new GreenfootImage(anims[1]);
                setImage(punchImage);
                break;
            case 50:
                GreenfootImage winImage = new GreenfootImage(anims[3]);
                setImage(winImage);
                break;
        }
        
        animDuration--;
    }
    
    public void unvictory()
    {
        victory = false;
        isAttacking = false;
        isGettingHit = true;
    }
}
