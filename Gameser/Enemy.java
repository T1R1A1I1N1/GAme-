import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
public class Enemy extends Moves
{
    boolean dead;
    public Enemy(int x, int y, int xsiz, int ysiz,int speed, int hp)
    {
        super(x,y,xsiz,ysiz,speed,hp);
    }

    public Enemy(int x, int y,int speed, int hp)
    {
        super(x,y,27,27,speed,hp);
    }
    
    public void fell(){dead = true;}
    
    public void move(Tile[][] map){
      x+= xdir;
      y+= ydir;
      if(!Stat.canMove(this,map)){x-=xdir;
        y-=ydir;}
      if(xdir>0) xdir--;
      if(xdir<0) xdir++;
      if(ydir>0) ydir--;
      if(ydir<0) ydir++;
    }
}

