import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;


import javafx.geometry.Point3D;



public class main_test {


	public static void main(String[] args) throws IOException, InterruptedException {  
		
		//Read_line RL = new Read_line();
		//RL.simple(15, 119.999583, 22.999583);
		
		Read_TIN RT = new Read_TIN();
		RT.tinFile("C://Users//sclab//Desktop//N23//30.tin", "Output_JAVA_N23_30.tin");
		
		Deploy_BS_on_Terrain DBT= new Deploy_BS_on_Terrain();
		
		
		DBT.Deploy("Output_JAVA_N23_30.tin", "testline.txt" , 
		 1200,
		 1200,
		 119.999583333333333,//左下角x經度
		 22.999583333333333,//左下角x緯度
		 0.0008333333333333, 
		 30000.0);
		
		//TL = RT.read("JAVA_N23.txt");"path.txt"
		
		/*
		f1_x = 5;
		f1_y = -7;
		f2_x = 3;
		f2_y = 10;
		*/
		//mesh_and_plan m = new mesh_and_plan();
		/*
		Point2D.Double p1 = new Point2D.Double(0.0 , 0.0);
		Point2D.Double p2 = new Point2D.Double(5 , 3);
		Point2D.Double p3 = new Point2D.Double(-7 , 10);
		*/
		
		/*
		Point2D.Double p1 = new Point2D.Double(0.0 , 111319.49079326923);
		Point2D.Double p2 = new Point2D.Double(21530.08774482246 , 102223.62699703676);
		Point2D.Double p3 = new Point2D.Double(37484.28934727446 , 73550.77624498715);
		
		
		
		m.plan_p1 = p1;
		m.plan_p2 = p2;
		m.plan_p3 = p3;
		
		TIN_Point TP1 = new TIN_Point(0.0 , 111319.49079326923 , 1107.0);
		TIN_Point TP2 = new TIN_Point(21518.79221347438 , 102228.39904515221 , 350.0);
		TIN_Point TP3 = new TIN_Point(37487.09579022332 , 73563.63016588544 , 235.0);
		
		

		m.p1 = TP1;
		m.p2 = TP2;
		m.p3 = TP3;
		
		Point2D.Double p = new Point2D.Double(35223.89150211568,77613.19109986647);
		Point2D_to_Point3D.Calcute(m, p);
		
		//Point2D_to_Point3D.Calcute(m.p1,m.p2,m.p3,p1.distance(p),m.plan_p2.distance(p),m.plan_p3.distance(p));
		
		Line2D.Double l = new Line2D.Double() ;
		l.setLine(1.0, 1.0, 3.0, 3.0);
		System.out.println(l.ptLineDistSq(2.0, 2.0));
		System.out.println(l.ptLineDistSq(1.0, 2.0));
		*/
		
	}
	
	
}
