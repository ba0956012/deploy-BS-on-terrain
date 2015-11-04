import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircleLine {



	    public static List<Point2D.Double> getCircleLineIntersectionPoint(Point2D.Double pointA,
	            Point2D.Double pointB, Point2D.Double center, double radius) {
	        double baX = pointB.x - pointA.x;
	        double baY = pointB.y - pointA.y;
	        double caX = center.x - pointA.x;
	        double caY = center.y - pointA.y;

	        double a = baX * baX + baY * baY;
	        double bBy2 = baX * caX + baY * caY;
	        double c = caX * caX + caY * caY - radius * radius;

	        double pBy2 = bBy2 / a;
	        double q = c / a;

	        double disc = pBy2 * pBy2 - q;
	        if (disc < 0) {
	            return Collections.emptyList();
	        }
	        
	        ArrayList<Point2D.Double> LS = new ArrayList<Point2D.Double>();
	        
	        // if disc == 0 ... dealt with later
	        double tmpSqrt = Math.sqrt(disc);
	        double abScalingFactor1 = -pBy2 + tmpSqrt;
	        double abScalingFactor2 = -pBy2 - tmpSqrt;

	        Point2D.Double p1 = new Point2D.Double(pointA.x - baX * abScalingFactor1, pointA.y
	                - baY * abScalingFactor1);
	        if (disc == 0) { // abScalingFactor1 == abScalingFactor2
	            return Collections.singletonList(p1);
	        }
	       
	        
	        Point2D.Double p2 = new Point2D.Double(pointA.x - baX * abScalingFactor2, pointA.y
	                - baY * abScalingFactor2);
	        
	       //return Arrays.asList(p1,p2);
	        
	        if((p1.getX()>=pointA.getX()&&p1.getX()<=pointB.getX())||(p1.getX()<=pointA.getX()&&p1.getX()>=pointB.getX()))
	        	LS.add(p1);
	        if((p2.getX()>=pointA.getX()&&p2.getX()<=pointB.getX())||(p2.getX()<=pointA.getX()&&p2.getX()>=pointB.getX()))
	        	LS.add(p2);
	        
	        return LS;
	        
	    }




}
