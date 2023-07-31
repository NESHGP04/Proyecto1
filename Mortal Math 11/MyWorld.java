import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;

public class MyWorld extends World
{
    private Timer timer = new Timer();
    private HealthBar healthBar = new HealthBar(1);
    private StartingCountdown startingCountdown = new StartingCountdown();
    
    private Operations operations = new Operations();
    private Number num1_1 = new Number();
    private Number num1_2 = new Number();
    private Number operator = new Number();
    private Number num2_1 = new Number();
    private Number num2_2 = new Number();
    
    private int timerCount = 150;
    private int startTimer = 200;
    private int transitionTimer = 100;
    private int numberPosition = 0;
    private int playerAnswer = 0;
    private int enemiesDefeated = 0;
    private int[] keyRepeat = {0,0,0,0,0,0,0,0,0,0,0,0};
    
    private boolean beginGame = false;
    private boolean gameOver = false;
    private boolean transitioning = false;
    private boolean keyPressed = false;
    
    private Player player = new Player();
    private Enemy enemy = new Enemy(1, 768, 496);
    
    private Answer ans1 = new Answer();
    private Answer ans2 = new Answer();
    private AnswerBar answerBar = new AnswerBar();
    private Answer[] ansList = {ans1,ans2};
    
    private String[] answers = {"","",""};
    
    private String[] imagesNames = {"0.png","1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png","9.png"}; //Imagenes de los numeros
    private String[] imagesTimer = {"Timer1.png","Timer2.png","Timer3.png","Timer4.png","Timer5.png","Timer6.png"}; //Imagenes del timer
    private String[] imagesStartingTimer = {"start3.png","start2.png","start1.png","startFight.png"};
    
