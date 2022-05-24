import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class GameGraph  extends JPanel
{
    Tile[][] map;
    Player p;
    Sword s;
    ArrayList<Enemy> bad;
    boolean[] endgame;
    ArrayList<Projectile> pr;
    public GameGraph(Tile[][] map, Player p,Sword s, ArrayList<Enemy> bad,ArrayList<Projectile> pr)
    { this.map = map;
      this.p = p;
      this.s = s;
      this.bad = bad;
      endgame = new boolean[1];
      this.pr = pr;
    }
    
    public void endGame(){
      endgame[0] = true;
    }
    
    public void paint (Graphics g)         
    {
        super.paint(g); 
        for(Tile[] h : map){
          for(Tile d : h){
            g.setColor(d.c);
            g.fillRect(d.x,d.y,d.xsiz,d.ysiz);
            g.setColor(d.s);
            g.drawRect(d.x,d.y,d.xsiz,d.ysiz);
            }
        }
        // player schenanigans
        if(p.inv) g.setColor(Color.green);
        else g.setColor(Color.blue);
        if(p.swim) g.setColor(new Color(0,0,200));
        g.fillRect(p.x,p.y,p.xsiz+1,p.ysiz+1);
        // sword stuff
        g.setColor(Color.red);
        if(s.appear)g.fillRect(s.x,s.y,s.xsiz,s.ysiz);
        // enemys
        g.setColor(Color.orange);
        for(Enemy e : bad){
            if(e.inv) g.setColor(Color.orange);
            else g.setColor(Color.yellow);
            g.fillRect(e.x,e.y,e.xsiz,e.ysiz);}
        g.setColor(Color.black);
                    if(endgame[0]){g.drawString("Game Over",50,50);}
        g.drawRect(0,550,540,30);
        g.setColor(Color.red);
        g.fillRect(1,551,(int)(((p.hp*1.0)/p.maxHP)*540)-1,29);
        for(int i = 0; i < pr.size(); i++){
        g.fillRect((int)pr.get(i).x,(int)pr.get(i).y,10,10);}
    }
}
