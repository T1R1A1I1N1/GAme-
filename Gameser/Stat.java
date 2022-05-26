import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
public class Stat
{
    
    public Stat()
    {
        
    }

    public static boolean collision(Base a, Base b){
      return (a.x<=b.x+b.xsiz && a.x >= b.x-a.xsiz && a.y<=b.y+b.ysiz && a.y >= b.y-a.ysiz);
    }
    
    public static boolean canMove(Moves m,Tile[][] map){
    boolean lava = true;
    for(Tile[] l: map){for(Tile t: l){
      if(Stat.collision(m,t)){
        if(t.toString().equals("wall")) return false;
        if(!(t.toString().equals("lava") || (t.toString().equals("fake") && !((FalseNormal)t).isnormal))) {
          lava = false;
        }
      }
    }}
    if(lava){m.fell();}
    return true;
    }
    
    
    public static Tile[][] mapFile(File f){
      FileReader fr;
      BufferedReader br;
      String[] s;
      Tile[][] map;
      s = new String[18];
      try{
       fr = new FileReader(f);
       br = new BufferedReader(fr);
       for(int i = 0; i < 18; i++){
            s[i] = br.readLine();
            
          
       }
       //s = br.readLine();
       br.close();
       
      }
      catch (IOException ex){}
      map = new Tile[18][18];
       for(int i = 0; i < 18; i++){
        if(s[i].length() != 18) System.out.print("line is too short");
        for(int j = 0; j < 18; j++){
          String key = s[j].substring(i,i+1);
          if(key.equals("n")){map[j][i] = new NormalTile(i*30,j*30);}
          else if(key.equals("s")){map[j][i] = new SpikeTile(i*30,j*30,1);}
          else if(key.equals("w")){map[j][i] = new WallTile(i*30,j*30);}
          else if(key.equals("l")){map[j][i] = new LavaTile(i*30,j*30);}
          else if(key.equals("f")){map[j][i] = new FalseNormal(i*30,j*30);}
          else {map[j][i] = new WaterTile(i*30,j*30);}
        }
      }
      return map;
    }
    
    public static File[][] setFile(File f){
      File[][] ans;
      ArrayList<String> s = new ArrayList<String>();
      FileReader fr;
      BufferedReader br;
      String wait;
      try{
        fr = new FileReader(f);
        br = new BufferedReader(fr);
        wait = br.readLine();
        while(wait != null){
          s.add(wait);
          wait = br.readLine();
        }
        br.close();
      }
       catch (IOException ex){}
      ans = new File[s.get(0).length()/7][s.size()];
      for(int i = 0; i < ans.length; i++){
        for(int j = 0; j < ans[0].length; j++){
          String t = s.get(i);
          ans[i][j] = new File(t.substring(0,7));
          s.set(i,t.substring(7));
        }
      }
      return ans;
    }
    
    public static void roomen(ArrayList<Enemy> b, File f){
      for(int i = b.size()-1; i >= 0; i--){b.remove(i);}
      ArrayList<String> s = new ArrayList<String>();
      FileReader fr;
      BufferedReader br;
      String wait;
      try{
        fr = new FileReader(f);
        br = new BufferedReader(fr);
        wait = br.readLine();
        while(wait != null){
          s.add(wait);
          wait = br.readLine();
        }
        br.close();
      }
       catch (IOException ex){}
      
      for(int i = 0; i < s.size(); i++){
        String type = s.get(i).substring(0,1);
        int x = Integer.parseInt(s.get(i).substring(1,4));
        int y= Integer.parseInt(s.get(i).substring(4,7));
        if(type.equals("e")) b.add(new Enemy(x,y,4,4));
        if(type.equals("b")) b.add(new Boss(x,y,40,40,2,10));
        }
    }
    
    public static void setInFile(String s,File f){
      try{
      BufferedWriter bw = new BufferedWriter(new FileWriter(f));
      //bw.newLine();
      bw.write(s);
      bw.close();
    }
    catch(IOException e){}
    }
}
