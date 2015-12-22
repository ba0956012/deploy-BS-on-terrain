

import java.awt.geom.Point2D;

import javafx.geometry.Point3D;

public class Point2D_to_Point3D {
/*
public static Point3D Calcute(Point3D p1, Point3D p2, Point3D p3, double d1, double d2, double d3){
	
	//System.out.println("p1:"+p1.getX()+","+p1.getY()+","+p1.getZ()+","+p1.distance(p2));
	//System.out.println("p2:"+p2.getX()+","+p2.getY()+","+p2.getZ()+","+ p2.distance(p3));
	//System.out.println("p3:"+p3.getX()+","+p3.getY()+","+p3.getZ()+","+ p3.distance(p1));
	//System.out.println("d:"+d1+" "+d2+" "+d3);

	double tolerance = 1.001;
	
	boolean flag1 = p1.distance(p2) > d1 + d2;
/*	
	if(flag1){
		double  ep =  p1.distance(p2) - d1 + d2;
		d2 = d2 + ep*tolerance;
		flag1 = p1.distance(p2) > d1 + d2;
	}
	
	boolean flag2 = p2.distance(p3) > d2 + d3;
	
	/*
	if(flag2){
		double  ep =  p2.distance(p3) - d2 + d3;
		d2 = d2 + ep*tolerance;
		flag2 = p2.distance(p3) > d2 + d3;
	}
	
	boolean flag3 = p3.distance(p1) > d1 + d3;
	/*
	if(flag3){
		double  ep =  p3.distance(p1) - d1 + d3;
		d3 = d3 + ep*tolerance;
		flag3 = p3.distance(p1) > d1 + d3;
	}
	
	//System.out.println("flag:" + flag1+" "+flag2+" "+flag3);
	
	if (flag1 || flag2 || flag3) {
	RuntimeException e = new RuntimeException("参数异常无法求解");
	//System.out.println("p1:"+p1.getX()+","+p1.getY()+","+p1.getZ()+","+p1.distance(p2));
	//System.out.println("p2:"+p2.getX()+","+p2.getY()+","+p2.getZ()+","+ p2.distance(p3));
	//System.out.println("p3:"+p3.getX()+","+p3.getY()+","+p3.getZ()+","+ p3.distance(p1));
	//System.out.println("d:"+d1+" "+d2+" "+d3);
	//System.out.println(p1.distance(p2)+","+p2.distance(p3)+","+p3.distance(p1));
	//System.out.println("flag1:" + p1.distance(p2)+" "+(d1+d2));
	//System.out.println("flag2:" + p2.distance(p3)+" "+(d2+d3));
	//System.out.println("flag3:" + p3.distance(p1)+" "+(d1+d3));
	//System.out.println("flag:" + flag1+" "+flag2+" "+flag3);

	throw e;
	}
	
	double k1 = p2.getX() * p2.getX() - p1.getX() * p1.getX() + p2.getY() * p2.getY() - p1.getY() * p1.getY() + p2.getZ() * p2.getZ() - p1.getZ() * p1.getZ() + d1 * d1 - d2 * d2;
	//System.out.println("k1:"+k1);
	if(k1==0) 
		{
			System.out.println("k1:"+k1);
			k1=tolerance;
		}
		
	double k2 = p3.getX() * p3.getX() - p1.getX() * p1.getX() + p3.getY() * p3.getY() - p1.getY() * p1.getY() + p3.getZ() * p3.getZ() - p1.getZ() * p1.getZ() + d1 * d1 - d3 * d3;
	if(k2==0) 
		{
			System.out.println("k2:"+k2);
			k2=tolerance;
		}
	
	double k3 = p3.getX() * p3.getX() - p2.getX() * p2.getX() + p3.getY() * p3.getY() - p2.getY() * p2.getY() + p3.getZ() * p3.getZ() - p2.getZ() * p2.getZ() + d2 * d2 - d3 * d3;
	if(k3==0) 
		{
			System.out.println("k3:"+k3);
			k3=tolerance;
		}
	
	double m1 = 2 * ((p2.getY() - p1.getY()) * (p3.getX() - p1.getX()) - (p3.getY() - p1.getY()) * (p2.getX() - p1.getX()));
	if(m1==0) 
		{
			System.out.println("m1:"+m1);
			m1=tolerance;
		}
	
	
	double m2 = 2 * ((p2.getY() - p1.getY()) * (p3.getX() - p2.getX()) - (p3.getY() - p2.getY()) * (p2.getX() - p1.getX()));
	if(m2==0) 
	{
		System.out.println("m2:"+m2);
		m2=tolerance;
	}
	
	double n1 = 2 * ((p2.getZ() - p1.getZ()) * (p3.getX() - p1.getX()) - (p3.getZ() - p1.getZ()) * (p2.getX() - p1.getX()));
	
	if(n1==0) 
	{
		System.out.println("n1:"+n1);
		n1=tolerance;
	}
	
	double n2 = 2 * ((p2.getZ() - p1.getZ()) * (p3.getX() - p2.getX()) - (p3.getZ() - p2.getZ()) * (p2.getX() - p1.getX()));
	if(n2==0) 
	{
		System.out.println("n2:"+n2);
		n1=tolerance;
	}
	
	double v1 = k1 * (p3.getX() - p1.getX()) - k2 * (p2.getX() - p1.getX());
	if(v1==0) 
	{
		System.out.println("v1:"+v1);
		v1=tolerance;
	}
	
	double v2 = k1 * (p3.getX() - p2.getX()) - k3 * (p2.getX() - p1.getX());
	if(v2==0) 
	{
		System.out.println("v2:"+v2);
		n1=tolerance;
	}
	
	double zz;
	System.out.println("z : "+(n1 * m2 - n2 * m1));
	if(n1 * m2 - n2 * m1==0)
	 zz = 0.000001;
	
	else
	zz	= n1 * m2 - n2 * m1;
	System.out.println("v1 * m2 - v2 * m1 : "+(v1 * m2 - v2 * m1));
	double z = (v1 * m2 - v2 * m1) /zz;
	if(z==0) 
	{
		System.out.println("z:"+z);
		z=tolerance;
	}
	
	double u1 = k1 * (p3.getY() - p1.getY()) - k2 * (p2.getY() - p1.getY());
	if(u1==0) 
	{
		System.out.println("u1:"+u1);
		u1=tolerance;
	}
	
	double s1 = 2 * ((p2.getZ() - p1.getZ()) * (p3.getY() - p1.getY()) - (p3.getZ() - p1.getZ()) * (p2.getY() - p1.getY()));
	if(s1==0) 
	{
		System.out.println("s1:"+s1);
		s1=tolerance;
	}
	
	double t1 = 2 * ((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()));
	if(t1==0) 
	{
		System.out.println("t1:"+t1);
		t1=tolerance;
	}
	
	double x = (u1 - s1 * z) / t1;
	if(x==0) 
	{
		System.out.println("x:"+x);
		x=tolerance;
	}
	
	double e1 = k1 * (p3.getZ() - p1.getZ()) - k2 * (p2.getZ() - p1.getZ());
	if(e1==0) 
	{
		System.out.println("e1:"+e1);
		e1=tolerance;
	}
	
	double q1 = 2 * ((p2.getX() - p1.getX()) * (p3.getZ() - p1.getZ()) - (p3.getX() - p1.getX()) * (p2.getZ() - p1.getZ()));

	
	double w1 = 2 * ((p2.getY() - p1.getY()) * (p3.getZ() - p1.getZ()) - (p3.getY() - p1.getY()) * (p2.getZ() - p1.getZ()));
	
	System.out.println("w1:"+w1);
	System.out.println("e1:"+e1);
	System.out.println("q1:"+q1);
	System.out.println("x:"+x);
	double y = (e1 - q1 * x) / w1;

	Point3D p = new Point3D(x, y, z);
	
	
	
	System.out.println("Point2D_to_Point3D : "+p.getX()+" "+p.getY()+" "+p.getZ());
	return p;
}

*/

public static Point3D Calcute(mesh_and_plan map , Point2D.Double P){
	//(mesh_and_plan map , Point2D.Double P)
	double f1_x = map.plan_p2.x-map.plan_p1.x;
	double f1_y = map.plan_p2.y-map.plan_p1.y;
	double f2_x = map.plan_p3.x-map.plan_p1.x;
	double f2_y = map.plan_p3.y-map.plan_p1.y;
	
	
	//System.out.println("f1x : "+f1_x);
	
	double a, b;
	
	
	
	double d = det(f1_x , f2_x, f1_y, f2_y); //det
	
	//System.out.println("det : "+d);
	
	f1_x = P.getX()-map.plan_p1.x;
	f1_y = P.getY()-map.plan_p1.y;
	
	double dx = det(f1_x , f2_x, f1_y, f2_y); // detx
	
	//System.out.println("detx : "+dx);
	
	f1_x = map.plan_p2.x-map.plan_p1.x;
	f1_y = map.plan_p2.y-map.plan_p1.y;
	f2_x = P.getX()-map.plan_p1.x;
	f2_y = P.getY()-map.plan_p1.y;
	
	double dy =det(f1_x , f2_x, f1_y, f2_y); // dety
	//System.out.println("dety : "+dy);
	
	a = dx/d;
	b = dy/d;
/*
	 f1_x = map.plan_p2.x-map.plan_p1.x;
	 f1_y = map.plan_p2.y-map.plan_p1.y;
	 f2_x = map.plan_p3.x-map.plan_p1.x;
	 f2_y = map.plan_p3.y-map.plan_p1.y;
*/
	//System.out.println("ab test: " +a + ","+b );
	//System.out.println("P 2D " + (a*f1_x+b*f2_x+map.plan_p1.x) +" , " + (a*f1_y+b*f2_y+map.plan_p1.y));
	
	
	 f1_x = map.p2.getX()-map.p1.getX();
	 f1_y = map.p2.getY()-map.p1.getY();
	 f2_x = map.p3.getX()-map.p1.getX();
	 f2_y = map.p3.getY()-map.p1.getY();
	double f1_z = map.p2.getZ()-map.p1.getZ();
	double f2_z = map.p3.getZ()-map.p1.getZ();
	/*
	System.out.println("map: ");
	System.out.println("p1 : " + map.p1.getX()+" , "+ map.p1.getY()+" , "+map.p1.getZ());
	System.out.println("p2 : " + map.p2.getX()+" , "+ map.p2.getY()+" , "+map.p2.getZ());
	System.out.println("p3 : " + map.p3.getX()+" , "+ map.p3.getY()+" , "+map.p3.getZ());
	
	System.out.println("plan: ");
	System.out.println("p1 : " + map.plan_p1.x+" , "+ map.plan_p1.y);
	System.out.println("p2 : " + map.plan_p2.x+" , "+ map.plan_p2.y);
	System.out.println("p3 : " + map.plan_p3.x+" , "+ map.plan_p3.y);
	
	System.out.println("distance: ");
	System.out.println("p1p2 : " + map.plan_p1.distance(map.plan_p2)+"   "+ map.p1.distance(map.p2.getX(),map.p2.getY(),map.p2.getZ()));
	System.out.println("p1p3 : " + map.plan_p1.distance(map.plan_p3)+"   "+ map.p1.distance(map.p3.getX(),map.p3.getY(),map.p3.getZ()));
	System.out.println("p2p3 : " + map.plan_p2.distance(map.plan_p3)+"   "+ map.p2.distance(map.p3.getX(),map.p3.getY(),map.p3.getZ()));
	
	System.out.println("fl :");
	System.out.println(f1_x + ","+ f1_y+","+f1_z);
	System.out.println("f2 :");
	System.out.println(f2_x + ","+ f2_y+","+f2_z);
	*/
	Point3D p = new Point3D(  a*f1_x + b*f2_x+map.p1.getX()
							, a*f1_y + b*f2_y+map.p1.getY()
							, a*f1_z + b*f2_z+map.p1.getZ());
	
	//System.out.println("p: ");
	//System.out.println(p.getX()+","+p.getY()+","+p.getZ());
	
	return p;
}

public static double det(double f1x, double f2x, double f1y, double f2y){
	
	return f1x*f2y-f1y*f2x;
	
}


}
