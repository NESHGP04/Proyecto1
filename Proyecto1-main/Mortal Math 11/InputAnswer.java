import greenfoot.*;
import javax.swing.JOptionPane;
public class InputAnswer extends AnswerBar
{
    public void act(){
        String input =JOptionPane.showInputDialog("Ingrese su respuesta:");
        int numero=Integer.parseInt(input);
        System.out.println(numero);
    }
      }
