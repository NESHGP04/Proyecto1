import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorldVersus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorldVersus extends World
{
    private Timer timer = new Timer();
    private HealthBar healthBarP1 = new HealthBar(1);
    private HealthBar healthBarP2 = new HealthBar(2);
    private StartingCountdown startingCountdown = new StartingCountdown();
    
    private Operations operations = new Operations();
    private Number num1_1 = new Number();
    private Number num1_2 = new Number();
    private Number operator = new Number();
    private Number num2_1 = new Number();
    private Number num2_2 = new Number();
    
    private int startTimer = 200;
    private int timerCount = 150;
    private int transitionTimer = 100;
    private int[] keyRepeatP1 = {0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] keyRepeatP2 = {0,0,0,0,0,0,0,0,0,0,0,0};
    private int numberPositionP1 = 0;
    private int numberPositionP2 = 0;
    private int player1Answer = 0;
    private int player2Answer = 0;
    
    private boolean beginGame = false;
    private boolean gameOver = false;
    private boolean transitioning = false;
    private boolean keyPressedP1 = false;
    private boolean keyPressedP2 = false;
    private boolean player1Win = false;
    private boolean player2Win = false;
    private boolean isATie = false;
    private boolean sentByP1 = false;
    private boolean sentByP2 = false;
    
    private TwoPlayer player1 = new TwoPlayer(1);
    private TwoPlayer player2 = new TwoPlayer(2);
    
    //Answer P1
    private Answer ans1P1 = new Answer();
    private Answer ans2P1 = new Answer();
    private AnswerBar answerBarP1 = new AnswerBar();
    private Answer[] ansListP1 = {ans1P1,ans2P1};
    private String[] answersP1 = {"","",""};
    
    //Answer P2
    private Answer ans1P2 = new Answer();
    private Answer ans2P2 = new Answer();
    private AnswerBar answerBarP2 = new AnswerBar();
    private Answer[] ansListP2 = {ans1P2,ans2P2};
    private String[] answersP2 = {"","",""};
    
    private String[] imagesNames = {"0-l.png","1-l.png","2-l.png","3-l.png","4-l.png","5-l.png","6-l.png","7-l.png","8-l.png","9-l.png"}; //Imagenes de los numeros
    private String[] imagesNamesAnswer = {"0-s.png","1-s.png","2-s.png","3-s.png","4-s.png","5-s.png","6-s.png","7-s.png","8-s.png","9-s.png"}; //Imagenes de los numeros
    private String[] imagesTimer = {"Timer1-s.png","Timer2-s.png","Timer3-s.png","Timer4-s.png","Timer5-s.png","Timer6-s.png"}; //Imagenes del timer
    private String[] imagesStartingTimer = {"start3.png","start2.png","start1.png","startFight.png"};
    
    public MyWorldVersus()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        setBackground("fondoTraining.jpg");

        //Adds background music
        GreenfootSound sound = new GreenfootSound("Syndrome-Mortal-Kombat-_Hard-Trance-Techno-Remix_.wav.crdownload");        
        sound.play();
        while (gameOver == true){
            sound.pause();
            GreenfootSound overSound = new GreenfootSound("Game-over.wav");
            overSound.play();
        }
    }
    
    public void act(){
        if(gameOver)
        return;
        
        if(!beginGame){
            startingCountdown();
            return;
        }
        
        if(!transitioning){
            player1Inputs();
            player2Inputs();
            countdownOriginal();
        }
        
        if(transitioning)
            countdownTransition();
        
        if(player1.getHealth() <= 0 && player2.getHealth() <= 0){ //Empate
            isATie = true;
            gameOver();
        }   
        else if(player1.getHealth() <= 0){ //Jugador 2 Gana
            player2Win = true;
            gameOver(); 
        }
        else if(player2.getHealth() <= 0){ //Jugador 1 gana
            player1Win = true;
            gameOver();
        }
        
        if(keyPressedP1 == false){
            for(int i = 0; i <= 11; i++)
                keyRepeatP1[i] = 0;
        }
        
        if(keyPressedP2 == false){
            for(int i = 0; i <= 11; i++)
                keyRepeatP2[i] = 0;
        }
    }
    
    public void startingCountdown(){
        addObject(startingCountdown,640,360);
            switch(startTimer)
            {
            case 150:
                GreenfootImage imageStartTimer2 = new GreenfootImage(imagesStartingTimer[1]);
                startingCountdown.setImage(imageStartTimer2);
                GreenfootSound sound = new GreenfootSound("Countdown.wav");
                sound.play();
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
    
    public void resetNumbers(){
        numberPositionP1 = 0;
        numberPositionP2 = 0;
        
        GreenfootImage less = new GreenfootImage("underscore-s.png");
        ansListP1[0].setImage(less);
        ansListP1[1].setImage(less);
        ansListP2[0].setImage(less);
        ansListP2[1].setImage(less);
        
        answersP1[0] = "";
        answersP1[1] = "";
        answersP2[0] = "";
        answersP2[1] = "";
    }
    
    private void posicionarObjetos()
    {
        addObject(operations,640,164); //Se agrega la barra de operaciones
        operations.setImage("OperationsBarVersus.png");
        //Se posicionan los numeros
        addObject(num1_1,580,164);
        //addObject(num1_2,838,192);
        addObject(operator,635,164);
        addObject(num2_1,686,164);
        //addObject(num2_2,1086,192);
        
        addObject(timer,634,330);//Timer
        timer.setImage("Timer1-s.png");
        
        addObject(player1,530,549);//Jugador
        addObject(player2,734,549);//Jugador 2
        player2.setImage("2P-s.png");
        
        addObject(answerBarP1,453,347);
        answerBarP1.setImage("AnswerBarP1-s.png");
        addObject(ans1P1,432,338);
        ans1P1.setImage("underscore-s.png");
        addObject(ans2P1,476,338);
        ans2P1.setImage("underscore-s.png");
        
        addObject(answerBarP2,815,347);
        answerBarP2.setImage("AnswerBarP2-s.png");
        addObject(ans1P2,799,338);
        ans1P2.setImage("underscore-s.png");
        addObject(ans2P2,842,338);
        ans2P2.setImage("underscore-s.png");
        
        addObject(healthBarP1,286,54);
        addObject(healthBarP2,994,54);
    }
    
    public void compareResults(){
        int correctAnswer = operations.getResult();
        boolean player1Correct = false;
        boolean player2Correct = false;
        transitioning = true;
        
        //Jugador 1
        if(answersP1[0] != "")
        {
        String player1AnswerString = answersP1[0]+answersP1[1];
        player1Answer = Integer.parseInt(player1AnswerString);        
        }
        else
        player1Answer = 0;
        
        if(sentByP1 == true && player1Answer != correctAnswer && player1Correct == false){
            player1Correct = false;
            player2Correct = true; 
            sentByP1 = false;
        }
        else if(player1Correct == false){
            if(player1Answer != correctAnswer || answersP1[0] == "" && player1Correct == true){//Incorrecto
            player1Correct = false;
            }
            else{//Correcto
            player1Correct = true;
            }
        }
        
        //Jugador 2
        if(answersP2[0] != "")
        {
        String player2AnswerString = answersP2[0]+answersP2[1];
        player2Answer = Integer.parseInt(player2AnswerString);        
        }
        else
        player2Answer = 0;
        
        if(sentByP2 == true && player2Answer != correctAnswer && player2Correct == false){
            player1Correct = true;
            player2Correct = false; 
            sentByP2 = false;
        }
        else if(player2Correct == false){
            if(player2Answer != correctAnswer || answersP2[0] == "" && player2Correct == true){//Incorrecto
            player2Correct = false;
            }
            else{//Correcto
            player2Correct = true;
            }
        }
        
        if(player1Correct && !player2Correct){ //Jugador 1 Gana
            healthBarP2.damage();
            player1.toggleAttack();
            player2.toggleDamage();
            transitioning = true;
        }
        else if(!player1Correct && player2Correct){ //Jugador 2 Gana
            healthBarP1.damage();
            player1.toggleDamage();
            player2.toggleAttack();
            transitioning = true;
        }
        else if(!player1Correct && !player2Correct){ //Empate de fallo
            healthBarP1.damage();
            healthBarP2.damage();
            player1.toggleBothFail();
            player2.toggleBothFail();
            transitioning = true;
        }
        else{ //Empate de exito
            player1.toggleBothSucced();
            player2.toggleBothSucced();
            transitioning = true;
        }
    }
    
    public void gameOver(){
        beginGame = false;
        gameOver = true;
        transitioning = false;
        
        addObject(startingCountdown,640,360);
        
        if(isATie){
            player1.tie();
            player2.tie();
            GreenfootImage gameOverScreen = new GreenfootImage("Tie.png");
            startingCountdown.setImage(gameOverScreen);
        }
        else if(player1Win){
            player1.win();
            player2.lose();
            GreenfootImage gameOverScreen = new GreenfootImage("Player1Wins.png");
            startingCountdown.setImage(gameOverScreen);
        }
            
        else if(player2Win){
            GreenfootImage gameOverScreen = new GreenfootImage("Player2Wins.png");
            startingCountdown.setImage(gameOverScreen);
            player2.win();
            player1.lose();
        }
            
        
        removeObject(operations);
        removeObject(num1_1);
        removeObject(operator);
        removeObject(num2_1);
        removeObject(timer);
        
        removeObject(answerBarP1);
        removeObject(answerBarP2);
        removeObject(ans1P1);
        removeObject(ans2P1);
        removeObject(ans1P2);
        removeObject(ans2P2);
        
        removeObject(healthBarP1);
        removeObject(healthBarP2);
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
               GreenfootImage imagePlusSymbol = new GreenfootImage("+-l.png");
               operator.setImage(imagePlusSymbol);
               break;
            case 2:
               GreenfootImage imageMinusSymbol = new GreenfootImage("_-l.png");
               operator.setImage(imageMinusSymbol);
               break;
            case 3:
               GreenfootImage imageProductSymbol = new GreenfootImage("div-l.png");
               operator.setImage(imageProductSymbol);
               break;
            case 4:
               GreenfootImage imageDivisionrSymbol = new GreenfootImage("X-l.png");
               operator.setImage(imageDivisionrSymbol);
               break;
        }
        
        int number2_1 = operations.getNum2();
        GreenfootImage imageNumber2 = new GreenfootImage(imagesNames[number2_1]); 
        num2_1.setImage(imageNumber2);
        
    }
    
    public void player1Inputs(){
        if(Greenfoot.isKeyDown("1")){
            keyPressedP1 = true;
            
            keyRepeatP1[1]++;
            
            if(keyRepeatP1[1] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[1]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "1";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("2")){
            keyPressedP1 = true;
            
            keyRepeatP1[2]++;
            
            if(keyRepeatP1[2]<= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[2]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "2";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("3")){
            keyPressedP1 = true;
            
            keyRepeatP1[3]++;
            
            if(keyRepeatP1[3] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[3]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "3";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("4")){
            keyPressedP1 = true;
            
            keyRepeatP1[4]++;
            
            if(keyRepeatP1[4] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[4]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "4";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("5")){
            keyPressedP1 = true;
            
            keyRepeatP1[5]++;
            
            if(keyRepeatP1[5] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[5]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "5";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("6")){
            keyPressedP1 = true;
            
            keyRepeatP1[6]++;
            
            if(keyRepeatP1[6] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[6]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "6";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("7")){
            keyPressedP1 = true;
            
            keyRepeatP1[7]++;
            
            if(keyRepeatP1[7] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[7]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "7";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("8")){
            keyPressedP1 = true;
            
            keyRepeatP1[8]++;
            
            if(keyRepeatP1[8] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[8]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "8";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("9")){
            keyPressedP1 = true;
            
            keyRepeatP1[9]++;
            
            if(keyRepeatP1[9] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[9]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "9";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("0")){
            keyPressedP1 = true;
            
            keyRepeatP1[0]++;
            
            if(keyRepeatP1[0] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[0]);
            
                if(numberPositionP1 <= 1)
                {
                    ansListP1[numberPositionP1].setImage(img);
                    answersP1[numberPositionP1] = "0";
                    numberPositionP1++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("enter")){
            keyPressedP1 = true;
            sentByP1 = true;
            
            keyRepeatP1[10]++;
            
            if(keyRepeatP1[10] <= 1){
                if(answersP1[0] != ""){
                    compareResults();
                    resetNumbers();
                }
            }
        }
        else if(Greenfoot.isKeyDown("backspace")){
            keyPressedP1 = true;
            
            keyRepeatP1[11]++;
            
            if(keyRepeatP1[11] <= 1){
            GreenfootImage less = new GreenfootImage("underscore-s.png");
                if(numberPositionP1 > 0)
                {
                    numberPositionP1--;
                    ansListP1[numberPositionP1].setImage(less);
                    answersP1[numberPositionP1] = "";
                }
            }
        }
        else{
            keyPressedP1 = false;
            
        }
    }
    
    public void player2Inputs(){
        if(Greenfoot.isKeyDown("Q")){
            keyPressedP2 = true;
            
            keyRepeatP2[1]++;
            
            if(keyRepeatP2[1] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[1]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "1";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("W")){
            keyPressedP2 = true;
            
            keyRepeatP2[2]++;
            
            if(keyRepeatP2[2]<= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[2]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "2";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("E")){
            keyPressedP2 = true;
            
            keyRepeatP2[3]++;
            
            if(keyRepeatP2[3] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[3]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "3";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("R")){
            keyPressedP2 = true;
            
            keyRepeatP2[4]++;
            
            if(keyRepeatP2[4] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[4]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "4";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("T")){
            keyPressedP2 = true;
            
            keyRepeatP2[5]++;
            
            if(keyRepeatP2[5] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[5]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "5";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("Y")){
            keyPressedP2 = true;
            
            keyRepeatP2[6]++;
            
            if(keyRepeatP2[6] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[6]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "6";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("U")){
            keyPressedP2 = true;
            
            keyRepeatP2[7]++;
            
            if(keyRepeatP2[7] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[7]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "7";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("I")){
            keyPressedP2 = true;
            
            keyRepeatP2[8]++;
            
            if(keyRepeatP2[8] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[8]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "8";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("O")){
            keyPressedP2 = true;
            
            keyRepeatP2[9]++;
            
            if(keyRepeatP2[9] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[9]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "9";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("P")){
            keyPressedP2 = true;
            
            keyRepeatP2[0]++;
            
            if(keyRepeatP2[0] <= 1){
            GreenfootImage img = new GreenfootImage(imagesNamesAnswer[0]);
            
                if(numberPositionP2 <= 1)
                {
                    ansListP2[numberPositionP2].setImage(img);
                    answersP2[numberPositionP2] = "0";
                    numberPositionP2++;
                }
            }
        }
        else if(Greenfoot.isKeyDown("space")){
            keyPressedP2 = true;
            sentByP2 = true;
            
            keyRepeatP2[10]++;
            
            if(keyRepeatP2[10] <= 1){
                if(answersP2[0] != ""){
                    compareResults();
                    resetNumbers();
                }
            }
        }
        else if(Greenfoot.isKeyDown("shift")){
            keyPressedP2 = true;
            
            keyRepeatP2[11]++;
            
            if(keyRepeatP2[11] <= 1){
            GreenfootImage less = new GreenfootImage("underscore-s.png");
                if(numberPositionP2 > 0)
                {
                    numberPositionP2--;
                    ansListP2[numberPositionP2].setImage(less);
                    answersP2[numberPositionP2] = "";
                }
            }
        }
        else{
            keyPressedP2 = false;
            
        }
    }
}
