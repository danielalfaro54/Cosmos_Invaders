package spaceinvadersP2;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.SOUTH;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import static java.awt.GridBagConstraints.SOUTH;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.scene.text.Font.font;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import static javax.swing.SpringLayout.SOUTH;
/**
 *
 * @author Daniel
 */
public class lienzo extends JPanel implements ActionListener {
    
JLabel ejemplo = new JLabel();
JLabel ene1 = new JLabel();
JLabel ene2 = new JLabel();
JLabel ene3 = new JLabel();
JLabel ene4 = new JLabel();
JLabel Score = new JLabel();
 private Font style;
    private String nave = "space2.gif";
    private String ene = "marci (1).png";
   
         private Image imagen;
         private Image ima;   
   
ImageIcon icon = new ImageIcon(this.getClass().getResource(nave));
//ImageIcon icon2 = new ImageIcon(this.getClass().getResource(ene));
    private SpaceShip spaceship;
    private Extraterrestre marciano[][];
    private Timer timer;
    private String puntos;
    private String SpaceShipVidas;
    private final int MarcianX, MarcianY;
    private final int MarcianRowNum, MarcianColumn;
    private final int MarcianGap;
    private int MarcianosRest;
    private int MarciansCant;
    private int SpaceShipLives;
public lienzo() {
     ejemplo.setIcon(icon);
    add(ejemplo);
style = new Font("Consolas", Font.PLAIN, 19);       
spaceship = new SpaceShip();
MarcianX = 10;         //Iniciar en x
MarcianY = 20;         //iNICIO EN eje Y
SpaceShipLives = 100;   //porcentaje de vida de la nave
MarciansCant = 0;     //Num de marcianos en total
MarcianGap = 5;        //Espacio entre cada marciano
MarcianRowNum = 14;    //marcianos por filas
MarcianColumn = 7;     //marcianos por columnas
MarcianosRest = MarcianRowNum * MarcianColumn;
int puntaje = MarcianosRest;
 marciano = new Extraterrestre[MarcianRowNum][MarcianColumn];
 for(int i = 0; i < MarcianRowNum; i++){
  for(int j = 0; j < MarcianColumn; j++){
  marciano[i][j] = new Extraterrestre(MarcianX, MarcianY);   
   marciano[i][j].setX(MarcianX + i*marciano[i][j].getImage().getWidth(null) + i*MarcianGap);
    marciano[i][j].setY(MarcianY + j*marciano[i][j].getImage().getHeight(null) + j*MarcianGap);
 }
}
timer = new Timer(20, this);
     timer.start();
setDoubleBuffered(true);
setFocusable(true);
addKeyListener(new Listener());
puntos = "Puntos: " + MarciansCant;
}
 
public void paint(Graphics g){
    super.paint(g);
    Graphics2D grapho = (Graphics2D)g;
  
//////////////////////////////////////////////////////////////////////////////////////////////////////
   //--DIBUJOS--
    for(int i = 0; i < MarcianRowNum; i++)
   for(int j = 0; j < MarcianColumn; j++){          // --DIBJO MARCIANOS--
      if(marciano[i][j].isMarcianV())
        grapho.drawImage(marciano[i][j].getImage(), marciano[i][j].getX(),
           marciano[i][j].getY(), this);
    ArrayList<Missile> missiles1 = marciano[i][j].getMissile();
    for(int k=0; k < missiles1.size(); k++){
        Missile missile = missiles1.get(k);
        grapho.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
    }
}
//////////////////////////////////////////////////////////////////////////////////
    ArrayList<Shooting> bullets1 = spaceship.getBullets();
     for(int i = 0; i < bullets1.size(); i++){               // --DIBUJO BALAS--   
        Shooting sh1 = bullets1.get(i);
    grapho.drawImage(sh1.getImage(), sh1.getX(), sh1.getY(), this);
}
 ///////////////////////////////////////////////////////////////////////////////    
     // --DIBUJO NAVE esp--
    grapho.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);
   
//---Estilo de la etiqueta del puntaaje, 
  grapho.setColor(Color.black);
   grapho.setFont(style);
    grapho.drawString(puntos, 1170, 19);
    
