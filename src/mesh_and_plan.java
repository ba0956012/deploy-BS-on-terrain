import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.jlab.geom.prim.Line3D;

import javafx.geometry.Point3D;


public class mesh_and_plan {

	
		TIN_Point p1 ; 
		TIN_Point p2 ;
		TIN_Point p3 ;
		Point2D.Double plan_p1 = new Point2D.Double();
		Point2D.Double plan_p2 = new Point2D.Double();
		Point2D.Double plan_p3 = new Point2D.Double();
	
	
	
	public  mesh_and_plan mesh_to_plan(TIN_Point p1, TIN_Point p2, TIN_Point p3){
		mesh_and_plan MaP = new mesh_and_plan();
		
		Point2D.Double plan_p1 = new Point2D.Double();
		Point2D.Double plan_p2 = new Point2D.Double();
		Point2D.Double plan_p3 = new Point2D.Double();
		MaP.p1 = p1;
		MaP.p2 = p2;
		MaP.p3 = p3;
		
		plan_p1.x = p1.getX();
		plan_p1.y = p1.getY();
		plan_p2 = project1(p1.distance(p2),p1,p2);
		plan_p3 = project1(p1.distance(p3),p1,p3);
		
		System.out.println("p1.distance(p3):"+p1.distance(p3));
		
		MaP.plan_p1 = plan_p1;
		MaP.plan_p2 = plan_p2;
		MaP.plan_p3 = plan_p3;
		System.out.println("MaP.plan_p3 :"+MaP.plan_p3);
		return MaP;
		
	}
	
	
	public  Line2D.Double project(Line3D l){
	Line2D.Double L = new Line2D.Double();
	L.setLine(
	project(this.p1.distance(l.origin()), this.p1, l.origin()).getX(),
	project(this.p1.distance(l.origin()), this.p1, l.origin()).getY(),
	project(this.p1.distance(l.end()), this.p1, l.end()).getX(),
	project(this.p1.distance(l.end()), this.p1, l.end()).getY()
	);
	return L;
	}
	
	public static Point2D.Double project(double D, Point3D p, org.jlab.geom.prim.Point3D p1){ //D = distance of p to p1 , project point3D p1 to plan(point3D p and (p1.x ,p1.y, p.z) ) 
		Point2D.Double P = new Point2D.Double();
		double d = p.distance(p1.x(), p1.y(), p.getZ());
		
		if(d == 0)
		{
			P.x = p1.x();	
			P.y = p1.y();
			return P;
		}
		
		else if(p1.x()-p.getX() != 0)
		{
		 d =  (p1.x()-p.getX()) / d;
		 
		 if(p1.y()-p.getY() == 0){
				P.y = p1.y();
		 P.x = D * d + p.getX();
		 return P;
		 }
		 else{
			  P.x = D * d + p.getX();
			  d =  (p1.y()-p.getY()) / d;
			  P.y = D * d + p.getY();
			  return P;
		 }
		
		}
		else{
			P.x = p1.x();
			d =  (p1.y()-p.getY()) / d;
			P.y = D * d + p.getY();
			return P;
		}
		/*
		Point2D.Double P = new Point2D.Double();
		double s;
		int ss=0;
		s= D*D/( (point3d.x()*point3d.x()-2*point3d.x()*p.getX()+point3d.x()*point3d.x())
				+(point3d.y()*point3d.y()-2*point3d.y()*p.getY()+point3d.y()*point3d.y()) );
		
		//System.out.println("s:"+s);
		if(s<0){
			s=s*-1.0;
			ss=1;
		}
		
		s = Math.sqrt(s);
		//System.out.println("s.sqrt:"+s);
		if(ss==1)
			s=s*-1.0;
		
		P.x=  p.getX()+point3d.x()*s;
		//System.out.println("P.x:"+P.x);
		//tmp =  (int)(P.x*point);
		//P.x = tmp/point;
		
		P.y = p.getY()+point3d.y()*s;
		//P.y = ((int)(P.y*point))/point;
		
		//System.out.println("project:"+P.x+","+P.y);
		return P;
		*/
	}
	
	public static Point2D.Double project1(double D, TIN_Point p, Point3D p1){ //D = distance of p to p1 , project point3D p1 to plan(point3D p and (p1.x ,p1.y, p.z) ) 
		Point2D.Double P = new Point2D.Double();
		double d = p.distance(p1.getX(), p1.getY(), p.getZ());
		
		if(p1.getX()-p.getX() != 0)
		{
		 d =  (p1.getX()-p.getX()) / d;
		 
		 if(p1.getY()-p.getY() == 0){
				P.y = p1.getY();
		 P.x = D * d + p.getX();
		 return P;
		 }
		 else{
			  P.x = D * d + p.getX();
			  d =  (p1.getY()-p.getY()) / d;
			  P.y = D * d + p.getY();
			  return P;
		 }
		
		}
		else{
			P.x = p1.getX();
			d =  (p1.getY()-p.getY()) / d;
			P.y = D * d + p.getY();
			return P;
		}
						
		/*
		Point2D.Double P = new Point2D.Double();
		double s;
		int ss=0;
		
		if( ( (p1.getX()*p1.getX()-2*p1.getX()*p.getX()+p1.getX()*p1.getX())
				+(p1.getY()*p1.getY()-2*p1.getY()*p.getY()+p1.getY()*p1.getY())) ==0 ){
					s=1;
					P.x=  p.getX()+p1.getX()*s;
					P.y = p.getY()+p1.getY()*s;
					System.out.println("P1:"+p1.getX()+","+p1.getY());
					System.out.println("P:"+p.getX()+","+p.getY());
					return P;
				}
		
		s= D*D/( (p1.getX()*p1.getX()-2*p1.getX()*p.getX()+p1.getX()*p1.getX())
				+(p1.getY()*p1.getY()-2*p1.getY()*p.getY()+p1.getY()*p1.getY()) );
		if(s<0){
			s=s*-1.0;
			ss=1;
		}
		
		s = Math.sqrt(s);
		
		if(ss==1)
			s=s*-1.0;
		
		P.x=  p.getX()+p1.getX()*s;
		P.y = p.getY()+p1.getY()*s;
		
		return P;
		*/
	}
	
}
