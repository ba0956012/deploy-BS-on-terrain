import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;


import javafx.geometry.Point3D;



public class main_test {


	public static void main(String[] args) throws IOException, InterruptedException {  
		Deploy_BS_on_Terrain DBT= new Deploy_BS_on_Terrain();
		DBT.Deploy("JAVA_N23.txt", "path.txt" , 
		 1200,
		 1200,
		 119.999583333333,//左下角x經度
		 22.999583333333,//左下角x緯度
		 0.000833333333, 
		 100.0);
		
		//TL = RT.read("JAVA_N23.txt");"path.txt"
		
	}
	
	
}
