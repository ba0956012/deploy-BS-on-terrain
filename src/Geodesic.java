import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Geodesic {

public void cal_Geodesic(ArrayList<Triangel> Input_Triangel_List, ArrayList<TIN_Point> Input_p_List ,ArrayList<Line2D.Double> l) throws IOException, InterruptedException{
	
	Polygon2D P = new Polygon2D();
		
	ArrayList<Polygon2D> Input_Polygon_List = new ArrayList<Polygon2D>();
	
	Triangle_to_Polygon2D T_to_P = new Triangle_to_Polygon2D();
	Input_Polygon_List  = T_to_P.Triangle_to_Polygon2D(Input_Triangel_List, Input_p_List);
	
	ArrayList<TIN_Point> p_List = new ArrayList<TIN_Point>();
	ArrayList<Polygon2D> Polygon_List = new ArrayList<Polygon2D>();
	ArrayList<Triangel> Triangel_List = new ArrayList<Triangel>();
	for(int i=0; i<l.size();i++){ ///////////////////////////////////////////////////////calculate all geodesic
		Polygon_List.removeAll(Polygon_List);
		Polygon_List = (ArrayList<Polygon2D>) Input_Polygon_List.clone();
		p_List.removeAll(p_List);
		p_List = (ArrayList<TIN_Point>) Input_p_List.clone();
		Triangel_List.removeAll(Triangel_List);
		Triangel_List = (ArrayList<Triangel>) Input_Triangel_List.clone();
		
		Point2D.Double Str = new Point2D.Double();
		Point2D.Double End = new Point2D.Double();
		Str.x = l.get(i).getX1();
		Str.y = l.get(i).getY1();
		End.x = l.get(i).getX2();
		End.y = l.get(i).getY2();
		Polygon2D[] new_triangle1 = new Polygon2D[3];
		Polygon2D[] new_triangle2 = new Polygon2D[3];
	///////////////////////////////////////////////////////////get points of line
		int b = 0;
		int bb = 0;
		int del_t_id1= 0;
		int del_t_id2= 0;
		int j = 0;
		for(j=0;( b==0 || bb==0 )&& j<Polygon_List.size() ;j++){ ///////////////////////calculate point in polygon
			
			P = Polygon_List.get(j);
						
			if(P.contains(Str) == true){	
				
				new_triangle1 = update_triangle.new_triangle_coordinates(P,Str.getX(),Str.getY());
				//new_triangle1 = new_triangle_coordinates(P,Str.getX(),Str.getY());
				del_t_id1= j;
				b = 1;
				
			}
			
				
			
			else if(P.contains(End) == true){
				//System.out.println(j);
				//System.out.println("del_t_id1 :"+ del_t_id1);
				//System.out.println("End.getX:"+End.getX());
				//System.out.println("End.getY:"+End.getY());
				//System.out.println(P.get_xpoint(0)+","+P.get_ypoint(0));
				//System.out.println(P.get_xpoint(1)+","+P.get_ypoint(1));
				//System.out.println(P.get_xpoint(2)+","+P.get_ypoint(2));
				new_triangle2 = update_triangle.new_triangle_coordinates(P,End.getX(),End.getY());
				//new_triangle2 = new_triangle_coordinates(P,End.getX(),End.getY());
				del_t_id2= j;
				bb=1;
			}
			
			
			
			//System.out.println(b +" "+bb+" "+j);
		}
		
		if((b==0||bb==0)&&j==Polygon_List.size()){
			System.out.println("point not in TIN");
			System.out.println("Str :"+Str+" "+b);
			System.out.println("End :"+End+" "+bb);
			System.exit(0);;
		}
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		double z = Find_Zcoordinate.getZ(Polygon_List.get(del_t_id1).get_xpoint(0), Polygon_List.get(del_t_id1).get_ypoint(0), p_List);
		TIN_Point p1 = new TIN_Point(Polygon_List.get(del_t_id1).get_xpoint(0), Polygon_List.get(del_t_id1).get_ypoint(0),z);
		
		z = Find_Zcoordinate.getZ(Polygon_List.get(del_t_id1).get_xpoint(1), Polygon_List.get(del_t_id1).get_ypoint(1), p_List);
		TIN_Point p2 = new TIN_Point(Polygon_List.get(del_t_id1).get_xpoint(1), Polygon_List.get(del_t_id1).get_ypoint(1),z);
		
		z = Find_Zcoordinate.getZ(Polygon_List.get(del_t_id1).get_xpoint(2), Polygon_List.get(del_t_id1).get_ypoint(2), p_List);
		TIN_Point p3 = new TIN_Point(Polygon_List.get(del_t_id1).get_xpoint(2), Polygon_List.get(del_t_id1).get_ypoint(2),z);
			
		TIN_Point S = new TIN_Point(Str.getX(),Str.getY(), Find_Zcoordinate.calcY(p1, p2, p3, Str.getX(), Str.getY()));
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		z = Find_Zcoordinate.getZ(Polygon_List.get(del_t_id2).get_xpoint(0), Polygon_List.get(del_t_id2).get_ypoint(0), p_List);
		TIN_Point pp1 = new TIN_Point(Polygon_List.get(del_t_id2).get_xpoint(0), Polygon_List.get(del_t_id2).get_ypoint(0),z);
		z = Find_Zcoordinate.getZ(Polygon_List.get(del_t_id2).get_xpoint(1), Polygon_List.get(del_t_id2).get_ypoint(1), p_List);
		TIN_Point pp2 = new TIN_Point(Polygon_List.get(del_t_id2).get_xpoint(1), Polygon_List.get(del_t_id2).get_ypoint(1),z);
		z = Find_Zcoordinate.getZ(Polygon_List.get(del_t_id2).get_xpoint(2), Polygon_List.get(del_t_id2).get_ypoint(2), p_List);
		TIN_Point pp3 = new TIN_Point(Polygon_List.get(del_t_id2).get_xpoint(2), Polygon_List.get(del_t_id2).get_ypoint(2),z);
			
		TIN_Point E = new TIN_Point(End.getX(),End.getY(), Find_Zcoordinate.calcY(pp1, pp2, pp3, End.getX(), End.getY()));
		
		p_List.add(S);
		p_List.add(E);
		
	/////////////////////////////////////////////////////////////////////////// update triangle list
	/*	
		Polygon_List.set(del_t_id1, new_triangle1[0]);
		Polygon_List.add(new_triangle1[1]);
		Polygon_List.add(new_triangle1[2]);
		Polygon_List.set(del_t_id2, new_triangle2[0]);
		Polygon_List.add(new_triangle2[1]);
		Polygon_List.add(new_triangle2[2]);	
	*/
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Polygon2D_to_Triangle.Polygon2D_to_Triangle(new_triangle1[0], p_List);
		Triangel_List.set(del_t_id1, Polygon2D_to_Triangle.Polygon2D_to_Triangle(new_triangle1[0], p_List));
		Triangel_List.add( Polygon2D_to_Triangle.Polygon2D_to_Triangle(new_triangle1[1], p_List));
		Triangel_List.add( Polygon2D_to_Triangle.Polygon2D_to_Triangle(new_triangle1[2], p_List));
		
		Triangel_List.set(del_t_id2, Polygon2D_to_Triangle.Polygon2D_to_Triangle(new_triangle2[0], p_List));
		Triangel_List.add( Polygon2D_to_Triangle.Polygon2D_to_Triangle(new_triangle2[1], p_List));
		Triangel_List.add( Polygon2D_to_Triangle.Polygon2D_to_Triangle(new_triangle2[2], p_List));
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		call_geotest.geotest(p_List.size(), Triangel_List.size(), p_List, Triangel_List);
		
	}///////////////////////////////////////////////////////calculate all geodesic
	
	
}

}
