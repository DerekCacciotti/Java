import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class WatchMe extends JApplet {
 public void init()
 {
  //setting the background color
  getContentPane().setBackground(Color.gray);
  
  // mouseListener
  addMouseListener(new MyMouseListener());
  
  // mouse motion listener
  addMouseMotionListener(new MyMouseMotionListener());

 }
 
 // constants for eye 
 final int eyeWidth = 30;
 final int eyeHeight = 40;
 final int pupilSize = 10;
 final int eyex1 = 70;
 final int eyey1 = 40;
 final int eyex2 = 110;
 final int eyey2 = 40;
 
 // vars for mouse exit and mouse down
 boolean MouseExit = false;
 boolean mouseDown = false;
 
 // enum to for the state of the eye
 
 enum EyeState {STRIGHT,UP,DOWN,LEFT,RIGHT};
 EyeState currentState = EyeState.STRIGHT;
 
 
 
 // APPLET METHODS
 public void paint(Graphics g)
 {
  super.paint(g);
  
  // set the color to the drawing
  g.setColor(Color.black);
  
  // drawing the first eye
  g.drawOval(eyex1, eyey1, eyeWidth, eyeHeight);
  
  // draw the second eye
  g.drawOval(eyex2, eyey2, eyeWidth, eyeHeight);
  
  // drawing the pupil
  switch (currentState)
  {
  case STRIGHT:
  lookStraight(g);
  break;
  case UP:
   lookUp(g);
   break;
  case DOWN:
   lookDown(g);
   break;
  case LEFT:
   lookLeft(g);
   break;
  case RIGHT:
   lookRight(g);
   break;
  } // end of switch
  
 } // end of paint method
 
 
 // pupil direction methods
 public void lookStraight(Graphics g)
 {
  g.fillOval(eyex1 + (eyeWidth/2)-(pupilSize/2), 
    eyey1+(eyeHeight/2)-(pupilSize/2),  pupilSize, pupilSize);
  
  g.fillOval(eyex2 + (eyeWidth/2)-(pupilSize/2), 
     eyey2+(eyeHeight/2)-(pupilSize/2),  pupilSize, pupilSize);

 } // end of lookStraight
 
 public void lookUp(Graphics g)
 {
  g.fillOval(eyex1 + (eyeWidth/2)-(pupilSize/2),
                eyey1, pupilSize, pupilSize);
  
          g.fillOval(eyex2 + (eyeWidth/2)-(pupilSize/2),
                eyey2, pupilSize, pupilSize);
 } // end of lookup
 
 public void lookDown(Graphics g)
 {
  g.fillOval(eyex1 + (eyeWidth/2)-(pupilSize/2),
    
                eyey1 + eyeHeight-pupilSize,
                        pupilSize, pupilSize);

          g.fillOval(eyex2 + (eyeWidth/2)-(pupilSize/2),
   
                eyey2 + eyeHeight-10,
    
                pupilSize, pupilSize);

 } // end of lookDown
 
 
 
 public void lookLeft(Graphics g)
 {
  g.fillOval(eyex1, eyey1+(eyeHeight/2)-(pupilSize/2),
    
                pupilSize,pupilSize);
           
          g.fillOval(eyex2, eyey1+(eyeHeight/2)-(pupilSize/2),
                pupilSize,pupilSize);

 } // end look left
 
 
 public void lookRight(Graphics g)
 {
  g.fillOval(eyex1+eyeWidth-pupilSize,
    
                eyey1+(eyeHeight/2)-(pupilSize/2),
                         pupilSize,pupilSize);
   
   
          g.fillOval(eyex2+eyeWidth-pupilSize,
                eyey1+(eyeHeight/2)-(pupilSize/2),
                         pupilSize,pupilSize);

 } // end of look right
 
 
 
 private class MyMouseListener implements MouseListener
 {

  

  @Override
  public void mousePressed(MouseEvent e) {
   mouseDown = true;
   
   
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    mouseDown = false;
   
  }

  @Override
  public void mouseEntered(MouseEvent e) {
   MouseExit = false;
  }

  @Override
  public void mouseExited(MouseEvent e) {
  currentState = EyeState.STRIGHT;
  MouseExit = true;
  repaint();
   
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  
  }
  
 }
 
 private class MyMouseMotionListener implements MouseMotionListener
 {

  @Override
  public void mouseDragged(MouseEvent e) {
   
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  EyeState oldState = currentState;
  
  if(!MouseExit)
  {
   // if teh mouse goes above the eye
   if (e.getY() < 40)
   {
    currentState = EyeState.UP;

   } // end of inner if
   
   // if mouse goes below eye
   else if(e.getY() > eyey1+eyeHeight)
   {
    currentState = EyeState.DOWN;
   } // end of below if
   
   // if mouse moves to the left
   else if (e.getX() < eyex1)
   {
    currentState = EyeState.LEFT;
   }
   
   // if the mouse moves to the right
   else if (e.getX() > eyex2 + eyeWidth)
   {
    currentState = EyeState.RIGHT;
   }
   
   // incase all if statements fail
   else 
   {
    currentState = EyeState.STRIGHT;
   }
  }
  
  else 
  {
   currentState = EyeState.STRIGHT;
  }
  
  
  if (oldState!= currentState)
  {
   repaint();
  }
  
   
  }
  
 }
 
 
 
 
 

 }
 
 


