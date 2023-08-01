import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextLabel extends Actor
{
    public TextLabel(String text, int color){
        GreenfootImage textImg = new GreenfootImage(text, 40,Color.BLACK, new Color(0, 0, 0, 0));
        switch(color){
            case 1:
                textImg = new GreenfootImage(text, 40,Color.BLACK, new Color(0, 0, 0, 0));
                setImage(textImg); 
                break;
            case 2:
                textImg = new GreenfootImage(text, 40,Color.BLUE, new Color(0, 0, 0, 0));
                setImage(textImg); 
                break;
            case 3:
                textImg = new GreenfootImage(text, 40,new Color(102, 204, 0, 255), new Color(0, 0, 0, 0));
                setImage(textImg); 
                break;
        }
    }
    
    public void setText(String text, int color){
        GreenfootImage textImg = new GreenfootImage(text, 40,Color.WHITE, new Color(0, 0, 0, 0));
        switch(color){
            case 1:
                textImg = new GreenfootImage(text, 40,Color.BLACK, new Color(0, 0, 0, 0));
                setImage(textImg); 
                break;
            case 2:
                textImg = new GreenfootImage(text, 40,Color.BLUE, new Color(0, 0, 0, 0));
                setImage(textImg); 
                break;
            case 3:
                textImg = new GreenfootImage(text, 40,new Color(0, 204, 0, 255), new Color(0, 0, 0, 0));
                setImage(textImg); 
                break;
        }

    }
}
