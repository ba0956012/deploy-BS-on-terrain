import java.awt.Point;
import java.util.ArrayList;

public class update_triangle { //
	
	public static Polygon2D[] new_triangle_coordinates(Polygon2D t, double x, double y) {
		Polygon2D new_triangle[] = new Polygon2D[3];
		for(int k=0;k<3;k++){
			new_triangle[k]=new Polygon2D();
			}
		
		double[] tmp_x = new double[3];
		double[] tmp_y = new double[3];
		
		
		for(int i=0;i<3;i++){           
			tmp_x[i] = t.get_xpoint(i);
			tmp_y[i] = t.get_ypoint(i);
		}
		
		//t.reset();
		
		for(int j=0; j<3; j++){   
			//System.out.println("new_triangle[j]:"+tmp_x[j%3]+" "+tmp_y[j%3]);
			//System.out.println("x,y:"+x+" "+y);
			new_triangle[j].addPoint(x, y);
			new_triangle[j].addPoint(tmp_x[j%3], tmp_y[j%3]);
			new_triangle[j].addPoint(tmp_x[(j+1)%3], tmp_y[(j+1)%3]);
			
		}
		return new_triangle;

	}
}
