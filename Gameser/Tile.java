import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Tile extends Base
{
    Color c,s;
    public Tile(int x, int y, Color c,Color s)
    {
        super(x,y,30,30);
        this.c = c;
        this.s = s;
    }

    public String toString(){return "";}
}
