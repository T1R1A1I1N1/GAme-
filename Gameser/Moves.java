
public abstract class Moves extends Base
{
    String dir = "down";
    boolean inv;
    int invtim,speed,hp;
    int safex,safey;
    int xdir,ydir;
    public Moves(int x, int y, int xsiz, int ysiz,int speed,int hp)
    {
       super(x,y,xsiz,ysiz);
       this.speed = speed;
       this.hp = hp;
    }
    
    public void hit(){
      invtim = 200;
      hp--;
      inv = true;
    }
    
    public void hit(int n){
      invtim = 200;
      hp-=n;
      inv = true;
    }
    
    public void hit(Moves m){
      if(m.dir.equals("down")) ydir = 10;
      if(m.dir.equals("up")) ydir = -10;
      if(m.dir.equals("left")) xdir = -10;
      if(m.dir.equals("right")) xdir = 10;
      invtim = 200;
      hp--;
      inv = true;
    }
    
    public void stuff(){
      invtim--;
      inv = (invtim>0);
    }
    
    public abstract void fell();
}
