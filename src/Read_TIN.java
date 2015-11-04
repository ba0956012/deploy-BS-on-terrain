import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;

import javafx.geometry.Point3D;


public class Read_TIN {
	
 public class TIN_List{
	 ArrayList<TIN_Point> p_List = new ArrayList<TIN_Point>();
     ArrayList<Triangel> t_List = new ArrayList<Triangel>(); 
 
     public ArrayList<TIN_Point> get_pList(){
    	return this.p_List; 
     }
     
     public ArrayList<Triangel> get_tList(){
     	return this.t_List; 
      }
 
 }


	
 
 public TIN_List read(String FileName) throws IOException{
	 
	 int Number_of_Triangel = 0;
	 int Number_of_Point = 0;
	 
	 FileReader fr = new FileReader(FileName);
     BufferedReader br = new BufferedReader(fr);
     String str = null;
     
     ArrayList<TIN_Point> p = new ArrayList<TIN_Point>();
     ArrayList<Triangel> t = new ArrayList<Triangel>();
     
     int c=0;
     int index_triangel = 0;
     
		while((str = br.readLine())!= null){
			    				
			
			String tempArray[]  = str.split(" |\\	");//���γr�� 
			if(c==0){
				Number_of_Point = Integer.valueOf(tempArray[0]);
				Number_of_Triangel = Integer.valueOf(tempArray[1]);
			c++;
			}
			
			//if(c>0&&c<=Number_of_Point){
			//	String tempArray[]  = str.split("	\\ ");//���γr�� 
			else if(c>0&&c<=Number_of_Point){
				//System.out.println(tempArray[1]);
				TIN_Point tmp_point = new TIN_Point(Double.valueOf(tempArray[0]),Double.valueOf(tempArray[1]), Double.valueOf(tempArray[2]));
				tmp_point.id = c;
				//tmp_point.add(Double.valueOf(tempArray[0]), Double.valueOf(tempArray[1]), Double.valueOf(tempArray[2]));
				
			    p.add(tmp_point.clone());
			    c++;
			}
			
			//if(c>Number_of_Point){
			//	String tempArray[]  = str.split("	\\ ");
			else if(c>Number_of_Point){
				Triangel tmp_Triangel = new Triangel();  
			    tmp_Triangel.id = index_triangel;
			    index_triangel++;
			    int[] tmp_p_id = new int[3];
			    tmp_p_id[0] = Integer.valueOf(tempArray[0]);
			    tmp_p_id[1] = Integer.valueOf(tempArray[1]);
			    tmp_p_id[2] = Integer.valueOf(tempArray[2]);
			    tmp_Triangel.p_id = tmp_p_id;
			    t.add(tmp_Triangel);
			    c++;
			}
				
		}
		
		TIN_List TL = new TIN_List();
		TL.p_List = p;
		//System.out.println(Number_of_Point);
		//System.out.println(t);
		TL.t_List = t;
		
		return TL;
		
 }
		 
}

 
		
		
		
		