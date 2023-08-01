import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inicio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inicio extends World
{
    private Button playButton = new Button("SolitarioButton.png",new MyWorld());
    private Button coopButton = new Button("CoopButton.png",new MyWorldCoop());
    private Button versusButton= new Button("VersusButton.png",new MyWorldVersus());
    
    private GreenfootSound backgroundMusic = new GreenfootSound("fight.wav");
    private int musicPlay = 0;
    
    public Inicio()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        setBackground("TitleScreen.jpg");
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
            modeSelect();
            
        musicPlay++;
        
        if(musicPlay <= 1)
        {
            backgroundMusic.setVolume(40);
            backgroundMusic.play();
            backgroundMusic.playLoop();
        }
    }
    
    public void modeSelect()
    {
        setBackground("TitleScreen-ModeSelect.jpg");
        addObject(playButton,640,400);
        addObject(versusButton,280,400);
        addObject(coopButton,1000,400);
    }
    
}
