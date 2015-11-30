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
		
		MaP.p1 = p1;
		MaP.p2 = p2;
		MaP.p3 = p3;
		
		MaP.plan_p1.x = p1.getX();
		MaP.plan_p1.y = p1.getY();
		MaP.plan_p2.setLocation( project1(p1.distance(p2),p1,p2));
		
		
		
		//MaP.plan_p2.x = project1(p1.distance(p2),p1,p2).getX();
		//MaP.plan_p2.y = project1(p1.distance(p2),p1,p2).getY();
		//System.out.println("plan_p2:"+MaP.plan_p2.getX()+","+MaP.plan_p2.getY());
		
		MaP.plan_p3 = project1(p1.distance(p3),p1,p3);
		
		
		//System.out.println("plan_p3:"+MaP.plan_p3.getX()+","+MaP.plan_p3.getY());
		/*
		System.out.println("distance between p1 p2 :" + MaP.p1.distance(p2)+" "+ MaP.plan_p1.distance(MaP.plan_p2));
		System.out.println("distance between p1 p3 :" + MaP.p1.distance(p3)+" "+ MaP.plan_p1.distance(MaP.plan_p3));
		System.out.println("distance between p3 p2 :" + MaP.p3.distance(p2)+" "+ MaP.plan_p3.distance(MaP.plan_p2));
		System.out.println("p1:"+MaP.p1.getX()+","+MaP.p1.getY()+","+MaP.p1.getZ());
		System.out.println("p2:"+MaP.p2.getX()+","+MaP.p2.getY()+","+MaP.p2.getZ());
		System.out.println("p3:"+MaP.p3.getX()+","+MaP.p3.getY()+","+MaP.p3.getZ());
		System.out.println("p1.distance(p3):"+MaP.p1.distance(MaP.p3));
		*/
		//Point3D pp1 =new Point3D(p1.getX(),p1.getY(),p1.getZ());
		//Point3D pp2 =new Point3D(p2.getX(),p2.getY(),p1.getZ());
		//Point3D pp3 =new Point3D(p3.getX(),p3.getY(),p1.getZ());
		
		
		/*
		System.out.println("angle2:"+pp1.angle(pp2, pp3));
		System.out.println("angle22:"+p1.angle(p2, p3));
		*/
		//double a = p1.angle(p2, p3)-pp1.angle(pp2, pp3);
		
		
		
		
		//angle(MaP.plan_p2.getX(), MaP.plan_p2.getY(), MaP.plan_p1.getX(), MaP.plan_p1.getY(), MaP.plan_p3.getX(), MaP.plan_p3.getY())
		//-p1.angle(p2, p3);
		MaP.plan_p3 = 
		rotation(MaP.plan_p2.getX(),MaP.plan_p2.getY(),MaP.plan_p1.getX(),MaP.plan_p1.getY(),  p1.angle(p2, p3));
		
		Point3D aa = new Point3D(MaP.plan_p3.x,MaP.plan_p3.y,p1.getZ());
		
		MaP.plan_p3 = 
		project1(p1.distance(p3), p1, aa);
		
		
		//System.out.println("**distance between p1 p2 :" + MaP.p1.distance(p2)+" "+ MaP.plan_p1.distance(MaP.plan_p2));
		//System.out.println("**distance between p3 p1 :" + MaP.p3.distance(p1)+" "+ MaP.plan_p3.distance(MaP.plan_p1));
		//System.out.println("**distance between p3 p2 :" + MaP.p3.distance(p2)+" "+ MaP.plan_p3.distance(MaP.plan_p2));
		
		
		return MaP;
		
	}
	
	
	public  Line2D.Double project(Line3D l){
	Line2D.Double L = new Line2D.Double();
	
	L.setLine(
	project(l.origin()).getX(),
	project(l.origin()).getY(),
	project(l.end()).getX(),
	project(l.end()).getY()
	);
	
	//System.out.println("l:"+l.origin() +","+l.end());
	//System.out.println("L:"+L.getX1()+","+L.getY1()+","+L.getX2()+","+L.getY2());
	return L;
	}
	
	public Point2D.Double project(Point3D p){
		Point2D.Double P = new Point2D.Double();
		
		double d = this.p1.distance(p);
		
		//System.out.println("106: " + d);
		//System.out.println("107: " + this.p2.distance(p));
		
		if(d > 0)
		{	
		//this.p1.angle(this.p2, p);
			//System.out.println("112: " + this.p1.angle(this.p2, p));
			
		P=
		rotation(this.plan_p2.getX(),this.plan_p2.getY(),this.plan_p1.getX(),this.plan_p1.getY(),  this.p1.angle(this.p2, p));
		
		//System.out.println("rotation:"+ P.getX()+","+P.getY());
		
		Point3D aa = new Point3D(P.x,P.y,p1.getZ());
		
		//System.out.println("aa :"+ aa.getX()+","+aa.getY());
		//System.out.println("d :"+ d);
		P=
		project1(d, p1, aa);	
		//MaP.plan_p3 = rotation(MaP.plan_p2.getX(),MaP.plan_p2.getY(),MaP.plan_p1.getX(),MaP.plan_p1.getY(),  p1.angle(p2, p3))
	
		return P;
		}
		
		
		
		else{
			P.x = this.p1.getX();
			P.y = this.p1.getY();
			return P;
		}
		
		
		
	}
	
	
	public Point2D.Double project(org.jlab.geom.prim.Point3D pp){
		
		Point2D.Double P = new Point2D.Double();
		Point3D p = new Point3D(pp.x(),pp.y(),pp.z());
	if(this.p1.angle(this.p2, p)>=0)	
	{	
		double d = this.p1.distance(p);
		//this.p1.angle(this.p2, p);
		//System.out.println("151: " + this.p1.angle(this.p2, p));
		
		P=
		rotation(this.plan_p2.getX(),this.plan_p2.getY(),this.plan_p1.getX(),this.plan_p1.getY(),  this.p1.angle(this.p2, p));
		//System.out.println("155: " + P.getX()+","+P.getY());
		Point3D aa = new Point3D(P.x,P.y,p1.getZ());
		
		P=
		project1(d, p1, aa);	
		//MaP.plan_p3 = rotation(MaP.plan_p2.getX(),MaP.plan_p2.getY(),MaP.plan_p1.getX(),MaP.plan_p1.getY(),  p1.angle(p2, p3))
	
		return P;
	 }
	
	else{
		if(p.getX() == this.p1.getX() && p.getY() == this.p1.getY()){
		P.x = this.p1.getX();
		P.y = this.p1.getY();
		return P;
		}
		else{
			P.x = this.p2.getX();
			P.y = this.p2.getY();
			return P;
		}	
	}
	
	}
	
	public static Point2D.Double project(double D, Point3D p, org.jlab.geom.prim.Point3D p1){ //D = distance of p to p1 , project point3D p1 to plan(point3D p and (p1.x ,p1.y, p.z) ) 
		Point2D.Double P = new Point2D.Double();
		
		//System.out.println("3D distance :"+ D);
		double dd = p.distance(p1.x(),p1.y(),p.getZ());
		//System.out.println("dd distance :"+ dd);
		
		
		if(D == 0)
		{
			P.x = p1.x();	
			P.y = p1.y();
			return P;
		}
		
		else if(p1.x()-p.getX() != 0)
		{
		 double d =  (p1.x()-p.getX()) / dd;
		 P.x = D * d + p.getX();
		 if(p1.y()-p.getY() == 0){
				P.y = p1.y();
				  if( P.distance(p.getX(),p.getY())!=p.distance(P.getX(),P.getY(),p.getZ()))
				  {
					  System.out.println("********************************************"); 
					 
				  }
				return P;
		 }
		 else{	  
			  d =  (p1.y()-p.getY()) / dd;
			  P.y = D * d + p.getY();
			 // System.out.println("P:" + P.getX()+" "+P.getY());
			 // System.out.println("P distance:" + P.distance(p.getX(),p.getY()));
			 // System.out.println("Pp distance:" + p.distance(P.getX(),P.getY(),p.getZ()));
			  if( P.distance(p.getX(),p.getY())!=p.distance(P.getX(),P.getY(),p.getZ()))
			  {
				  System.out.println("********************************************"); 
				
			  }
			  return P;
		 }
		
		}
		else{
			P.x = p1.x();
			double d =  (p1.y()-p.getY()) / dd;
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
		
		//System.out.println("3D distance :"+ D);
		double dd = p.distance(p1.getX(),p1.getY(),p.getZ());
		
		
		//System.out.println("dd distance :"+ dd);
		//System.out.println("TIN p: " + p.getX()+","+p.getY()+","+p.getZ());
		//System.out.println("269 p1 : " + p1.getX()+","+p1.getY()+","+p.getZ());
		
		if(D == 0.0)
		{
			P.x = p1.getX();	
			P.y = p1.getY();
			return P;
		}
		
		else if(p1.getX()-p.getX() != 0)
		{
		 double d =  (p1.getX()-p.getX()) / dd;
		 P.x = D * d + p.getX();
		 if(p1.getY()-p.getY() == 0){
				P.y = p1.getY();
				return P;
		 }
		 else{	  
			  d =  (p1.getY()-p.getY()) / dd;
			  P.y = D * d + p.getY();
			  //System.out.println("P:" + P.getX()+" "+P.getY());
			  //System.out.println("P distance:" + P.distance(p.getX(),p.getY()));
			 // System.out.println("Pp distance:" + p.distance(P.getX(),P.getY(),p.getZ()));
			  return P;
		 }
		
		}
		else{
			P.x = p1.getX();
			double d =  (p1.getY()-p.getY()) / dd;
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
	
	public Point2D.Double rotation(double x, double y, double h, double k, double angle){
		
		//System.out.println("316: "+ x +","+y+","+h+","+k+","+angle);
		
		Point2D.Double P = new Point2D.Double(); 
		double x1, y1;
		x = x - h;
		y = y - k;
		angle = Math.toRadians(angle);
		x1 = x * Math.cos(angle) + y* Math.sin(angle) + h;
		// i=x1*point;
		// x1=i/point;
		y1 = -1 * x * Math.sin(angle) + y* Math.cos(angle) + k;
		P.x = x1;
		P.y = y1;
		
		
		//P.x = x + (x-h)*Math.cos(angle) - (y-k)*Math.sin(angle);

		//P.y = y + (x-h)*Math.sin(angle) + (y-k)*Math.cos(angle);
		
		
	 return P;
	}
	
	
	
}
