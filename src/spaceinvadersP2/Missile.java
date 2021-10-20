package spaceinvadersP2;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Daniel
 */
 public class Missile extends Shooting{
    protected Image picm;
    public Missile(int x, int y){
        super(x, y);
    ImageIcon picture = new ImageIcon(this.getClass().getResource("missile.gif"));
    picm = picture.getImage(); 
}
    public void missDir(){
            y++;          //direccion en y hacia donde se avientan los misiles
  }
public Rectangle imgM(){
        return new Rectangle(x, y, picm.getWidth(null), picm.getHeight(null));
}
 }