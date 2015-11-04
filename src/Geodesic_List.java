import java.util.ArrayList;

import org.jlab.geom.prim.Line3D;
import org.jlab.geom.prim.Point3D;

public class Geodesic_List {
	ArrayList<Line3D> GL = new  ArrayList();	


public void Generate(ArrayList<Point3D> Point_List){
	
	for(int i=0;i<Point_List.size()-1;i++){
		Line3D l = new Line3D();
		l.setOrigin(Point_List.get(i));
		l.setEnd(Point_List.get(i+1));
		GL.add(l);
	}

}


}