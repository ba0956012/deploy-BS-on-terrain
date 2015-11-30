import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;


import javafx.geometry.Point3D;



public class main_test {


	public static void main(String[] args) throws IOException, InterruptedException {  
		Read_line RL = new Read_line();
		RL.simple(21, 120, 23);
		
		Read_TIN RT = new Read_TIN();
		RT.tinFile("N23.tin", "Output_JAVA_N23.tin");
		
		Deploy_BS_on_Terrain DBT= new Deploy_BS_on_Terrain();
		
		
		DBT.Deploy("Output_JAVA_N23.tin", "testline.txt" , 
		 1200,
		 1200,
		 119.999583333333,//左下角x經度
		 22.999583333333,//左下角x緯度
		 0.000833333333, 
		 100.0);
		
		//TL = RT.read("JAVA_N23.txt");"path.txt"
		

		
	}
	
	
}
