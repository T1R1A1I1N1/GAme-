
public class Projectile extends Base
{
    double x,y,xdir,ydir;
    boolean deflect;
    int age;
    public Projectile(int x, int y, int xdir, int ydir)
    {
      super(x,y,10,10);
      this.x = x;
      this.y = y; 
      this.xdir = xdir;
      this.ydir = ydir;
    }
    
    public Projectile(int x, int y, Moves targ){
      super(x,y,10,10);
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
    
    public void hit(){deflect = true;
    xdir*=-1;
    ydir*=-1;}
}
