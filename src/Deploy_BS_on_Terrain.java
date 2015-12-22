import java.awt.geom.Line2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jlab.geom.prim.Point3D;

public class Deploy_BS_on_Terrain {

	public  void Deploy(String TIN_File, String Path_File, 
			int ncols,
			int ycols,
			double xllcorner,//左下角x經度
			double yllcorner,//左下角x緯度
			double cellsize, 
			double r) throws IOException, InterruptedException{
    	//SubTask.geodesc = new ArrayList<Point3D>();
			//Read_TIN tr = new Read_TIN();
			//tr.read("C:/Users/sclab/Desktop/terrain/geotest/flat_triangular_mesh.txt");
			
		    //TIN_Point p1 = new TIN_Point(42.000000,3.2046642,29.000000);
		    //TIN_Point p2 = new TIN_Point(42.000000,3.9619608,28.000000);
		    //TIN_Point p3 = new TIN_Point(42.000000,3.5283644,29.000000);
		    //double i = Find_Zcoordinate.calcY(p1,p2,p3, 42.779999,3.695628);
    	///////////////////////////////////////////////////////////////////////////////////
    	Coordinate_Transform CT = new Coordinate_Transform(); //.asc之參數
	    CT.cellsize = cellsize;
	    CT.xllcorner = xllcorner;
	    CT.yllcorner = yllcorner;    
    	CT.ycols = ycols;
    	CT.ncols = ncols;
    	
		    //CT.GeneratePath(2, 1200, 1200);
		///////////////////////////////////////////////////////////////////////////////////////////////    
		    //Point2D.Double p = new Point2D.Double();
		    //p = Find_Zcoordinate.intersection(42.0, 3.2046642, 42.000000, 3.9619608, 42.0, 3.5283644,  42.779999, 4.1337585);
		    //System.out.print(i);
		    Read_TIN.TIN_List TL;
		    
		    ArrayList<TIN_Point> TL1 = new ArrayList<TIN_Point>();
		    	    
		    Read_TIN RT = new Read_TIN();
		    //TL = RT.read("C:/Users/sclab/Desktop/terrain/geotest/hedgehog_mesh.txt");
		    TL = RT.read(TIN_File);
		    
		    for(int i=0; i<TL.p_List.size();i++) //將TIN轉為公尺完單位之座標
		    {
		  //   System.out.println("0TP "+i+" : "+TL.p_List.get(i).getX()+","+TL.p_List.get(i).getY()+","+TL.p_List.get(i).getZ());
		  //  System.out.println("1TP "+i+" : "+(xllcorner +TL.p_List.get(i).getX()*cellsize)+","+(yllcorner +TL.p_List.get(i).getY()*cellsize)+","+TL.p_List.get(i).getZ());
		    	
		    Point3D p = new Point3D(TL.p_List.get(i).getX(),TL.p_List.get(i).getY(),TL.p_List.get(i).getZ());
		    p = CT.ascGrid_to_meter_coor(yllcorner, xllcorner, p);
		    /*
		    p = 
		     TIN_Point TP = new TIN_Point(CT.to_meter_coor(
		    			  					yllcorner, xllcorner, yllcorner +(TL.p_List.get(i).getY()*cellsize)
		    			  					, xllcorner+(TL.p_List.get(i).getX()*cellsize)).getY(),
		    			  				  CT.to_meter_coor(
		    			  				  	yllcorner, xllcorner, yllcorner +(TL.p_List.get(i).getY()*cellsize)
			  								, xllcorner+(TL.p_List.get(i).getX()*cellsize)).getX(),
		    							  TL.p_List.get(i).getZ()); 
		    	
		    	TP.id = i;
		    */
		    TIN_Point TP = new TIN_Point(p.x(),p.y(),p.z());
		    TP.id = i;
		    // System.out.println("TP "+i+" : "+TP.getX()+","+TP.getY()+","+TP.getZ());	
		    TL1.add(TP);
		    }
		    //System.out.println("TL.p_List.size"+TL.p_List.size());
		    TL.p_List = TL1; 
		    //System.out.println("TL1.size"+TL1.size());
		    //System.out.println("TL1.p_List.size"+TL.p_List.size());
		    
		    
		    FileWriter FW = new FileWriter("DtestTIN.tin");
		    FW.write(TL.p_List.size()+"	"+TL.t_List.size()+"\r\n");
		    for(int i=0; i<TL.p_List.size();i++)
		    {
		    	FW.write(TL.p_List.get(i).getX()+"	"+TL.p_List.get(i).getY()+"	"+TL.p_List.get(i).getZ()+"\r\n");
		    }
		    
		    for(int i=0; i<TL.t_List.size();i++)
		    {
		  //  	if(i == 0)
		  // 	System.out.println(TL.t_List.get(i).p_id[0]+"	"+TL.t_List.get(i).p_id[1]+"	"+TL.t_List.get(i).p_id[2]+"\r\n");
		    	FW.write(TL.t_List.get(i).p_id[0]+"	"+TL.t_List.get(i).p_id[1]+"	"+TL.t_List.get(i).p_id[2]+"\r\n");
		    }
		    FW.close();
		    
		    //TL = RT.read("JAVA_N23.txt");"path.txt"
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
		    System.out.println("Read Path");
		    ArrayList<Line2D.Double> l = new ArrayList<Line2D.Double>();
		    l = Read_line.read(Path_File, CT);
		    
		  //  System.out.println("l.size(): "+l.size());
		    for(int i=0; i<l.size();i++){
		    	
		    	Line2D.Double ll= new Line2D.Double();
		    	ll.setLine(CT.ascGrid_to_meter_coor(yllcorner, xllcorner, l.get(i).getP1()), 
		    				CT.ascGrid_to_meter_coor(yllcorner, xllcorner, l.get(i).getP2())
		    						);
		    	
		    	l.set(i, ll);
		    	
		    	/*
		    	System.out.println("path :");
		    	System.out.println( l.get(i).x1+","+ l.get(i).y1);
		    	
		    	System.out.println(
		    	CT.toLatitude_and_Longitude( l.get(i).x1,l.get(i).y1).getX()+","+CT.toLatitude_and_Longitude( l.get(i).x1,  l.get(i).y1).getY());
		    	
		    	System.out.println(CT.to_grid(
		    	CT.toLatitude_and_Longitude( l.get(i).x1,l.get(i).y1).getX(),CT.toLatitude_and_Longitude( l.get(i).x1,  l.get(i).y1).getY()
		    	));
		    	*/
		    	//System.out.println( l.get(i).x2+","+ l.get(i).y2);
		    	//System.out.println(" ");
		    }
		 //   System.out.println("ll.size(): "+l.size());
		  
		   // l = Read_line.read("path.txt");
		    //System.out.println(l.get(0).getX1());
		    Geodesic g = new Geodesic();
		    //g.cal_Geodesic(TL.t_List, TL.p_List, l);
		    
		    System.out.println("Geodesic");
		    
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
		    FileWriter fw = new FileWriter("GeoPath.txt", true);
		    
		   
		    for(int GeoPath=0; GeoPath<GL.GL.size();GeoPath++)
    			fw.write(	GL.GL.get(GeoPath).origin().x()+" "+
    						GL.GL.get(GeoPath).origin().y()+" "+
    						(GL.GL.get(GeoPath).origin().z())+" "+
    						GL.GL.get(GeoPath).end().x()+" "+
    						GL.GL.get(GeoPath).end().y()+" "+
    						(GL.GL.get(GeoPath).end().z())+" "+"\r\n"
    						
    					);	
		    
		 //    System.out.println("GL :"+ GL.GL.size());
		    
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
		    
		    System.out.println("covered_package");
		    covered_package CP = new covered_package();
		    ArrayList<covered_package> CP_L = new ArrayList();
		    
		    CP_L = CP.into(TL, GL);
		    
		   // System.out.println("CP_L : " + CP_L .size());
		    
		    FileWriter fw1 = new FileWriter("GeoPath1.txt", true);
		    
			   
		    
    						
		    System.out.println("Deploy_BS");
		    for(int i = 0;i<CP_L.size();i++){
		    	
		    	if(CP_L.get(i).Line.size()>0){
		    		
		    		for(int GeoPath=0; GeoPath<CP_L.get(i).Line.size();GeoPath++)
		    			fw1.write(	CP_L.get(i).Line.get(GeoPath).origin().x()+" "+
		    					CP_L.get(i).Line.get(GeoPath).origin().y()+" "+
		    					CP_L.get(i).Line.get(GeoPath).origin().z()+" "+
		    					CP_L.get(i).Line.get(GeoPath).end().x()+" "+
		    					CP_L.get(i).Line.get(GeoPath).end().y()+" "+
		    					CP_L.get(i).Line.get(GeoPath).end().z()+" "+"\r\n");
		   		
		    		Deploy_BS DB = new  Deploy_BS();
		   		//	System.out.println("i:"+i);
		    	//	System.out.println("CP_L.get(i).Line.size()"+CP_L.get(i).Line.size());
		    		
		    	//	for(int k=0;k<CP_L.get(i).Line.size();k++){
		    	//		System.out.println(CP_L.get(i).Line.get(k).origin().x()+","+CP_L.get(i).Line.get(k).origin().y());	
		    	//		System.out.println(CP_L.get(i).Line.get(k).end().x()+","+CP_L.get(i).Line.get(k).end().y());
		    	//	}
		    		DB.Deploy(CP_L.get(i), TL, r, CT);
		    		
		    	}
		    	
		    }
		    fw1.close();
		    fw.close();
		    
		    
		    System.out.println("Finish");
		    
		    //covered_package cp = new covered_package();
		    //cp.create_package(TL, GL);
		    
		   
		    
		    
		    
		    
			
		    
		    //for(int ii=0;ii<SubTask.geodesc.size();ii++)
		    //System.out.println(SubTask.geodesc.get(ii));
			
		}

}
