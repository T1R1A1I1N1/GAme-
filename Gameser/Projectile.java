
public class Projectile extends Base
{
    double xd,yd,xdir,ydir;
    boolean deflect;
    int age;
    public Projectile(int xd, int yd, int xdir, int ydir)
    {
      super(xd,yd,10,10);
      this.xd = xd;
      this.yd = yd; 
      this.xdir = xdir;
      this.ydir = ydir;
    }
    
    public Projectile(int xd, int yd, Moves targ){
      super(xd,yd,10,10);
      this.xd = xd;
      this.yd = yd;
      updateDir(targ);
    }
    
    public void updateDir(Moves targ){
      int dist,ydist,xdist;
      ydist = targ.y-(int)yd;
      xdist = targ.x-(int)xd;
      dist = (int)Math.sqrt(xdist*xdist+ydist*ydist);
      xdir = (double)xdist/dist;
      ydir = (double)ydist/dist;
    }
    
    public void move(){xd+=xdir;
    yd+=ydir;
    age++;
     x = (int)xd;
     y = (int)yd;}
    
    public void hit(){deflect = true;
    xdir*=-1;
    ydir*=-1;}
    
    public void hit(Moves b){deflect = true;
    updateDir(b);}
}
