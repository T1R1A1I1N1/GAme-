import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
public class GameMain implements ActionListener, KeyListener
{
  JFrame f1;
  JPanel main, sub;
  GameGraph g1;
  JButton b1,b2;
  boolean endgame,start,enkilled,Rshut;
  Tile[][] map;
  Player p;
  Sword s;
  ArrayList<Enemy> bad;
  File[][] f, en;
  int roomx, roomy;
  Projectile pr;
  public GameMain()
  {
    makeMap();
    setVariables();
    setPanel();
    game();
  }
  private void makeMap(){
    f = Stat.setFile(new File("roomSetup"));
    en = Stat.setFile(new File("roomSetupstuff"));
    map = Stat.mapFile(f[roomy][roomx]);
    bad = new ArrayList<Enemy>();
    Stat.roomen(bad,en[roomy][roomx]);
    }
  private void setPanel()
  {
    f1 = new JFrame("GAME!!!");
      f1.setSize(600,800);
      f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c1 = f1.getContentPane();
    
    b1 =  new JButton("Start");
      b1.addActionListener(this);
        
    b2 =  new JButton("End");
      b2.addActionListener(this);
      
    g1 = new GameGraph(map,p,s,bad,pr);
      g1.addKeyListener(this);  
      
    
    sub = new JPanel(); 
      sub.add(b1);
      sub.add(b2);
    
    main = new JPanel();
      main.setLayout(new BorderLayout());          
      main.setSize(500,500);
      main.add(g1,BorderLayout.CENTER);
      main.add(sub,BorderLayout.SOUTH);
    c1.add(main);
    f1.show();
    f1.setResizable(false);
  }
  private void setVariables()
  {
    p = new Player(200,200,27,27,5,5);
    s = new Sword(p);
    pr = new Projectile(70,30,p);
  }
  private void game()
  {
    Thread runner = new Thread();
    while(!endgame)
    {    
      try 
      { 
        runner.sleep(5); 
      }
      catch(InterruptedException e) {}  
      if(start)
      {
        swordStuff();
        imstuff();
        boxcollision();
        collision();
        moveEnemy();
        
        g1.repaint();
      }
    }
  }
  
  private void moveEnemy(){
    for(int i = 0; i < bad.size(); i++){
      
    }
    }
  private void boxcollision(){
    boolean swim = true;
    boolean safe = true;
    for(Tile[] g: map){
        for(Tile t: g){
        if(Stat.collision(t,p)) {
          if(t.toString().equals("spike") && !p.inv) p.hit(((SpikeTile)t).dam);
          if(!t.toString().equals("water")) swim = false;
          if(!t.toString().equals("normal")) safe = false;
        }
      }
    }
    p.swim = swim;
    if(safe){
      p.safex = p.x;
      p.safey = p.y;}
  }
  
  private void imstuff(){
    for(int i = 0; i < bad.size(); i++){bad.get(i).stuff();
      if(bad.get(i).hp <=0) {bad.remove(i);
      i--;}
    }
    if(p.x<0){roomx--;
      p.x = 500;
      newRoom();
      }
    if(p.y<0){roomy--;
      p.y = 500;
      newRoom();}
    if(p.x>=509){roomx++;
      p.x = 0;
      newRoom();}
    if(p.y>=509){roomy++;
      p.y = 0;
      newRoom();}
    roomSpecial();
    pr.move();
    p.stuff();
    for(Enemy e: bad) e.move(map);
    if(p.hp<=0){ endgame = true;
      g1.endGame();
    }
  }
    
  private void roomSpecial(){
    if(roomx == 0 && roomy == 1 && !enkilled && !Rshut && p.x > 40 && p.y > 40 && p.x < 460 && p.y < 460){
      Rshut = true;
      map[0][9] = new WallTile(270,0);
      map[0][8] = new WallTile(240,0);
      map[17][9] = new WallTile(270,510);
      map[17][8] = new WallTile(240,510);
      map[8][17] = new WallTile(510,240);
      map[9][17] = new WallTile(510,270);
    } 
    if(Rshut && bad.size() == 0 && !enkilled){
      enkilled = true;
      map[0][9] = new NormalTile(270,0);
      map[0][8] = new NormalTile(240,0);
      map[17][9] = new NormalTile(270,510);
      map[17][8] = new NormalTile(240,510);
      map[8][17] = new NormalTile(510,240);
      map[9][17] = new NormalTile(510,270);
    }
    }
  
  private void newRoom(){
    map = Stat.mapFile(f[roomy][roomx]);
    Stat.roomen(bad,en[roomy][roomx]);
    g1.map = map;
    }  
    
  private void swordStuff(){
    s.stime--;
    s.updateSword();
    
    }
  
  private void collision(){
    for(Enemy b: bad){
      if(Stat.collision(p,b) && !p.inv)p.hit();
      if(Stat.collision(b,s) && s.appear && !b.inv) b.hit(p);
    }
    
    }

  public void actionPerformed (ActionEvent event)
  {
    if (event.getSource() == b1)
    {
       start = true; 
       g1.requestFocus();
    }
    if (event.getSource() == b2)
    {
       endgame = true;
    }
   }
  public void keyPressed(KeyEvent evt)
  {
    if(evt.getKeyCode() == 38)
     {
      //up 
      for(int i = p.speed; i >0; i--){
        p.y-=i;
        if(!Stat.canMove(p,map)) p.y+=i;
        else break;
        }
      p.dir = "up";
     }
    if(evt.getKeyCode() == 40)
    {
      //down
      for(int i = p.speed; i >0; i--){
        p.y+=i;
        if(!Stat.canMove(p,map)) p.y-=i;
        else break;
        }
      p.dir = "down";
    }
    if(evt.getKeyCode() == 37)
    {
       //left
       for(int i = p.speed; i >0; i--){
        p.x-=i;
        if(!Stat.canMove(p,map)) p.x+=i;
        else break;
        }
       p.dir ="left";
    }
    if(evt.getKeyCode() == 39)
    {
       //right
       for(int i = p.speed; i >0; i--){
        p.x+=i;
        if(!Stat.canMove(p,map)) p.x-=i;
        else break;
        }
       p.dir = "right";
    }
    if(evt.getKeyCode() == 32)
    {
       s.appear = true;
       s.stime = 100;
    }
  }
  
  public void keyReleased(KeyEvent evt)
  {}
  public void keyTyped(KeyEvent evt)
  {}
}
