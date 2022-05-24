
public class Projectile
{
    double x,y,xdir,ydir;
    boolean dead;
    int age;
    public Projectile(int x, int y, int xdir, int ydir)
    {
      this.x = x;
      this.y = y; 
      this.xdir = xdir;
      this.ydir = ydir;
    }
    
    public Projectile(int x, int y, Moves targ){
      
      this.x = x;
      this.y = y;
      updateDir(targ);
    }
    
    public void updateDir(Moves targ){
      int dist,ydist,xdist;
      ydist = targ.y-(int)y;
      xdist = targ.x-(int)x;
      dist = (int)Math.sqrt(xdist*xdist+ydist*ydist);
      xdir = (double)xdist/dist;
      ydir = (double)ydist/dist;
    }
    
    public void move(){x+=xdir;
    y+=ydir;
    age++;}
    
    public void hit(){dead = true;}
}
