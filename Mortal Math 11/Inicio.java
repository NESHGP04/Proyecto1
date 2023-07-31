import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inicio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inicio extends World
{
    private Button playButton = new Button("bee.png",new MyWorld());
    private Button coopButton = new Button("salir.png",new MyWorldCoop());
    private Button versusButton= new Button("vs.png",new MyWorldVersus());
    public Inicio()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        addObject(playButton,150,100);
        addObject(versusButton,150,500);
        addObject(coopButton,300,500);
    }
}
