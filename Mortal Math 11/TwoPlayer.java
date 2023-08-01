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
    private boolean victory = false;
    private boolean tie = false;
    
    private String[] player1Anims = {"Character1-s.png","Punching-s.png","Hitted-s.png","Recovering-s.png","Defeated-s.png","Victory-s.png","TiePunch-s.png","Defend-s.png"}; 
    private String[] player2Anims = {"2P-s.png","2PPunching-s.png","2PHitted-s.png","2PRecovering-s.png","2PDefeated-s.png","2PVictory-s.png","2PTiePunch-s.png","2PDefend-s.png"}; 
    private String[] playerAnims = {"","","","","",""}; 
    /*
    Idle = 0
    Punching = 1
    Hitted = 2
    Recovering = 3
    Defeated = 4
    Victory = 5
    TiePunch = 6
    Defend = 7
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
        
        if(victory){
            winAnimation();
            return;
        }
        
        if(tie){
            tieAnimation();
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
        Greenfoot.playSound("Gets-hit.wav"); //Hitting sound
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
        Greenfoot.playSound("Punch.wav"); //Punching sound
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
        Greenfoot.playSound("Win.wav"); //Winning sound
    }
    
    public void bothSucced(){
        switch(animDuration){
            case 100:
                if(playerIndex == 1){
                    GreenfootImage firstImage = new GreenfootImage(playerAnims[7]);
                    setLocation(getX()-30, getY());
                    setImage(firstImage);
                }
                else{
                    GreenfootImage firstImage = new GreenfootImage(playerAnims[1]);
                    setLocation(getX(), getY()-50);
                    setImage(firstImage);
                }  
                break;
            case 50:
                if(playerIndex == 1){
                    GreenfootImage firstImage = new GreenfootImage(playerAnims[1]);
                    setLocation(getX(), getY()-30);
                    setImage(firstImage);
                }
                else{
                    GreenfootImage firstImage = new GreenfootImage(playerAnims[7]);
                    setLocation(getX()+30, getY()+50);
                    setImage(firstImage);
                }  
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            GreenfootImage idleImage = new GreenfootImage(playerAnims[0]);
            setImage(idleImage);
            bothSucceded = false;
            animDuration = 100;
            if(playerIndex == 1)
                    setLocation(getX()+30, getY()+30);
                else
                    setLocation(getX()-30, getY());
        } 
    }
    
    public void toggleBothFail(){
        bothFailed = true;
        health--;
    }
    
    public void bothFail(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(playerAnims[6]);
                setImage(hitImage);
                if(playerIndex == 1)
                    setLocation(getX()+100, getY());
                else
                    setLocation(getX()-100, getY());
                break;
            case 50:
                GreenfootImage recoverImage = new GreenfootImage(playerAnims[3]);
                setImage(recoverImage);
                if(playerIndex == 1)
                    setLocation(getX()-150, getY());
                else
                    setLocation(getX()+150, getY());
                break;
        }
        
        animDuration--;
        
        if(animDuration <= 0){
            if(playerIndex == 1)
                    setLocation(getX()+50, getY());
                else
                    setLocation(getX()-50, getY());
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
                GreenfootImage recoverImage = new GreenfootImage(playerAnims[4]);
                setImage(recoverImage);
                if(playerIndex == 1)
                    setLocation(getX()-50, getY());
                else
                    setLocation(getX(), getY());
                break;
        }
        
        animDuration--;
    }
    
    public void win(){
        victory = true;
    }
    
    public void winAnimation(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(playerAnims[1]);
                setImage(hitImage);
                if(playerIndex == 1)
                    setLocation(getX()+50, getY());
                else
                    setLocation(getX()-50, getY());
                break;
            case 50:
                GreenfootImage defeatImage = new GreenfootImage(playerAnims[5]);
                setImage(defeatImage);
                if(playerIndex == 1)
                    setLocation(getX()-50, getY());
                else
                    setLocation(getX()+50, getY());
                break;
        }
        
        animDuration--;
    }
    
    public void tie(){
        tie = true;
    }
    
    public void tieAnimation(){
        switch(animDuration){
            case 100:
                GreenfootImage hitImage = new GreenfootImage(playerAnims[6]);
                setImage(hitImage);
                if(playerIndex == 1)
                    setLocation(getX()+100, getY());
                else
                    setLocation(getX()-100, getY());
                break;
            case 50:
                GreenfootImage recoverImage = new GreenfootImage(playerAnims[4]);
                setImage(recoverImage);
                if(playerIndex == 1)
                    setLocation(getX()-150, getY());
                else
                    setLocation(getX()+150, getY());
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
    
    public void undefeat()
    {
        defeated = false;
    }
}
