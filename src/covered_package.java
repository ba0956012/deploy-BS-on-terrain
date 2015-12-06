import java.util.ArrayList;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Point2D;
import org.jlab.geom.prim.Line3D;



public class covered_package {

	

		ArrayList<Line3D> Line = new ArrayList<Line3D>(); 
		Triangel t  = new Triangel();

	
	public ArrayList<covered_package> into(Read_TIN.TIN_List TL,Geodesic_List l){
		
		ArrayList<covered_package> packegts_List = new ArrayList<covered_package>();  
		int debug = 0;
		int j=0;
		
		for(j=0;j<TL.t_List.size();j++){
			covered_package p = new covered_package();
			Polygon2D Triangel2D = new  Polygon2D();
			Triangel2D = Triangle_to_Polygon2D.single_Triangle_to_Polygon2D(TL.t_List.get(j), TL.p_List);
			p.t = TL.t_List.get(j);	
			int tt[];
			tt= p.t.p_id;
			/*
			if(Triangel2D.contains(0.53185,0.41698)){ //  0.58,0.39 0.53185,0.41698
				System.out.println("0.53185 0.41698:"+tt[0]);
				System.out.println("0.53185 0.41698:"+tt[1]);
				System.out.println("0.53185 0.41698:"+tt[2]);
				System.out.println();
				debug++;	
					}
			*/
			
			for(int i=0;i<l.GL.size();i++){	
				
				if(Triangel2D.contains(l.GL.get(i).end().x(),l.GL.get(i).end().y())
				&&Triangel2D.contains(l.GL.get(i).origin().x(),l.GL.get(i).origin().y())){
					p.Line.add(l.GL.get(i));
					//l.GL.get(i).show();
				}
				//					
				//System.out.println();	
			}
			packegts_List.add(p);
			
		}
		//System.out.println("debug:"+debug);
		//System.out.println("j:"+j);
		return packegts_List;
	}
	

	public void create_package(Read_TIN.TIN_List TL ,Geodesic_List l){

		
		
		for(int j=0; j<TL.t_List.size();j++){
			covered_package p = new covered_package();
			Polygon2D Triangel2D = new  Polygon2D();
			Triangel2D = Triangle_to_Polygon2D.single_Triangle_to_Polygon2D(TL.t_List.get(j) , TL.p_List);
			//System.out.println(Triangel2D.get_xpoint(0)+" "+Triangel2D.get_ypoint(0));
			//System.out.println(Triangel2D.get_xpoint(1)+" "+Triangel2D.get_ypoint(1));
			//System.out.println(Triangel2D.get_xpoint(2)+" "+Triangel2D.get_ypoint(2));
			
		}
		
	
	
	}
	

}
