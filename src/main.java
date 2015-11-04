import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;


import javafx.geometry.Point3D;



public class main {

    public static void main(String[] args) throws IOException, InterruptedException{
    	//SubTask.geodesc = new ArrayList<Point3D>();
			//Read_TIN tr = new Read_TIN();
			//tr.read("C:/Users/sclab/Desktop/terrain/geotest/flat_triangular_mesh.txt");
			
		    //TIN_Point p1 = new TIN_Point(42.000000,3.2046642,29.000000);
		    //TIN_Point p2 = new TIN_Point(42.000000,3.9619608,28.000000);
		    //TIN_Point p3 = new TIN_Point(42.000000,3.5283644,29.000000);
		    //double i = Find_Zcoordinate.calcY(p1,p2,p3, 42.779999,3.695628);
    	///////////////////////////////////////////////////////////////////////////////////
    	Coordinae_Transform CT = new Coordinae_Transform();
	    CT.cellsize = 0.000833333333;
	    CT.xllcorner = 119.999583333333;
	    CT.yllcorner = 22.999583333333;    
    	
    	
		    //CT.GeneratePath(2, 1200, 1200);
		///////////////////////////////////////////////////////////////////////////////////////////////    
		    //Point2D.Double p = new Point2D.Double();
		    //p = Find_Zcoordinate.intersection(42.0, 3.2046642, 42.000000, 3.9619608, 42.0, 3.5283644,  42.779999, 4.1337585);
		    //System.out.print(i);
		    Read_TIN.TIN_List TL;
		    
		    	    
		    Read_TIN RT = new Read_TIN();
		    //TL = RT.read("C:/Users/sclab/Desktop/terrain/geotest/hedgehog_mesh.txt");
		    TL = RT.read("JAVA_N23.txt");
		 /*   
		    ArrayList<TIN_Point> TLP = new ArrayList<TIN_Point>();
		    for(int i=0;i<TL.p_List.size();i++){
		    	
		    		
		    	TIN_Point TP = new TIN_Point(CT.ASCtoLatitude_and_Longitude(TL.p_List.get(i).getX(), TL.p_List.get(i).getY(), TL.p_List.get(i).getZ()).x(),
		    			CT.ASCtoLatitude_and_Longitude(TL.p_List.get(i).getX(), TL.p_List.get(i).getY(), TL.p_List.get(i).getZ()).y(),
		    			CT.ASCtoLatitude_and_Longitude(TL.p_List.get(i).getX(), TL.p_List.get(i).getY(), TL.p_List.get(i).getZ()).z());
		    	TLP.add(TP);
		    	
		    }
		   */ 
		    
		    //System.out.println("TL.t_List.size():"+TL.t_List.size());
		    ArrayList<Line2D.Double> l = new ArrayList<Line2D.Double>();
		    l = Read_line.read("path.txt");
		    //System.out.println(l.get(0).getX1());
		    Geodesic g = new Geodesic();
		    //g.cal_Geodesic(TL.t_List, TL.p_List, l);
		    
		    g.cal_Geodesic(TL.t_List, TL.p_List, l);
		    
		    //Point3D pp = new Point3D(1.0,1.0,1.0);
		    //Point3D ppp = new Point3D(2.0,2.0,2.0);
		    //double D = pp.distance(ppp);
		    
		    //Point2D.Double pp2 = new Point2D.Double(pp.getX(),pp.getY());
		    		    
		   // Point2D.Double pppp = new Point2D.Double();
		    //pppp =  mesh_to_plan.project(D, pp, ppp);
		    
		    //call_geotest.geotest(TL.p_List.size(), TL.t_List.size(), TL.p_List, TL.t_List);
		    
		    //System.out.println(D);
		    //System.out.println(pppp.distance(pp2));
		    //System.out.println(pppp.getX()+" "+pppp.getY());
		    
		    //for(int i1=0; i1<SubTask.geodesc.size();i1++)
		    //	 System.out.println(SubTask.geodesc.get(i1).x()+" "+SubTask.geodesc.get(i1).y()+" "+SubTask.geodesc.get(i1).z()+" ");
		    
		    Geodesic_List GL = new Geodesic_List();
		    GL.Generate(SubTask.geodesc);
		    for(int tt=0 ;tt<GL.GL.size();tt++)
		    {	
		   //	GL.GL.get(tt).show();
		    }
		    
		   // System.out.println(GL.GL.size());
		    
		    for(int zz=0;zz<GL.GL.size();zz++)
		    {
		   // GL.GL.get(zz).origin().show();
		   // GL.GL.get(zz).end().show();
		    }
		    
		    int de=0;
		    //System.out.println("de");
		    for(de=0; de<TL.t_List.size();de++){
		    	int dt[];
		    	dt = TL.t_List.get(de).p_id;
		   // 	System.out.println(dt[0]+","+dt[1]+","+dt[2]);
		    }
		    
		   // System.out.println("de"+de);
		    
		    covered_package CP = new covered_package();
		    ArrayList<covered_package> CP_L = new ArrayList();
		    CP_L = CP.into(TL, GL);
		    
		    
		    
		    for(int i = 0;i<CP_L.size();i++){
		    	
		    	if(CP_L.get(i).Line.size()>0){
		   		Deploy_BS DB = new  Deploy_BS();
		   		//	System.out.println("i:"+i);
		    	//	System.out.println("CP_L.get(i).Line.size()"+CP_L.get(i).Line.size());
		    		
		    		for(int k=0;k<CP_L.get(i).Line.size();k++){
		    	//		System.out.println(CP_L.get(i).Line.get(k).origin().x()+","+CP_L.get(i).Line.get(k).origin().y());	
		    	//		System.out.println(CP_L.get(i).Line.get(k).end().x()+","+CP_L.get(i).Line.get(k).end().y());
		    		}
		    		DB.Deploy(CP_L.get(i), TL, CT);
		    		
		    	}
		    	
		    }
		   
		    
		    
		    
		    
		    
		    //covered_package cp = new covered_package();
		    //cp.create_package(TL, GL);
		    
		   
		    
		    
		    
		    
			
		    
		    //for(int ii=0;ii<SubTask.geodesc.size();ii++)
		    //System.out.println(SubTask.geodesc.get(ii));
			
		}
}
