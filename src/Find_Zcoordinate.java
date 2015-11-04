
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
}