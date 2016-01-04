import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;


import javafx.geometry.Point3D;



public class main {


	public static void main(String[] args)throws IOException, InterruptedException {  
	/*
	String InputTIN = args[0];
	String OutputTIN = args[1];
	String line = args[2];
	int ncols =  Integer.valueOf(args[3]);
	
			int ycols = Integer.valueOf(args[4]);
			
			double xllcorner = Double.valueOf(args[5]);//左下角x經度
			double yllcorner = Double.valueOf(args[6]);//左下角x緯度
			double cellsize =  Double.valueOf(args[7]); 
			double r = Double.valueOf(args[8]);
		*/
		
		Read_line RL = new Read_line();
		RL.simple(20, 119.999583, 22.999583);
		
		
		
		Read_TIN RT = new Read_TIN();
		
		System.out.println("Read TIN");
		RT.tinFile("30.tin", "out.tin");
		//RT.tinFile(InputTIN, OutputTIN);
		
		
		Deploy_BS_on_Terrain DBT= new Deploy_BS_on_Terrain();
		/*
		
		DBT.Deploy(OutputTIN, line , 
				ncols,
				ycols,
				xllcorner,//左下角x經度
				yllcorner,//左下角x緯度
				cellsize, 
				r);
			*/
		
		DBT.Deploy("out.tin" , "testline.txt", 1200,1200,119.9995833333, 22.995833333, 0.000833333, 10000);
		
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
		
		//System.out.println("CT :");
		Point2D.Double p = new Point2D.Double();
		Coordinate_Transform CT = new Coordinate_Transform(); //.asc之參數
	    CT.cellsize = 0.0008333333333333;
	    CT.xllcorner = 119.999583333333333;
	    CT.yllcorner = 22.999583333333333;    
    	CT.ycols = 1200;
    	CT.ncols = 1200;
		
		p = CT.convert(0, 0);
	//	System.out.println(p.getX()+" , "+p.getY());
	}
	
	
}