    private TextLabel textLabel = new TextLabel("Enemies defeated: "+enemiesDefeated,1);
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        setBackground("fondoTraining.jpg");
    }
    
    public void act(){
        if(gameOver){
            return;
        }
        
        if(!beginGame){
            startingCountdown();
            return;
        }
                        
        if(!transitioning){
            userKeyboardInput();
            countdownOriginal();
        }  
        
        if(transitioning)
            countdownTransition();
            
        if(player.getHealth() <= 0)
        {
            removeObject(textLabel);
            showEnemiesDefeated("Enemies defeated: ",640,220);
            gameOver();
        }
            
            
        if(keyPressed == false){
            for(int i = 0; i <= 11; i++)
                keyRepeat[i] = 0;
        }   
    }
    
    public void showEnemiesDefeated(String theText, int x, int y){
        addObject(textLabel, x, y);
        textLabel.setText(theText+enemiesDefeated,1);
    }
    
    public void startingCountdown(){
        addObject(startingCountdown,640,360);
            switch(startTimer)
            {
            case 150:
                GreenfootImage imageStartTimer2 = new GreenfootImage(imagesStartingTimer[1]);
                startingCountdown.setImage(imageStartTimer2);
                break;
            case 100:
                GreenfootImage imageStartTimer3 = new GreenfootImage(imagesStartingTimer[2]);
                startingCountdown.setImage(imageStartTimer3);
                break;
            case 50:
                GreenfootImage imageStartTimer4 = new GreenfootImage(imagesStartingTimer[3]);
                startingCountdown.setImage(imageStartTimer4);
                break;
            }
        
            startTimer--;
        
            if(startTimer <= 0){
                removeObject(startingCountdown);
                beginGame = true;
                posicionarObjetos();
                changeNumbers();
            }
    }
    
    public void countdownOriginal(){
        if(transitioning)
        return;
        
        switch(timerCount)
        {
            case 120:
                GreenfootImage imageTimer2 = new GreenfootImage(imagesTimer[1]);
                timer.setImage(imageTimer2);
                break;
            case 90:
                GreenfootImage imageTimer3 = new GreenfootImage(imagesTimer[2]);
                timer.setImage(imageTimer3);
                break;
            case 60:
                GreenfootImage imageTimer4 = new GreenfootImage(imagesTimer[3]);
                timer.setImage(imageTimer4);
                break;
            case 30:
                GreenfootImage imageTimer5 = new GreenfootImage(imagesTimer[4]);
                timer.setImage(imageTimer5);
                break;
            case 0:
                GreenfootImage imageTimer6 = new GreenfootImage(imagesTimer[5]);
                timer.setImage(imageTimer6);
                break;
        }
        
        timerCount--;
        
        if(timerCount <= 0){
            GreenfootImage imageTimer6 = new GreenfootImage(imagesTimer[5]);
            timer.setImage(imageTimer6);
            compareResults();
            transitioning = true;
        }
    }
    
    public void countdownTransition(){
        transitionTimer --;
        
        if(transitionTimer <= 0){
            countdownReset();
            transitionTimer = 100;
            transitioning = false;
        }
    }
    
    public void countdownReset(){
        timerCount = 150;
        GreenfootImage imageTimer1 = new GreenfootImage(imagesTimer[0]);
        timer.setImage(imageTimer1);
        changeNumbers();
        resetNumbers();
    }
    
    public void userKeyboardInput(){
        if(Greenfoot.isKeyDown("1")){
            keyPressed = true;
            
            keyRepeat[1]++;
            
            if(keyRepeat[1] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[1]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "1";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("2")){
            keyPressed = true;
            
            keyRepeat[2]++;
            
            if(keyRepeat[2]<= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[2]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "2";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("3")){
            keyPressed = true;
            
            keyRepeat[3]++;
            
            if(keyRepeat[3] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[3]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "3";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("4")){
            keyPressed = true;
            
            keyRepeat[4]++;
            
            if(keyRepeat[4] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[4]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "4";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("5")){
            keyPressed = true;
            
            keyRepeat[5]++;
            
            if(keyRepeat[5] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[5]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "5";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("6")){
            keyPressed = true;
            
            keyRepeat[6]++;
            
            if(keyRepeat[6] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[6]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "6";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("7")){
            keyPressed = true;
            
            keyRepeat[7]++;
            
            if(keyRepeat[7] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[7]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "7";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("8")){
            keyPressed = true;
            
            keyRepeat[8]++;
            
            if(keyRepeat[8] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[8]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "8";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("9")){
            keyPressed = true;
            
            keyRepeat[9]++;
            
            if(keyRepeat[9] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[9]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "9";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("0")){
            keyPressed = true;
            
            keyRepeat[0]++;
            
            if(keyRepeat[0] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNames[0]);
            
                if(numberPosition <= 1)
                {
                    ansList[numberPosition].setImage(img);
                    answers[numberPosition] = "0";
                    numberPosition++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("enter")){
            keyPressed = true;
            
            keyRepeat[10]++;
            
            if(keyRepeat[10] <= 1){
                if(answers[0] != ""){
                    compareResults();
                    resetNumbers();
                }
            }
        }
        else if(Greenfoot.isKeyDown("backspace")){
            keyPressed = true;
            
            keyRepeat[11]++;
            
            if(keyRepeat[11] <= 1){
            GreenfootImage less = new GreenfootImage("underscore.png");
                if(numberPosition > 0)
                {
                    numberPosition--;
                    ansList[numberPosition].setImage(less);
                    answers[numberPosition] = "";
                }
            }
        }
        else{
            keyPressed = false;
            
        }
    }
    
    public void resetNumbers(){
        numberPosition = 0;
        
        GreenfootImage less = new GreenfootImage("underscore.png");
        ansList[0].setImage(less);
        ansList[1].setImage(less);
        
        answers[0] = "";
        answers[1] = "";
    }
    
    public void compareResults(){
        int correctAnswer = operations.getResult();
        
        if(answers[0] != "")
        {
        String playerAnswerString = answers[0]+answers[1];
        playerAnswer = Integer.parseInt(playerAnswerString);        
        }
        else
        playerAnswer = 0;
        
        if(playerAnswer != correctAnswer || answers[0] == ""){//Incorrecto
            healthBar.damage();
            player.toggleDamage();
            enemy.toggleAttack();
            transitioning = true;
        }
        else{//Correcto
            player.toggleAttack();
            enemy.toggleDamage();
            enemiesDefeated++;
            showEnemiesDefeated("Enemies defeated: ",841,45);
            transitioning = true;
        }
    }
    
    public void changeNumbers(){
        operations.operar();
        
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
               GreenfootImage imageProductSymbol = new GreenfootImage("div.png");
               operator.setImage(imageProductSymbol);
               break;
            case 4:
               GreenfootImage imageDivisionrSymbol = new GreenfootImage("X.png");
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
        
        addObject(timer,634,203);//Timer
        
        addObject(player,488,496);//Jugador
        addObject(enemy,768,496);//Enemigo
        
        addObject(answerBar,320,200);
        addObject(ans1,292,192);
        addObject(ans2,375,192);
        
        addObject(healthBar,286,54);
    }
    
    public void gameOver(){
        beginGame = false;
        gameOver = true;
        transitioning = false;
        player.lose();
        enemy.win();
        
        showText("",768,45);
        showEnemiesDefeated("Enemies defeated: ",520,220);
        addObject(startingCountdown,640,360);
        GreenfootImage gameOverScreen = new GreenfootImage("GameOverScreen.png");
        startingCountdown.setImage(gameOverScreen);
        
        removeObject(operations);
        removeObject(num1_1);
        removeObject(operator);
        removeObject(num2_1);
        removeObject(timer);
        removeObject(answerBar);
        removeObject(ans1);
        removeObject(ans2);
        removeObject(ans2);
        removeObject(healthBar);
    }
}
