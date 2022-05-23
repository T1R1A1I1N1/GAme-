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
    
    public void swapn(){
      isnormal = true;
      timeswap = 0;
    }
    
    public Projectile swapl(Moves m){
      isnormal = false;
      timeswap = 0;
      return new Projectile(x,y,m);
    }
}
