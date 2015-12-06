import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Read_line {

	
	 public static  ArrayList<java.awt.geom.Line2D.Double> read(String FileName, Coordinate_Transform CT) throws IOException{
	
		 ArrayList<Line2D.Double> l = new ArrayList<Line2D.Double>();
		 FileReader fr = new FileReader(FileName);
		 BufferedReader br = new BufferedReader(fr);
		 String str = null;
		 int j=0;
		 Line2D.Double tmp_line = new Line2D.Double();
		 while((str = br.readLine())!= null){
			   if(j%2==0){
				String tempArray[]  = str.split(" ");//分割空格     			
				tmp_line.x1=Double.valueOf(tempArray[0]);
				tmp_line.y1=Double.valueOf(tempArray[1]);
				tmp_line.x1 = CT.to_grid(Double.valueOf(tempArray[0]), Double.valueOf(tempArray[1])).getX();
				tmp_line.y1 = CT.to_grid(Double.valueOf(tempArray[0]), Double.valueOf(tempArray[1])).getY();
				j++;
			   }
			   else{
				   String tempArray[]  = str.split(" ");//分割空格     
				   tmp_line.x2 = Double.valueOf(tempArray[0]);
				   tmp_line.y2 = Double.valueOf(tempArray[1]);
				   tmp_line.x2 = CT.to_grid(Double.valueOf(tempArray[0]), Double.valueOf(tempArray[1])).getX();
				tmp_line.y2 = CT.to_grid(Double.valueOf(tempArray[0]), Double.valueOf(tempArray[1])).getY();
			   l.add((java.awt.geom.Line2D.Double) tmp_line.clone());
			   j=0;
			   }
		 }
		 
		 
        for(int i=0;i<l.size();i++)	 
        {	
        //	System.out.println(l.get(i).getX1()+" "+l.get(i).getY1()+" "+l.get(i).getX2()+" "+l.get(i).getY2());
        }
	 
	  return l;
	 }
	
	 public void simple(int i, double left,  double down) throws IOException{
		 FileWriter FW = new FileWriter("testline.txt"); 
		 double z;
	 for(int j = 0 ; j<=i;j++){
		 
		 if(j==i){
			 z = Math.random();
			 z = (int)(Math.random()*10000);
			 z = z/10000;
			 double x = left + z; 
			 
			 z = Math.random();
			 z = (int)(Math.random()*10000);
			 z = z/10000;
			 double y = down + z;
			
			 FW.write(String.valueOf(x)+" "+String.valueOf(y));
		 }
		 
		 else{
			 z = Math.random();
			 z = (int)(Math.random()*10000);
			 z = z/10000;
			 double x = left + z; 
			 
			 z = Math.random();
			 z = (int)(Math.random()*10000);
			 z = z/10000;
			 double y = down + z;
		 
			 FW.write(String.valueOf(x)+" "+String.valueOf(y)+"\r\n");
			 
		 }
		 
	  
	 
	 }
	  FW.close();
	 }


}
