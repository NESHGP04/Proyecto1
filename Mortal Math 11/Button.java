import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Botton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the Botton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private World link;
    public Button(String image, World link){    //Button playButton = new Button("play.png",new MyWorld());
        GreenfootImage buttonImg = new GreenfootImage(image);
        setImage(buttonImg);
        this.link = link;
        
    }
    public void act()
    {
        // Add your action code here.
        if (Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(this.link);
        
        }
    }
}
