import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class NormalTile extends Tile
{
    
    public NormalTile(int x, int y)
    {
        super(x,y,new Color(102,255,102),new Color(72,195,72));
    }

    public String toString(){return "normal";}
}
