import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class FalseNormal extends NormalTile
{
    boolean isnormal;
    int timeswap;
    public FalseNormal(int x, int y)
    {
        super(x,y);
        isnormal = true;
    }
    
    public void swapN(){
      isnormal = true;
      timeswap = 0;
      c = new Color(102,255,102);
      s = new Color(72,195,72);
    }
    
    public Projectile swapL(Moves m){
      isnormal = false;
      timeswap = 0;
      c = Color.orange;
      s = new Color(255,153,0);
      return new Projectile(x,y,m);
    }
    
    public String toString(){return "fake";}
    
    public void incSwap(){timeswap++;}
}
