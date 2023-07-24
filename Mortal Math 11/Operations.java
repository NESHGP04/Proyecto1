import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Operations extends Actor
{
    private float num1;
    private int operator;
    private float num2;
    private float fResult = 0.0f;
    private int result = 0;
    private boolean esEntero = false;
    private boolean esPositivo = false;
    
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
    }
    
    public int getNum1(){
        int numero1 = (int)num1;
        return numero1;
    }
    
    public void setNum2(){
        num2 = random.nextInt(10);
    }
    
    public int getNum2(){
        int numero2 = (int)num2;
        return numero2;
    }
    
    public void operar()
    {
        esEntero = false;
        esPositivo = false;
        
        while(esEntero == false || esPositivo == false)
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
            esPositivo = fResult >= 0.0f;
        }
        
        num1 = num1; 
        num2 = num2;
        result = (int)fResult;
    }
    
    public int getResult(){
        return result;
    }
}
