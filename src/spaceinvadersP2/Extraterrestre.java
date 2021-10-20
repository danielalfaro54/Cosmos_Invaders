package spaceinvadersP2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 *
 * @author Daniel
 */
public class Extraterrestre {
    
    private final int amplitud;
    private boolean MarcianVsble;
     private boolean Abajo;
     private Image imagen;
    private ArrayList missile;
     private Random random;
    private int x, y, mX, mY;
    private int velocity, posicion, GirarAbajo, missileFrecuency;
    
public Extraterrestre(int x, int y){
    ImageIcon marcian = new ImageIcon(this.getClass().getResource("marcian.gif"));
    imagen = marcian.getImage();
missileFrecuency = 1200;           //Cantidad de bombas que avienta
missile = new ArrayList();       //arreglo de los misiles arrojados
velocity = 3;      //rapidez de los marcianos(inicia en 1)
amplitud = 800;    //longitud de rango de movimeinto
mX = 0;        //posicion en x en donde se encuentran al iniciar
posicion = 1;     //direccion a la que se mueven los marcianos.
MarcianVsble = true;    //hace visible a los marcianitos
Abajo = false;     //si es true se hace un salto inicial hacia abajo
mY = 0;        //posicion inicial de movimiento en y
GirarAbajo = 0;             //Cda vez que se giran hacia abajo
random = new Random();
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
     public int getVelocity(){
        return velocity;
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
    public Image getImage(){
        return imagen;
    }

    public void setMarcianV(boolean marcianv){
        this.MarcianVsble = marcianv;
    }
    
    public boolean isMarcianV(){
        return MarcianVsble;
    }
    
 public void turning(){   //método para que una vez llega al limite, baja y da la vuelta
    if(mX>amplitud){  
     mX = 0;
     Abajo = true;
        posicion*=-1;   
}
  if(Abajo==true){     
  mY++;             //espacio movimiento que realiza hacia abajo.
     y++;              //permite girar hacia abajo en el eje y.
          
     if(23<mY) {      //cantidad de espacio que deja cuando bajar
    mY = 0;
    Abajo = false;
      
        velocity++;
    }       
} 
   else {
        x +=posicion*velocity;   //mover hacia los lados
       mX+= velocity;              //mover hacia abajo
        }
    if(random.nextInt()% missileFrecuency == 150 && y <= 300)
       missile.add(new Missile(x,y));
    }
public ArrayList getMissile(){
    return missile;
}
public Rectangle imglimits(){
        return new Rectangle(x, y, imagen.getWidth(null), imagen.getHeight(null));
    }


}

