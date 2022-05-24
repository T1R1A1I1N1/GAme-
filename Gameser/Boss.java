import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
public class Boss extends Enemy
{
    ArrayList<Integer> fakex, fakey;
    Tile[][] map;
    int q;
    boolean g;
    public Boss(int x, int y, int xsiz, int ysiz,int speed, int hp)
    {
        super(x,y,xsiz,ysiz,speed,hp);
        fakex = new ArrayList<Integer>();
        fakey = new ArrayList<Integer>();
        int q = 0;
    }
    
    public void runBoss(Tile[][] map){
      this.map = map;
      for(int i = 0; i < map.length; i++){for(int j = 0; j < map[0].length; j++){
          if(map[i][j].toString().equals("fake")){fakex.add(i);
            fakey.add(j);}
         }}
      g = true;
    }
    
    public Projectile bossStuff(Player p){
    int xs;
    q++;
    xs = (int)(Math.random()*fakex.size());
    if(fakex.size() != 0 && ((FalseNormal)map[fakex.get(xs)][fakey.get(xs)]).isnormal == true){return ((FalseNormal)map[fakex.get(xs)][fakey.get(xs)]).swapL(p);}
    return new Projectile(x,y,p);
   }

  public String toString(){return "boss";}
}
