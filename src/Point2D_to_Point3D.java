

import javafx.geometry.Point3D;

public class Point2D_to_Point3D {

public static Point3D Calcute(Point3D p1, Point3D p2, Point3D p3, double d1, double d2, double d3){
	
	//System.out.println("p1:"+p1.getX()+","+p1.getY()+","+p1.getZ()+","+p1.distance(p2));
	//System.out.println("p2:"+p2.getX()+","+p2.getY()+","+p2.getZ()+","+ p2.distance(p3));
	//System.out.println("p3:"+p3.getX()+","+p3.getY()+","+p3.getZ()+","+ p3.distance(p1));
	//System.out.println("d:"+d1+" "+d2+" "+d3);

	
	boolean flag1 = p1.distance(p2) > d1 + d2;
	boolean flag2 = p2.distance(p3) > d2 + d3;
	boolean flag3 = p3.distance(p1) > d1 + d3;
	
	//System.out.println("flag:" + flag1+" "+flag2+" "+flag3);
	
	if (flag1 || flag2 || flag3) {
	RuntimeException e = new RuntimeException("参数异常无法求解");
	throw e;
	}
	
	double k1 = p2.getX() * p2.getX() - p1.getX() * p1.getX() + p2.getY() * p2.getY() - p1.getY() * p1.getY() + p2.getZ() * p2.getZ() - p1.getZ() * p1.getZ() + d1 * d1 - d2 * d2;
	//System.out.println("k1:"+k1);
	double k2 = p3.getX() * p3.getX() - p1.getX() * p1.getX() + p3.getY() * p3.getY() - p1.getY() * p1.getY() + p3.getZ() * p3.getZ() - p1.getZ() * p1.getZ() + d1 * d1 - d3 * d3;
	double k3 = p3.getX() * p3.getX() - p2.getX() * p2.getX() + p3.getY() * p3.getY() - p2.getY() * p2.getY() + p3.getZ() * p3.getZ() - p2.getZ() * p2.getZ() + d2 * d2 - d3 * d3;
	double m1 = 2 * ((p2.getY() - p1.getY()) * (p3.getX() - p1.getX()) - (p3.getY() - p1.getY()) * (p2.getX() - p1.getX()));
	double m2 = 2 * ((p2.getY() - p1.getY()) * (p3.getX() - p2.getX()) - (p3.getY() - p2.getY()) * (p2.getX() - p1.getX()));
	double n1 = 2 * ((p2.getZ() - p1.getZ()) * (p3.getX() - p1.getX()) - (p3.getZ() - p1.getZ()) * (p2.getX() - p1.getX()));
	double n2 = 2 * ((p2.getZ() - p1.getZ()) * (p3.getX() - p2.getX()) - (p3.getZ() - p2.getZ()) * (p2.getX() - p1.getX()));
	double v1 = k1 * (p3.getX() - p1.getX()) - k2 * (p2.getX() - p1.getX());
	double v2 = k1 * (p3.getX() - p2.getX()) - k3 * (p2.getX() - p1.getX());
	double z = (v1 * m2 - v2 * m1) / (n1 * m2 - n2 * m1);
	double u1 = k1 * (p3.getY() - p1.getY()) - k2 * (p2.getY() - p1.getY());
	double s1 = 2 * ((p2.getZ() - p1.getZ()) * (p3.getY() - p1.getY()) - (p3.getZ() - p1.getZ()) * (p2.getY() - p1.getY()));
	double t1 = 2 * ((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()));
	double x = (u1 - s1 * z) / t1;
	double e1 = k1 * (p3.getZ() - p1.getZ()) - k2 * (p2.getZ() - p1.getZ());
	double q1 = 2 * ((p2.getX() - p1.getX()) * (p3.getZ() - p1.getZ()) - (p3.getX() - p1.getX()) * (p2.getZ() - p1.getZ()));
	double w1 = 2 * ((p2.getY() - p1.getY()) * (p3.getZ() - p1.getZ()) - (p3.getY() - p1.getY()) * (p2.getZ() - p1.getZ()));
	double y = (e1 - q1 * x) / w1;
	Point3D p = new Point3D(x, y, z);
	return p;
}

}
