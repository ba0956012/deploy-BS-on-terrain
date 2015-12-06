
import java.awt.geom.Point2D;
import java.util.ArrayList;



public class Find_Zcoordinate {
	
	public static double getZ(double x, double y, ArrayList<TIN_Point> list){
		double z = 0;
		for(int i=0;i<list.size();i++){
			if(x==list.get(i).getX()&&y==list.get(i).getY())
				z = list.get(i).getZ();
		}
	return z;
	}

	public static Point2D.Double intersection(
			double x1,double y1,double x2,double y2, 
			double x3, double y3, double x4,double y4
			  ) {
		double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
			    if (d == 0) return null;
			    
			    double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
			    double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;
			    
			    return new Point2D.Double (xi,yi);
			  }
	
	
	/*
	static double calcY(TIN_Point p1, TIN_Point p2, TIN_Point p3, double x, double y) {
		

		Point2D.Double intersection_point = new Point2D.Double();
		intersection_point = intersection(p1.getX(), p1.getY(), p2.getX(), p2.getY(),p3.getX(), p3.getY(), x, y);
		
		double d, dd;
		d = Point2D.distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		dd = Point2D.distance(p1.getX(), p1.getY(), intersection_point.getX(), intersection_point.getY());
				
		double z;
		z= p1.getZ()+((p2.getZ()-p1.getZ())/d)*dd;
		
		Double.parseDouble(String.valueOf(Float.parseFloat(String.valueOf(intersection_point.getX())))); 
		Double.parseDouble(String.valueOf(Float.parseFloat(String.valueOf(intersection_point.getY()))));
				
		Float.parseFloat(String.valueOf(intersection_point.getX()));
			
		d = Point2D.distance(p3.getX(), p3.getY(),
				Double.parseDouble(String.valueOf(Float.parseFloat(String.valueOf(intersection_point.getX())))),
				Double.parseDouble(String.valueOf(Float.parseFloat(String.valueOf(intersection_point.getX()))))); 	
		dd = Point2D.distance(p3.getX(), p3.getY(), x, y);
		z = p3.getZ()+((z-p3.getZ())/d)*dd;
								   		
		return z;
	}
	*/
	
	
	static double calcY(TIN_Point p1, TIN_Point p2, TIN_Point p3, double x, double y) {
	double z=0;
	//ªk¦V¶q
	   double vp1 = ( (p2.getY()-p1.getY())*(p3.getZ()-p1.getZ())-(p2.getZ()-p1.getZ())*(p3.getY()-p1.getY()) );  	  
	   double vp2 = ( (p2.getZ()-p1.getZ())*(p3.getX()-p1.getX())-(p2.getX()-p1.getX())*(p3.getZ()-p1.getZ()) ); 
	   double vp3 = ( (p2.getX()-p1.getX())*(p3.getY()-p1.getY())-(p2.getY()-p1.getY())*(p3.getX()-p1.getX()) ); 
	   double n1 = p1.getX();
	   double n2 = p1.getY();
	   double n3 = p1.getZ();
	   double m1 = x;
	   double m2 = y;
	   double m3 = 10;
	   double v1 = 0;
	   double v2 = 0;
	   double v3 = 10;
	   double vpt = v1 * vp1 + v2 * vp2 + v3 * vp3; 
	   if (vpt == 0)  
	   {  
		   System.out.println("vpt == 0");
		   System.exit(0);;
	   return 0;  
	   } 
	//
	   else{
		   
		   double t = ((n1 - m1) * vp1 + (n2 - m2) * vp2 + (n3 - m3) * vp3) / vpt; 
		   if(m3 + v3 * t<0){
			   System.out.println("p1: "+ p1.getX()+","+p1.getY()+","+p1.getZ());
			   System.out.println("p2: "+ p2.getX()+","+p2.getY()+","+p2.getZ());
			   System.out.println("p3: "+ p3.getX()+","+p3.getY()+","+p3.getZ());
			   System.out.println("XY: "+ x+","+y);
			   System.out.println("t: "+ t);
			   System.out.println(m3 + v3 * t);
		   
		   }
		   return m3 + v3 * t;   
	   }
	    
	}
	
}