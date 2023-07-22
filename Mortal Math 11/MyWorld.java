import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    Operations operations = new Operations();
    Number num1_1 = new Number();
    Number num1_2 = new Number();
    Number operator = new Number();
    Number num2_1 = new Number();
    Number num2_2 = new Number();
    
    String[] imagesNames = {"0.png","1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png","9.png"};
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        setBackground("fondoTraining.jpg");
        posicionarObjetos();
        changeNumbers();
    }
    
    private void changeNumbers(){
        operations.asignarValores();
        
        int number1_1 = operations.getNum1();
        GreenfootImage imageNumber1 = new GreenfootImage(imagesNames[number1_1]); 
        num1_1.setImage(imageNumber1);
        
        int operatorSymbol = operations.getOperator();
        switch(operatorSymbol)
        {
            case 1:
               GreenfootImage imagePlusSymbol = new GreenfootImage("+.png");
               operator.setImage(imagePlusSymbol);
               break;
            case 2:
               GreenfootImage imageMinusSymbol = new GreenfootImage("_.png");
               operator.setImage(imageMinusSymbol);
               break;
            case 3:
               GreenfootImage imageProductSymbol = new GreenfootImage("X.png");
               operator.setImage(imageProductSymbol);
               break;
            case 4:
               GreenfootImage imageDivisionrSymbol = new GreenfootImage("div.png");
               operator.setImage(imageDivisionrSymbol);
               break;
        }
        
        int number2_1 = operations.getNum2();
        GreenfootImage imageNumber2 = new GreenfootImage(imagesNames[number2_1]); 
        num2_1.setImage(imageNumber2);
        
    }
    
    private void posicionarObjetos()
    {
        //POSICIONES DE OBJETOS:
        //OPERATIONS:
        //x = 925
        //y = 200
        //
        //NUMEROS:
        //num 1.1: 758, 192
        //num 1.2: 838, 192
        //operacion: 927, 192
        //num 2.1: 1011, 192
        //num 2.2: 1086, 192
        //
        //TIMER: 634, 203
        //PLAYER: 355, 496
        //ENEMY: 902, 496
        //
        //ANSWER BAR: 320, 200
        //ans 1:252,192
        //ans 2:330,192
        //ans 3:406,192
        
        addObject(operations,925,200); //Se agrega la barra de operaciones
        //Se posicionan los numeros
        addObject(num1_1,844,192);
        //addObject(num1_2,838,192);
        addObject(operator,927,192);
        addObject(num2_1,1011,192);
        //addObject(num2_2,1086,192);
        
        addObject(new Timer(),634,203);//Timer
        
        addObject(new Player(),355,496);//Jugador
        addObject(new Enemy(),902,496);//Enemigo
        
        addObject(new AnswerBar(),320,200);
        addObject(new Answer(),252,192);
        addObject(new Answer(),330,192);
        addObject(new Answer(),406,192);
    }
}
