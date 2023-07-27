import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TwoPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TwoPlayer extends Actor
{
    private int playerIndex = 0;
    private int health = 5;
    private int animDuration = 100;
    private boolean isGettingHit = false;
    private boolean isAttacking = false;
    private boolean bothSucceded = false;
    private boolean bothFailed = false;
    private boolean defeated = false;
    
    private String[] player1Anims = {"Character1-s.png","Punching-s.png","Hitted-s.png","Recovering-s.png","Defeated-s.png","Victory-s.png"}; 
    private String[] player2Anims = {"2P-s.png","2PPunching-s.png","2PHitted-s.png","2PRecovering-s.png","2PDefeated-s.png","2PVictory-s.png"}; 
    private String[] playerAnims = {"","","","","",""}; 
    /*
    Idle = 0
    Punching = 1
    Hitted = 2
    Recovering = 3
    Defeated = 4
    Victory = 5
    */
    
    public TwoPlayer(int playerIndex)
    {
        this.playerIndex = playerIndex;
        if(this.playerIndex == 1)
            playerAnims = player1Anims;
        else
            playerAnims = player2Anims;
    }
    
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
        
        if(bothSucceded)
        bothSucced();
        
        if(bothFailed)
        bothFail();
    }
    
    public void toggleDamage(){
        isGettingHit = true;
        health--;
    }
    
    public void damage(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(playerAnims[2]);
                setImage(hitImage);
                break;
            case 50:
                GreenfootImage recoverImage = new GreenfootImage(playerAnims[3]);
                setImage(recoverImage);
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            GreenfootImage idleImage = new GreenfootImage(playerAnims[0]);
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
                GreenfootImage punchImage = new GreenfootImage(playerAnims[1]);
                if(playerIndex == 1)
                    setLocation(getX()+50, getY());
                else
                    setLocation(getX()-50, getY());
                setImage(punchImage);
                break;
            case 50:
                GreenfootImage idleImage = new GreenfootImage(playerAnims[0]);
                if(playerIndex == 1)
                    setLocation(getX()-50, getY());
                else
                    setLocation(getX()+50, getY());
                setImage(idleImage);
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            isAttacking = false;
            animDuration = 100;
        } 
    }
    
    public void toggleBothSucced(){
        bothSucceded = true;
    }
    
    public void bothSucced(){
        switch(animDuration){
            case 100:
                GreenfootImage punchImage = new GreenfootImage(playerAnims[1]);
                setImage(punchImage);
                break;
            case 50:
                GreenfootImage victoryImage = new GreenfootImage(playerAnims[4]);
                setImage(victoryImage);
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            GreenfootImage idleImage = new GreenfootImage(playerAnims[0]);
            setImage(idleImage);
            bothSucceded = false;
            animDuration = 100;
        } 
    }
    
    public void toggleBothFail(){
        bothFailed = true;
    }
    
    public void bothFail(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(playerAnims[2]);
                setImage(hitImage);
                break;
            case 50:
                GreenfootImage recoverImage = new GreenfootImage(playerAnims[3]);
                setImage(recoverImage);
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            GreenfootImage idleImage = new GreenfootImage(playerAnims[0]);
            setImage(idleImage);
            bothFailed = false;
            animDuration = 100;
        } 
    }
    
    public void lose(){
        defeated = true;
    }
    
    public void loseAnimation(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(playerAnims[2]);
                setImage(hitImage);
                break;
            case 50:
                GreenfootImage defeatImage = new GreenfootImage(playerAnims[4]);
                setImage(defeatImage);
                setLocation(getX()-20,getY());
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