    // ---DIBUJO ETIQUETA de la VIDA--
   grapho.setColor(Color.WHITE);
   grapho.setFont(style);
    grapho.drawString(SpaceShipVidas, 7, 20);
//Toolkit.getDefaultToolkit().sync();
    g.dispose();
}
////////////////////////////////////////////////////////////////////////////////
//----acciones y movimientos---
public void actionPerformed(ActionEvent e){
    spaceship.movement();  
// --BALAS nave--
   ArrayList<Shooting> bullettime = spaceship.getBullets();
     for(int i = 0; i < bullettime.size(); i++){
        Shooting sh2 = bullettime.get(i);
        if(sh2.isVisible())
            sh2.shDir();
        else
            bullettime.remove(i);
        }
for(int i = 0; i < MarcianRowNum; i++){
       for(int j = 0; j < MarcianColumn; j++){
     marciano[i][j].turning();
 ////////////////////////////////////////////////////////////////////////////////               
// --MISILES MARCIANOS--
    ArrayList<Missile> missiles = marciano[i][j].getMissile();
      for(int k = 0; k < missiles.size(); k++){
        Missile missile = missiles.get(k);
    if(missile.isVisible())
        missile.missDir();
    else
        missiles.remove(k);
if(missile.imgB().intersects(spaceship.imgB()))  //cuando el misil toca la nave esta desaparece
{      missile.setVisible(false); 
    SpaceShipLives-=10;
  }
}
      SpaceShipVidas = "Vida: " + SpaceShipLives + "%";
//////////////////////////////////////////////////////////////////////////////////////////      
//--HITBOX(que desparezcan la bala y la nave al colisionar)
for(int z = 0; z < bullettime.size(); z++){
   Shooting s = bullettime.get(z);
  if(s.imgB().intersects(marciano[i][j].imglimits()) && s.isVisible() && marciano[i][j].isMarcianV()){
    marciano[i][j].setMarcianV(false);
      s.setVisible(false);
MarcianosRest--;     
MarciansCant+=20;
        }
      }
    }
  }
puntos = "Puntos: " + MarciansCant;
////////////////////////////////////////////////////////////////////////////////
                                  //--MENSAJE DE VICTORIA---
if (MarcianosRest==0) {
    
    int answerWINNER = JOptionPane.showConfirmDialog(this,
  "HAS GANADO. ¿Deseas jugar de nuevo?", "Felicidades",
  JOptionPane.YES_NO_OPTION);
    
 if (answerWINNER == JOptionPane.YES_OPTION)
 {
  for(int i = 0; i < MarcianRowNum; i++)
  for(int j = 0; j < MarcianColumn; j++){
    marciano[i][j] = null;
    marciano[i][j] = new Extraterrestre(MarcianX, MarcianY);
    marciano[i][j].setX(MarcianX + i*marciano[i][j].getImage().getWidth(null) + i*MarcianGap);
    marciano[i][j].setY(MarcianY + j*marciano[i][j].getImage().getHeight(null) + j*MarcianGap);
MarcianosRest = 98;
MarciansCant = 0;
SpaceShipLives = 100;

    JOptionPane.getRootFrame().dispose();   
  }
}     
 if (answerWINNER == JOptionPane.NO_OPTION)
 {
     
  System.exit(0);
  
  }     
 }
////////////////////////////////////////////////////////////////////////////////
                                    //--MENSAJE DE DERROTA---
 if (SpaceShipLives<=0) {
    int answerLOSER = JOptionPane.showConfirmDialog(this,
  "HAS PERDIDO. ¿Deseas jugar de nuevo?", "Perdedor",
  JOptionPane.YES_NO_OPTION);
 if (answerLOSER == JOptionPane.YES_OPTION)
 {
     for(int b = 0; b < MarcianRowNum; b++)
  for(int c = 0; c < MarcianColumn; c++){
    marciano[b][c] = null;
    marciano[b][c] = new Extraterrestre(MarcianX, MarcianY);
    marciano[b][c].setX(MarcianX + b*marciano[b][c].getImage().getWidth(null) + b*MarcianGap);
    marciano[b][c].setY(MarcianY + c*marciano[b][c].getImage().getHeight(null) + c*MarcianGap);
MarcianosRest = 98;
MarciansCant = 0;
SpaceShipLives = 100;
    JOptionPane.getRootFrame().dispose();   
  }
 }    
 if (answerLOSER == JOptionPane.NO_OPTION)
  {
  System.exit(0);
  }
 } 
}
private class Listener extends KeyAdapter{
            @Override
                public void keyPressed(KeyEvent e){
                    spaceship.keyPressed(e);     
}
                        @Override
                    public void keyReleased(KeyEvent e){
                    spaceship.keyReleased(e);
}               
}
public void keyTyped(KeyEvent ev) {
    }
    public void keyPressed(KeyEvent ev) {
        if (ev.getExtendedKeyCode() == KeyEvent.VK_RIGHT || ev.getExtendedKeyCode() == KeyEvent.VK_D) {
            ejemplo.setLocation(ejemplo.getX() + 5, 500);
        }
        if (ev.getExtendedKeyCode() == KeyEvent.VK_LEFT || ev.getExtendedKeyCode() == KeyEvent.VK_A) {
            ejemplo.setLocation(ejemplo.getX() - 5, 500);
        }
        if (ev.getExtendedKeyCode() == KeyEvent.VK_SPACE || ev.getExtendedKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Disparaste");
        }
    }
}

