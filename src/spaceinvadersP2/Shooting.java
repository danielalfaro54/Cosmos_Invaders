package spaceinvadersP2;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
/**
 *
 * @author Daniel
 */
public class Shooting {
    int x;
    protected int y;
  protected Image pic;
  private boolean visible;
  private int velocity =13;
       public Shooting(int x, int y){
    ImageIcon shot = new ImageIcon(this.getClass().getResource("shot.gif"));
    pic = shot.getImage();
        visible = true;
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
   public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public void shDir(){
    y-=velocity;    //"direccion" de las balas.
        if(y<2) {          //distancia del disparo en eje y.
        visible =false;
        }
    }
    public Image getImage(){
        return pic;
    }
    public Rectangle imgB(){
        return new Rectangle(x, y, pic.getWidth(null), pic.getHeight(null));
    }
    
}
