package spaceinvadersP2;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Image;
import java.util.ArrayList;
/**
 *
 * @author Daniel
 */
public class SpaceShip {
 private int x,y,z;
 private Image naveimg;
 private ArrayList bullets;
 private boolean shooting;
 private final int vel = 7;
    
  public SpaceShip(){
       bullets = new ArrayList();
       shooting = true;
ImageIcon spaceship = new ImageIcon(this.getClass().getResource("spaceship2.gif"));
naveimg = spaceship.getImage();
        y = 600;
        x = 605;
        z = 0;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Image getImage(){
        return naveimg;
    }
    public ArrayList getBullets(){
        return bullets;
    }
     public Rectangle imgB(){
        return new Rectangle(x, y, naveimg.getWidth(null), naveimg.getHeight(null));
    }
 public void keyPressed(KeyEvent ev){
    int K1 = ev.getKeyCode();
        if(K1 == KeyEvent.VK_RIGHT) 
            z = vel;
        if(K1 == KeyEvent.VK_D) 
            z = vel;
        if(K1 == KeyEvent.VK_LEFT)
            z = vel * -1;
        if(K1 == KeyEvent.VK_A)
            z = vel * -1;
        if(K1 == KeyEvent.VK_UP && shooting)
        {
            bullets.add(new Shooting(x + 35, y));  
        }if(K1 == KeyEvent.VK_B && shooting)
        {
            bullets.add(new Shooting(x + 35, y));
            //shot = false;
        }
    }  public void keyReleased(KeyEvent ev){
    int K2 = ev.getKeyCode();
        if(K2 == KeyEvent.VK_LEFT && z < 0)
            z = 0;
         if(K2 == KeyEvent.VK_A && z > 0)
            z = 0;
        if(K2 == KeyEvent.VK_RIGHT && z > 0)
            z = 0;
        if(K2 == KeyEvent.VK_D && z > 0)
            z = 0;
        if(K2 == KeyEvent.VK_B)
            shooting = true;
         if(K2 == KeyEvent.VK_UP)
            shooting = true;
    }
 public void movement(){
   if((x>0 && z<0) || (x<1215 && z>0))  //rango de movimiento de nave en ejes
    x += z;
    }/* public void keyPressed(KeyEvent ev) {
        if (ev.getExtendedKeyCode() == KeyEvent.VK_RIGHT || ev.getExtendedKeyCode() == KeyEvent.VK_D) {
            ejemplo.setLocation(ejemplo.getX() + 5, 500);
        }

        if (ev.getExtendedKeyCode() == KeyEvent.VK_LEFT || ev.getExtendedKeyCode() == KeyEvent.VK_A) {
            ejemplo.setLocation(ejemplo.getX() - 5, 500);
        }
        
        if (ev.getExtendedKeyCode() == KeyEvent.VK_SPACE || ev.getExtendedKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Disparaste");
        }*/
}
 