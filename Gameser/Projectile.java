
public class Projectile
{
    double x,y,xdir,ydir;
    boolean dead;
    public Projectile(int x, int y, int xdir, int ydir)
    {
      this.x = x;
      this.y = y; 
      this.xdir = xdir;
      this.ydir = ydir;
    }
    
    public Projectile(int x, int y, Moves targ){
      int dist,ydist,xdist;
      ydist = targ.y-y;
      xdist = targ.x-x;
      dist = (int)Math.sqrt(xdist*xdist+ydist*ydist);
      xdir = (double)xdist/dist;
      ydir = (double)ydist/dist;
      this.x = x;
      this.y = y;
      
    }
    
    public void move(){x+=xdir;
    y+=ydir;}
    
    public void hit(){dead = true;}
}
