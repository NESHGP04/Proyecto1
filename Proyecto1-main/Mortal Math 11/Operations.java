import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Operations extends Actor
{
    private float num1;
    private int operator;
    private float num2;
    private float fResult = 0.0f;
    private int result = 0;
    private boolean esEntero = true;
    
    Random random = new Random();
    
    public void act()
    {
        
    }
    
    public void asignarValores(){
        setNum1();
        setNum2();
        setOperator();
    }
    
    public void setOperator(){
        operator = random.nextInt(4)+1;
    }
    
    public int getOperator(){
        return operator;
    }
    
    public void setNum1(){
        num1 = random.nextInt(10);
        System.out.println("Num1: " + num1);
    }
    
    public int getNum1(){
        int numero1 = (int)num1;
        return numero1;
    }
    
    public void setNum2(){
        num2 = random.nextInt(10);
        System.out.println("Num2: " + num2);
    }
    
    public int getNum2(){
        int numero2 = (int)num2;
        return numero2;
    }
    
    public int resultado()
    {        
        while(esEntero == true)
        {
            setNum1();
            setNum2();
            setOperator();
            
            switch(operator){
                case 1:
                    fResult = num1 + num2;
                    break;
                case 2:
                    fResult = num1 - num2;
                    break;
                case 3:
                    fResult = num1 / num2;
                    break;
                case 4:
                    fResult = num1 * num2;
                    break;
            }
            
            esEntero = (fResult - Math.floor(fResult) == 0.0f);
        }
        
        result = (int)fResult;
        
        return result;
    }
}
