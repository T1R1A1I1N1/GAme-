import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class LavaTile extends Tile
{
    
    public LavaTile(int x, int y)
    {
        super(x,y,Color.orange,new Color(255,153,0));
    }

    public String toString(){return "lava";}
}
