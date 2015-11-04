import java.util.ArrayList;

public class Triangle_to_Polygon2D {


	public ArrayList<Polygon2D> Triangle_to_Polygon2D(ArrayList<Triangel> t_List , ArrayList<TIN_Point> p_List){
		
		ArrayList<Polygon2D> Polygon2D_List = new ArrayList<Polygon2D>();
		for(int i=0;i<t_List.size();i++){
			//Polygon2D triangle2D = new Polygon2D();
			Triangel triangle;
			triangle = t_List.get(i);
			int[] pid = triangle.get_pid();
			TIN_Point p0 = p_List.get(pid[0]);
			TIN_Point p1 = p_List.get(pid[1]);
			TIN_Point p2 = p_List.get(pid[2]);
			Polygon2D Polygon = new Polygon2D();
			Polygon.addPoint(p0.getX(), p0.getY());
			Polygon.addPoint(p1.getX(), p1.getY());
			Polygon.addPoint(p2.getX(), p2.getY());
			Polygon2D_List.add(Polygon);
     }
		return Polygon2D_List;

	}
	
	
	public static Polygon2D single_Triangle_to_Polygon2D(Triangel t,  ArrayList<TIN_Point> p_List){
		
		Polygon2D t2D = new Polygon2D();	
		int[] pid = t.get_pid();
		
		TIN_Point p0 = p_List.get(pid[0]);
		TIN_Point p1 = p_List.get(pid[1]);
		TIN_Point p2 = p_List.get(pid[2]);
		
		t2D.addPoint(p0.getX(), p0.getY());
		t2D.addPoint(p1.getX(), p1.getY());
		t2D.addPoint(p2.getX(), p2.getY());
						
		return t2D;
	}
}