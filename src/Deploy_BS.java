import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jlab.geom.prim.Line3D;

import javafx.geometry.Point3D;

public class Deploy_BS {
	
	static ArrayList<Double> ls = new ArrayList<Double>();
	static ArrayList<Double> coverls = new ArrayList<Double>();
	static int point = 10000;// 控制小數點
	
	public void Deploy(covered_package PK, Read_TIN.TIN_List TL,  double rr, Coordinate_Transform CT ) throws IOException{
	
		mesh_and_plan Map = new mesh_and_plan();
		
		
		
		
		int jj[] = PK.t.p_id;
		/*
		System.out.println(TL.t_List.get(0).p_id[0]+" , "+TL.t_List.get(0).p_id[1]+" , "+TL.t_List.get(0).p_id[2]);
		
		System.out.println("PK.t.p_id - "+PK.t.id+ " : " + PK.t.p_id[0]+" , " +PK.t.p_id[1]+" , "+ PK.t.p_id[2]);

		System.out.println("TL.p_List+  : " + TL.p_List.get(0)+" , " +TL.p_List.get(1)+" , "+ TL.p_List.get(2));
		
		System.out.println("TL.p_List.get - "+PK.t.id+ " : " + TL.p_List.get(jj[0])+" , " +TL.p_List.get(jj[1])+" , "+ TL.p_List.get(jj[2]));
		*/
		Map = Map.mesh_to_plan(TL.p_List.get(jj[0]), TL.p_List.get(jj[1]), TL.p_List.get(jj[2]));
		
		Polygon2D pol = new Polygon2D();
		pol.addPoint(Map.plan_p1.x, Map.plan_p1.y);
		pol.addPoint(Map.plan_p2.x, Map.plan_p2.y);
		pol.addPoint(Map.plan_p3.x, Map.plan_p3.y);
		
		
		System.out.println("Map.plan : ");
		System.out.println(Map.plan_p1.x+","+Map.plan_p1.y);
		System.out.println(Map.plan_p2.x+","+Map.plan_p2.y);
		System.out.println(Map.plan_p3.x+","+Map.plan_p3.y);
		
		
		Line2D.Double l12 = new Line2D.Double();
		l12.setLine(Map.plan_p1, Map.plan_p2);
		Line2D.Double l13 = new Line2D.Double();
		l13.setLine(Map.plan_p1, Map.plan_p3);
		Line2D.Double l23 = new Line2D.Double();
		l23.setLine(Map.plan_p2, Map.plan_p3);
		
		/*
		System.out.println("map plan point : ");
		System.out.println(Map.plan_p1.x+" , " + Map.plan_p1.y);
		System.out.println(Map.plan_p2.x+" , " + Map.plan_p2.y);
		System.out.println(Map.plan_p3.x+" , " + Map.plan_p3.y);
		
		System.out.println("map TIN point : ");
		System.out.println(Map.p1.getX()+" , " + Map.p1.getY()+" , "+ Map.p1.getZ());
		System.out.println(Map.p2.getX()+" , " + Map.p2.getY()+" , "+ Map.p2.getZ());
		System.out.println(Map.p3.getX()+" , " + Map.p3.getY()+" , "+ Map.p3.getZ());
		*/
		
		//System.out.println("DBS26: "+Map.plan_p3);
		//System.out.println("DBS27: "+Map.project(Map.p3));
		
		
		ArrayList<Line2D.Double> L_list =new ArrayList<Line2D.Double>();
		//System.out.println("PK.Line.size():"+PK.Line.size());
		for(int ii=0; ii<PK.Line.size();ii++){
			/*
			System.out.println("DS30:"+ TL.p_List.get(jj[0]).getX()+","+TL.p_List.get(jj[0]).getY());
			System.out.println("DS31:"+ PK.Line.get(ii).origin().x()+","+PK.Line.get(ii).origin().y());
			System.out.println("DS32:"+ PK.Line.get(ii).end().x()+","+PK.Line.get(ii).end().y());
			*/
			//System.out.println("DS32:"+line3D_to_2D(PK.Line.get(ii), TL.p_List.get(jj[0])).getX1());
			//L_list .add(line3D_to_2D(PK.Line.get(ii), TL.p_List.get(jj[0])));
			Line2D.Double l = new Line2D.Double();
			System.out.println("Line :" + ii);
			
			//PK.Line.get(ii).show();
			
			l = Map.project(PK.Line.get(ii));
			/*			
			System.out.println(l.getX1()+","+l.getY1());
			System.out.println(l.getX2()+","+l.getY2());
			System.out.println("dist : ");
			System.out.println( TL.p_List.get(jj[0]).distance(PK.Line.get(ii).origin()) );
			System.out.println( TL.p_List.get(jj[1]).distance(PK.Line.get(ii).origin()) );
			System.out.println( TL.p_List.get(jj[2]).distance(PK.Line.get(ii).origin()) );
			System.out.println( l.getP1().distance(Map.plan_p1) );
			System.out.println( l.getP1().distance(Map.plan_p2) );
			System.out.println( l.getP1().distance(Map.plan_p3) ) ;
			*/
			
			/*
			System.out.println("DS33:"+ TL.p_List.get(jj[0]).getX()+","+TL.p_List.get(jj[0]).getY());
			System.out.println("DS34:"+ l.getX1()+","+l.getY1());
			System.out.println("DS35:"+ l.getX1()+","+l.getY2());
			*/
			
			L_list .add(l);
		}

	///////////////////////////////////////////////////////////
		int tmp, stop = 0;
		double h, k, r = CT.range(rr); // 第一個圓心 //r 半徑
		// double x1=4.02, y1=7.2, x2=h, y2=k, x3=6, y3=4; //三點座標
		double x1, y1, x2, y2, x3, y3; // 三點座標
		double a1, b1, c1; // 線(x1, y1, x2, y2)
		double a2, b2, c2; // 線(x2, y2, x3, y3)
		double a3, b3, c3; // 分角線

		ArrayList<Double> linels = new ArrayList<Double>();
		ArrayList<Double> newlinels = new ArrayList<Double>();
		ArrayList<Double> centerls = new ArrayList<Double>();
		double  min_line;
		int  i, j = 0;
		int z = 0;
		// Construct BufferedReader from InputStreamReader

		

			//String line = null;
			/*
			 * while ((line = br.readLine()) != null) { t =
			 * Double.parseDouble(line); linels.add(t);
			 */
			//System.out.println("L_list.size():"+L_list.size());

			for(int iii=0;iii<L_list.size();iii++) {
				//String tempArray[] = line.split("\\,");// 分割逗號

				
				linels.add(L_list.get(iii).x1);
				linels.add(L_list.get(iii).y1);
				linels.add(L_list.get(iii).x2);
				linels.add(L_list.get(iii).y2);
				
			}
			
			//for(int ii=0;ii<linels.size();ii++)
			//System.out.println("linels :"+linels.get(ii));

			/////////////////////////////////////////////////////////////
			do {
				//lssize = linels.size();
				min_line = 1.0 / 0.0;

				for (i = 0; i < linels.size(); i = i + 2) {
					if (linels.get(i) < min_line) {
						j = i; // 最左的x座標
						min_line = linels.get(i);
					}
				}

				h = linels.get(j);
				k = linels.get(j + 1);
				
				x1 = h + 0.1;
				y1 = k + 2;
				
				x2 = h;
				y2 = k;
				
				x3 = h + 0.1;
				y3 = k - 1;
				
				line(h, k, x1, y1);
				a1 = ls.get(0);
				b1 = ls.get(1);
				c1 = ls.get(2);
				line(h, k, x3, y3);
				a2 = ls.get(0);
				b2 = ls.get(1);
				c2 = ls.get(2);
				angular(x1, y1, x2, y2, x3, y3, a1, b1, c1, a2, b2, c2);
				a3 = ls.get(0);
				b3 = ls.get(1);
				c3 = ls.get(2);
				
				
				//System.out.println("angular: ");
				//System.out.println(ls.get(0));
				//System.out.println(ls.get(1));
				/*
				if(pol.contains(h,k))
				{
				centerls.add(h);
				centerls.add(k);
				}
				*/
				
				
				centerls.add(h);
				centerls.add(k);
				
				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				Point2D.Double fist_center= new Point2D.Double();
				fist_center.x = h;
				fist_center.y = k;
			
			center_on_angular(a3, b3, c3, h, k, r, x1, y1, x2, y2, x3, y3);
				
				
				double h1, k1;
				
				h1 = ls.get(0);
				k1 = ls.get(1);
				
				
				rotation_0(h1, k1, h, k);
				h1 = ls.get(0);
				k1 = ls.get(1);
				
				
				
				if(!pol.contains(h1,k1))
				{
					/*
					Line2D.Double l12 = new Line2D.Double();
					l12.setLine(Map.plan_p1, Map.plan_p2);
					Line2D.Double l13 = new Line2D.Double();
					l13.setLine(Map.plan_p1, Map.plan_p3);
					Line2D.Double l23 = new Line2D.Double();
					l23.setLine(Map.plan_p2, Map.plan_p3);
					*/
				Line2D.Double hk= new Line2D.Double();
				hk.setLine(h1, k1, h, k);
				/*
				System.out.println("pol.contains(h1,k1)*************************** ");
				System.out.println(h1 +" , "+k1 );
				System.out.println(pol.contains(h1,k1) );
				
				System.out.println("l12 :");
				System.out.println(l12.x1+" , "+l12.y1);
				System.out.println(l12.x2+" , "+l12.y2);
				System.out.println("l13 :");
				System.out.println(l13.x1+" , "+l13.y1);
				System.out.println(l13.x2+" , "+l13.y2);
				System.out.println("l23 :");
				System.out.println(l23.x1+" , "+l23.y1);
				System.out.println(l23.x2+" , "+l23.y2);
				
				System.out.println("h1 k1");
				System.out.println(h1+" , "+k1);
				*/
				if(l12.intersectsLine(h1, k1, h, k)){
					Point2D.Double IntersectionPoint = new Point2D.Double();
					IntersectionPoint = getIntersectionPoint(hk,l12);	
					centerls.add(IntersectionPoint.getX());
					centerls.add(IntersectionPoint.getY());
					System.out.println("l12********************************* : ");
					System.out.println(IntersectionPoint.getX()+","+IntersectionPoint.getY());
				}
				
				else if(l13.intersectsLine(h1, k1, h, k)){
					Point2D.Double IntersectionPoint = new Point2D.Double();
					IntersectionPoint = getIntersectionPoint(hk,l13);
					
					
					System.out.println("IntersectionPoint");
					
					System.out.println(IntersectionPoint.getX()+" , "+IntersectionPoint.getY());
					
					
					centerls.add(IntersectionPoint.getX());
					centerls.add(IntersectionPoint.getY());
					System.out.println("l13********************************* : ");
					System.out.println(IntersectionPoint.getX()+","+IntersectionPoint.getY());
				}
				
				else if(l23.intersectsLine(h1, k1, h, k)){	
					Point2D.Double IntersectionPoint = new Point2D.Double();
					IntersectionPoint = getIntersectionPoint(hk,l23);	
					centerls.add(IntersectionPoint.getX());
					centerls.add(IntersectionPoint.getY());
					System.out.println("l23********************************* : ");
					System.out.println(IntersectionPoint.getX()+","+IntersectionPoint.getY());
				}
				
				else{
					System.out.println("l23121212112121212121212121 : ");
					centerls.add(h);
					centerls.add(k);
				}
				
				}
				
				
				else{
					System.out.println("pol.contains(h1,k1) is  :"+ pol.contains(h1,k1) +"********************************* ");	
					
				centerls.add(h1);
				centerls.add(k1);
				}
				
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
				double dist = Point2D.Double.distance(h1, k1, h, k);
				
				rotation_1(h1, k1, h, k); //ls.get(0)ls.get(1)
				
				if(pol.contains(ls.get(0),ls.get(1)))
				{
					System.out.println("rotation_1 contains");
					System.out.println(ls.get(0)+" , "+ls.get(1));
				centerls.add(ls.get(0));
				centerls.add(ls.get(1));
				}
				
				
				
				
				else
				{
					System.out.println("307++++++++++++++++++++++++++++++++++");
					Point2D.Double r1= new Point2D.Double(ls.get(0),ls.get(1));
				 
				 if(l12.ptLineDist(r1) <= l13.ptLineDist(r1)&&l12.ptLineDist(r1)<= l23.ptLineDist(r1)){
					 System.out.println("311++++++++++++++++++++++++++++++++++");
					 Point2D.Double l12_1= new Point2D.Double(l12.x1,l12.y1);
							 Point2D.Double l12_2= new Point2D.Double(l12.x2,l12.y2);
							 List<Point2D.Double> List = new ArrayList<Point2D.Double>();
							 List.clear();
							 List = getCircleLineIntersectionPoint(l12_1,l12_2,fist_center,dist);
					
					 
					if(List.size()==0){
						System.out.println("318++++++++++++++++++++++++++++++++++");
						if(Map.plan_p1.distance(r1) < Map.plan_p2.distance(r1)&&Map.plan_p1.x>fist_center.getX()){
							centerls.add(Map.plan_p1.x);
							centerls.add(Map.plan_p1.y);
						}
						else if(Map.plan_p1.distance(r1) > Map.plan_p2.distance(r1)&&Map.plan_p2.x>fist_center.getX()){
							System.out.println("324++++++++++++++++++++++++++++++++++");
							centerls.add(Map.plan_p2.x);
							centerls.add(Map.plan_p2.y);
						}
						else{
							System.out.println("329++++++++++++++++++++++++++++++++++");
						}
					}	
					else if( List.size()==1){
						System.out.println("333-------------------------");
						
						centerls.add(List.get(0).x);
						centerls.add(List.get(0).y);
						
					}
					
					else if( List.size()==2){
						System.out.println("340++++++++++++++++++++++++++++++++++");
						if(r1.distance(List.get(0).x,List.get(0).y)<
						r1.distance(List.get(1).x,List.get(1).y)){
							
							if(pol.contains(List.get(0).x,List.get(0).y)){
								System.out.println("360-------------------------");
								System.out.println(List.get(0).x+" , "+List.get(0).y);
								centerls.add(List.get(0).x);
								centerls.add(List.get(0).y);
							}
							
							else{
								if(l12.getP1().distance(List.get(0).x,List.get(0).y)
								   <l12.getP2().distance(List.get(0).x,List.get(0).y)){
									
									centerls.add(Map.plan_p1.x);
									centerls.add(Map.plan_p1.y);
								}
								else{
									centerls.add(Map.plan_p2.x);
									centerls.add(Map.plan_p2.y);
								}
							}
							
							
						}
						
						else{
							System.out.println("347++++++++++++++++++++++++++++++++++");
							if(pol.contains(List.get(1).x,List.get(1).y)){
								System.out.println("383-------------------------");
								System.out.println(List.get(1).x+" , "+List.get(1).y);
								centerls.add(List.get(1).x);
								centerls.add(List.get(1).y);
								}
								else{
									if(l12.getP1().distance(List.get(1).x,List.get(1).y)
									   <l12.getP2().distance(List.get(1).x,List.get(1).y)){
										centerls.add(Map.plan_p1.x);
										centerls.add(Map.plan_p1.y);
									}
									else{
										centerls.add(Map.plan_p2.x);
										centerls.add(Map.plan_p2.y);
									}
								}
						}					
					}
					
					
					 //getCircleLineIntersectionPoint(Point2D.Double pointA,
					 //	Point2D.Double pointB, Point2D.Double center, double radius)
					 
				 }
				 
				 else if(l13.ptLineDist(r1) <= l12.ptLineDist(r1)&&l13.ptLineDist(r1)<= l23.ptLineDist(r1)){
					 System.out.println("361++++++++++++++++++++++++++++++++++");
					 Point2D.Double l13_1= new Point2D.Double(l13.x1,l13.y1);
					 Point2D.Double l13_2= new Point2D.Double(l13.x2,l13.y2);
					 List<Point2D.Double> List = new ArrayList<Point2D.Double>();
					 List = getCircleLineIntersectionPoint(l13_1,l13_2,fist_center,dist);
					 
						if(List.size()==0){
							System.out.println("318++++++++++++++++++++++++++++++++++");
							if(Map.plan_p1.distance(r1) < Map.plan_p2.distance(r1)&&Map.plan_p1.x>fist_center.getX()){
								centerls.add(Map.plan_p1.x);
								centerls.add(Map.plan_p1.y);
							}
							else if(Map.plan_p1.distance(r1) > Map.plan_p2.distance(r1)&&Map.plan_p2.x>fist_center.getX()){
								System.out.println("324++++++++++++++++++++++++++++++++++");
								centerls.add(Map.plan_p2.x);
								centerls.add(Map.plan_p2.y);
							}
							else{
								System.out.println("329++++++++++++++++++++++++++++++++++");
							}
						}	
						else if( List.size()==1){
							System.out.println("333++++++++++++++++++++++++++++++++++");
							
							centerls.add(List.get(0).x);
							centerls.add(List.get(0).y);
							
						}
						
						else if( List.size()==2){
							System.out.println("340++++++++++++++++++++++++++++++++++");
							if(r1.distance(List.get(0).x,List.get(0).y)<
							r1.distance(List.get(1).x,List.get(1).y)){
								System.out.println("446++++++++++++++++++++++++++++++++++");
								System.out.println(List.get(0).x+" , "+List.get(0).y);
								
								if(pol.contains(List.get(0).x,List.get(0).y)){
									System.out.println("449++++++++++++++++++++++++++++++++++");
									
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(0));
									System.out.println(pol.get_xpoint(1)+" , "+pol.get_ypoint(1));
									System.out.println(pol.get_xpoint(2)+" , "+pol.get_ypoint(2));
									
									System.out.println("455++++++++++++++++++++++++++++++++++");
												
									System.out.println(List.get(0).x+" , "+List.get(0).y);
									
								centerls.add(List.get(0).x);
								centerls.add(List.get(0).y);
								}
								
								else{
									
									if(l13.getP1().distance(List.get(0).x,List.get(0).y)
									   <l13.getP2().distance(List.get(0).x,List.get(0).y)){
										System.out.println("455++++++++++++++++++++++++++++++++++");
										centerls.add(Map.plan_p1.x);
										centerls.add(Map.plan_p1.y);
									}
									else{
										System.out.println("460++++++++++++++++++++++++++++++++++");
										centerls.add(Map.plan_p3.x);
										centerls.add(Map.plan_p3.y);
									}
								}
								
							}
							else{
								System.out.println("347++++++++++++++++++++++++++++++++++");
								if(pol.contains(List.get(1).x,List.get(1).y)){
									System.out.println(List.get(1).x+" , "+List.get(1).y);
									centerls.add(List.get(1).x);
									centerls.add(List.get(1).y);
									}
									else{
										if(l13.getP1().distance(List.get(1).x,List.get(1).y)
										   <l13.getP2().distance(List.get(1).x,List.get(1).y)){
											centerls.add(Map.plan_p1.x);
											centerls.add(Map.plan_p1.y);
										}
										else{
											centerls.add(Map.plan_p3.x);
											centerls.add(Map.plan_p3.y);
										}
									}
							}					
						}
				 }
				 
				 
				 else if(l23.ptLineDist(r1) < l13.ptLineDist(r1)&&l23.ptLineDist(r1)< l12.ptLineDist(r1)){
					 System.out.println("403++++++++++++++++++++++++++++++++++");
					 Point2D.Double l23_1= new Point2D.Double(l23.x1,l23.y1);
					 Point2D.Double l23_2= new Point2D.Double(l23.x2,l23.y2);
					 List<Point2D.Double> List = new ArrayList<Point2D.Double>();
					 List = getCircleLineIntersectionPoint(l23_1,l23_2,fist_center,dist);
			 
						if(List.size()==0){
							System.out.println("318++++++++++++++++++++++++++++++++++");
							if(Map.plan_p1.distance(r1) < Map.plan_p2.distance(r1)&&Map.plan_p1.x>fist_center.getX()){
								centerls.add(Map.plan_p1.x);
								centerls.add(Map.plan_p1.y);
							}
							else if(Map.plan_p1.distance(r1) > Map.plan_p2.distance(r1)&&Map.plan_p2.x>fist_center.getX()){
								System.out.println("324++++++++++++++++++++++++++++++++++");
								centerls.add(Map.plan_p2.x);
								centerls.add(Map.plan_p2.y);
							}
							else{
								System.out.println("329++++++++++++++++++++++++++++++++++");
							}
						}	
						else if( List.size()==1){
							System.out.println("333++++++++++++++++++++++++++++++++++");
							
							centerls.add(List.get(0).x);
							centerls.add(List.get(0).y);
							
						}
						
						else if( List.size()==2){
							System.out.println("340++++++++++++++++++++++++++++++++++");
							if(r1.distance(List.get(0).x,List.get(0).y)<
							r1.distance(List.get(1).x,List.get(1).y)){
								
								System.out.println(List.get(0).x+" , "+List.get(0).y);
								
								if(pol.contains(List.get(0).x,List.get(0).y)){
									System.out.println("540++++++++++++++++++++++++++++++++++");
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(0));
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(1));
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(2));
									System.out.println(List.get(0).x+" , "+List.get(0).y);
								centerls.add(List.get(0).x);
								centerls.add(List.get(0).y);
								}
								else{
									if(l23.getP1().distance(List.get(0).x,List.get(0).y)
									   <l23.getP2().distance(List.get(0).x,List.get(0).y)){
										
										centerls.add(Map.plan_p2.x);
										centerls.add(Map.plan_p2.y);
									}
									else{
										centerls.add(Map.plan_p3.x);
										centerls.add(Map.plan_p3.y);
									}
								}
								
							}
							else{
								System.out.println("347++++++++++++++++++++++++++++++++++");
								if(pol.contains(List.get(1).x,List.get(1).y)){
									System.out.println(List.get(1).x+" , "+List.get(1).y);
									centerls.add(List.get(1).x);
									centerls.add(List.get(1).y);
									}
									else{
										if(l23.getP1().distance(List.get(1).x,List.get(1).y)
										   <l23.getP2().distance(List.get(1).x,List.get(1).y)){
											centerls.add(Map.plan_p2.x);
											centerls.add(Map.plan_p2.y);
										}
										else{
											centerls.add(Map.plan_p3.x);
											centerls.add(Map.plan_p3.y);
										}
									}
							}					
						}
	 
				 }
				 
				}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
//double dist = Point2D.Double.distance(h1, k1, h, k);
			
				rotation_2(h1, k1, h, k); //ls.get(0)ls.get(1)
				
				if(pol.contains(ls.get(0),ls.get(1)))
				{
					System.out.println("rotation_2 contains");
					System.out.println(ls.get(0)+" , "+ls.get(1));
				centerls.add(ls.get(0));
				centerls.add(ls.get(1));
				}
				
				
				
				
				else
				{
					System.out.println("307++++++++++++++++++++++++++++++++++");
					Point2D.Double r1= new Point2D.Double(ls.get(0),ls.get(1));
				 
				 if(l12.ptLineDist(r1) <= l13.ptLineDist(r1)&&l12.ptLineDist(r1)<= l23.ptLineDist(r1)){
					 System.out.println("311++++++++++++++++++++++++++++++++++");
					 Point2D.Double l12_1= new Point2D.Double(l12.x1,l12.y1);
							 Point2D.Double l12_2= new Point2D.Double(l12.x2,l12.y2);
							 List<Point2D.Double> List = new ArrayList<Point2D.Double>();
							 List.clear();
							 List = getCircleLineIntersectionPoint(l12_1,l12_2,fist_center,dist);
					
					 
					if(List.size()==0){
						System.out.println("318++++++++++++++++++++++++++++++++++");
						if(Map.plan_p1.distance(r1) < Map.plan_p2.distance(r1)&&Map.plan_p1.x>fist_center.getX()){
							centerls.add(Map.plan_p1.x);
							centerls.add(Map.plan_p1.y);
						}
						else if(Map.plan_p1.distance(r1) > Map.plan_p2.distance(r1)&&Map.plan_p2.x>fist_center.getX()){
							System.out.println("324++++++++++++++++++++++++++++++++++");
							centerls.add(Map.plan_p2.x);
							centerls.add(Map.plan_p2.y);
						}
						else{
							System.out.println("329++++++++++++++++++++++++++++++++++");
						}
					}	
					else if( List.size()==1){
						System.out.println("333-------------------------");
						
						centerls.add(List.get(0).x);
						centerls.add(List.get(0).y);
						
					}
					
					else if( List.size()==2){
						System.out.println("340++++++++++++++++++++++++++++++++++");
						if(r1.distance(List.get(0).x,List.get(0).y)<
						r1.distance(List.get(1).x,List.get(1).y)){
							
							if(pol.contains(List.get(0).x,List.get(0).y)){
								System.out.println("360-------------------------");
								System.out.println(List.get(0).x+" , "+List.get(0).y);
								centerls.add(List.get(0).x);
								centerls.add(List.get(0).y);
							}
							
							else{
								if(l12.getP1().distance(List.get(0).x,List.get(0).y)
								   <l12.getP2().distance(List.get(0).x,List.get(0).y)){
									
									centerls.add(Map.plan_p1.x);
									centerls.add(Map.plan_p1.y);
								}
								else{
									centerls.add(Map.plan_p2.x);
									centerls.add(Map.plan_p2.y);
								}
							}
							
							
						}
						
						else{
							System.out.println("347++++++++++++++++++++++++++++++++++");
							if(pol.contains(List.get(1).x,List.get(1).y)){
								System.out.println("383-------------------------");
								System.out.println(List.get(1).x+" , "+List.get(1).y);
								centerls.add(List.get(1).x);
								centerls.add(List.get(1).y);
								}
								else{
									if(l12.getP1().distance(List.get(1).x,List.get(1).y)
									   <l12.getP2().distance(List.get(1).x,List.get(1).y)){
										centerls.add(Map.plan_p1.x);
										centerls.add(Map.plan_p1.y);
									}
									else{
										centerls.add(Map.plan_p2.x);
										centerls.add(Map.plan_p2.y);
									}
								}
						}					
					}
					
					
					 //getCircleLineIntersectionPoint(Point2D.Double pointA,
					 //	Point2D.Double pointB, Point2D.Double center, double radius)
					 
				 }
				 
				 else if(l13.ptLineDist(r1) <= l12.ptLineDist(r1)&&l13.ptLineDist(r1)<= l23.ptLineDist(r1)){
					 System.out.println("361++++++++++++++++++++++++++++++++++");
					 Point2D.Double l13_1= new Point2D.Double(l13.x1,l13.y1);
					 Point2D.Double l13_2= new Point2D.Double(l13.x2,l13.y2);
					 List<Point2D.Double> List = new ArrayList<Point2D.Double>();
					 List = getCircleLineIntersectionPoint(l13_1,l13_2,fist_center,dist);
					 
						if(List.size()==0){
							System.out.println("318++++++++++++++++++++++++++++++++++");
							if(Map.plan_p1.distance(r1) < Map.plan_p2.distance(r1)&&Map.plan_p1.x>fist_center.getX()){
								centerls.add(Map.plan_p1.x);
								centerls.add(Map.plan_p1.y);
							}
							else if(Map.plan_p1.distance(r1) > Map.plan_p2.distance(r1)&&Map.plan_p2.x>fist_center.getX()){
								System.out.println("324++++++++++++++++++++++++++++++++++");
								centerls.add(Map.plan_p2.x);
								centerls.add(Map.plan_p2.y);
							}
							else{
								System.out.println("329++++++++++++++++++++++++++++++++++");
							}
						}	
						else if( List.size()==1){
							System.out.println("333++++++++++++++++++++++++++++++++++");
							
							centerls.add(List.get(0).x);
							centerls.add(List.get(0).y);
							
						}
						
						else if( List.size()==2){
							System.out.println("340++++++++++++++++++++++++++++++++++");
							if(r1.distance(List.get(0).x,List.get(0).y)<
							r1.distance(List.get(1).x,List.get(1).y)){
								System.out.println("446++++++++++++++++++++++++++++++++++");
								System.out.println(List.get(0).x+" , "+List.get(0).y);
								
								if(pol.contains(List.get(0).x,List.get(0).y)){
									System.out.println("449++++++++++++++++++++++++++++++++++");
									
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(0));
									System.out.println(pol.get_xpoint(1)+" , "+pol.get_ypoint(1));
									System.out.println(pol.get_xpoint(2)+" , "+pol.get_ypoint(2));
									
									System.out.println("455++++++++++++++++++++++++++++++++++");
												
									System.out.println(List.get(0).x+" , "+List.get(0).y);
									
								centerls.add(List.get(0).x);
								centerls.add(List.get(0).y);
								}
								
								else{
									
									if(l13.getP1().distance(List.get(0).x,List.get(0).y)
									   <l13.getP2().distance(List.get(0).x,List.get(0).y)){
										System.out.println("455++++++++++++++++++++++++++++++++++");
										centerls.add(Map.plan_p1.x);
										centerls.add(Map.plan_p1.y);
									}
									else{
										System.out.println("460++++++++++++++++++++++++++++++++++");
										centerls.add(Map.plan_p3.x);
										centerls.add(Map.plan_p3.y);
									}
								}
								
							}
							else{
								System.out.println("347++++++++++++++++++++++++++++++++++");
								if(pol.contains(List.get(1).x,List.get(1).y)){
									System.out.println(List.get(1).x+" , "+List.get(1).y);
									centerls.add(List.get(1).x);
									centerls.add(List.get(1).y);
									}
									else{
										if(l13.getP1().distance(List.get(1).x,List.get(1).y)
										   <l13.getP2().distance(List.get(1).x,List.get(1).y)){
											centerls.add(Map.plan_p1.x);
											centerls.add(Map.plan_p1.y);
										}
										else{
											centerls.add(Map.plan_p3.x);
											centerls.add(Map.plan_p3.y);
										}
									}
							}					
						}
				 }
				 
				 
				 else if(l23.ptLineDist(r1) < l13.ptLineDist(r1)&&l23.ptLineDist(r1)< l12.ptLineDist(r1)){
					 System.out.println("403++++++++++++++++++++++++++++++++++");
					 Point2D.Double l23_1= new Point2D.Double(l23.x1,l23.y1);
					 Point2D.Double l23_2= new Point2D.Double(l23.x2,l23.y2);
					 List<Point2D.Double> List = new ArrayList<Point2D.Double>();
					 List = getCircleLineIntersectionPoint(l23_1,l23_2,fist_center,dist);
			 
						if(List.size()==0){
							System.out.println("318++++++++++++++++++++++++++++++++++");
							if(Map.plan_p1.distance(r1) < Map.plan_p2.distance(r1)&&Map.plan_p1.x>fist_center.getX()){
								centerls.add(Map.plan_p1.x);
								centerls.add(Map.plan_p1.y);
							}
							else if(Map.plan_p1.distance(r1) > Map.plan_p2.distance(r1)&&Map.plan_p2.x>fist_center.getX()){
								System.out.println("324++++++++++++++++++++++++++++++++++");
								centerls.add(Map.plan_p2.x);
								centerls.add(Map.plan_p2.y);
							}
							else{
								System.out.println("329++++++++++++++++++++++++++++++++++");
							}
						}	
						else if( List.size()==1){
							System.out.println("333++++++++++++++++++++++++++++++++++");
							
							centerls.add(List.get(0).x);
							centerls.add(List.get(0).y);
							
						}
						
						else if( List.size()==2){
							System.out.println("340++++++++++++++++++++++++++++++++++");
							if(r1.distance(List.get(0).x,List.get(0).y)<
							r1.distance(List.get(1).x,List.get(1).y)){
								
								System.out.println(List.get(0).x+" , "+List.get(0).y);
								
								if(pol.contains(List.get(0).x,List.get(0).y)){
									System.out.println("540++++++++++++++++++++++++++++++++++");
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(0));
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(1));
									System.out.println(pol.get_xpoint(0)+" , "+pol.get_ypoint(2));
									System.out.println(List.get(0).x+" , "+List.get(0).y);
								centerls.add(List.get(0).x);
								centerls.add(List.get(0).y);
								}
								else{
									if(l23.getP1().distance(List.get(0).x,List.get(0).y)
									   <l23.getP2().distance(List.get(0).x,List.get(0).y)){
										
										centerls.add(Map.plan_p2.x);
										centerls.add(Map.plan_p2.y);
									}
									else{
										centerls.add(Map.plan_p3.x);
										centerls.add(Map.plan_p3.y);
									}
								}
								
							}
							else{
								System.out.println("347++++++++++++++++++++++++++++++++++");
								if(pol.contains(List.get(1).x,List.get(1).y)){
									System.out.println(List.get(1).x+" , "+List.get(1).y);
									centerls.add(List.get(1).x);
									centerls.add(List.get(1).y);
									}
									else{
										if(l23.getP1().distance(List.get(1).x,List.get(1).y)
										   <l23.getP2().distance(List.get(1).x,List.get(1).y)){
											centerls.add(Map.plan_p2.x);
											centerls.add(Map.plan_p2.y);
										}
										else{
											centerls.add(Map.plan_p3.x);
											centerls.add(Map.plan_p3.y);
										}
									}
							}					
						}
	 
				 }
				 
				}
				
	///////////////////////////////////////////////////////////////////////////////////////////////////////			
				
				/*
				rotation_2(h1, k1, h, k);
				
				
			
				centerls.add(ls.get(0));
				centerls.add(ls.get(1));
				*/
				
///////////////////////////////////////////////////////////////////////////////////////////////////////				
				
				
				//for (i = 0; i < centerls.size(); i++)
				//	System.out.println("centerls:" + centerls.get(i));

				//for (i = 0; i < linels.size(); i++)
				//	System.out.println("linels:" + linels.get(i));
				//System.out.println("linels size:" + i);
				// line 在 linels中 center在centerls

				tmp = 0;


				for (; z < centerls.size();) {

					newlinels.clear();
					for (j = 0; j < linels.size(); j = j + 4) {
						// line
						

						// cover(my_line_x_str[j], my_line_y_str[j],
						// my_line_x_end[j], my_line_y_end[j], X[i], Y[i], r,
						// line_x, line_y, line_xx, line_yy, line_x1, line_y1,
						// line_xx1, line_yy1);

						cover(linels.get(j), linels.get(j + 1), linels.get(j + 2),
								linels.get(j + 3), centerls.get(z),
								centerls.get(z + 1), r);

						for (tmp = 0; tmp < coverls.size(); tmp++)
							newlinels.add(coverls.get(tmp));

					}

					linels.clear();
					z = z + 2;

					for (i = 0; i < newlinels.size(); i++)
						linels.add(newlinels.get(i));
/*
					System.out.println("stop:" + stop);
					System.out.println("z:" + z);
					System.out.println("centerls.size:" + centerls.size());
*/
					
			//		for (i = 0; i < linels.size(); i++)
			//			System.out.println("linels:" + linels.get(i));
			
					System.out.println("linels size:" + i);
				}

				if (linels.size() == 0)
					stop = 1;


			} while (stop == 0);
	/////////////////////////////////////////////////////////////////////////////////
		for(i=0;i<PK.Line.size();i++){
			PK.Line.get(i).show();
			Line2D.Double l = new Line2D.Double();
			l = Map.project(PK.Line.get(i));
			System.out.println("Line2D : " + l.getX1()+" , "+l.getY1()+" , "+ l.getX2()+" , "+ l.getY2());
		}
		
		/*
		System.out.println("centerls:");	
		for(i=0;i<centerls.size();i++)
		 System.out.println(centerls.get(i));
		*/
		 
///////////////////////////////////////////////////////////////////////////////////// ls point to point3D		
		//	FileWriter fw1 = new FileWriter("center.txt",true);
			
			 ArrayList<Point3D> center = new ArrayList<Point3D>();
			
			for(i=0;i<centerls.size();i=i+2){
			/*
			System.out.println("D224:" +Map.plan_p1.x+","+Map.plan_p1.y);	
			System.out.println("D224:" +Map.plan_p2.x+","+Map.plan_p2.y);	
			System.out.println("D224:" +Map.plan_p3.x+","+Map.plan_p3.y);
			*/
			System.out.println("centerls"+i+":"+centerls.get(i)+","+ centerls.get(i+1));
			
			
			
			
		   // if(pol.contains(centerls.get(i),centerls.get(i+1))==true)
		   // {	
				
		    	if(Map.plan_p1.distance(centerls.get(i), centerls.get(i+1))==0){
				Point3D p = Map.p1;
				center.add(p);
				//System.out.println("center3D:"+p.getX()+","+p.getX()+","+p.getZ());
		    	}
			
		    	else if(Map.plan_p2.distance(centerls.get(i), centerls.get(i+1))==0){
				Point3D p = Map.p2;
				center.add(p);
				//System.out.println("center3D:"+p.getX()+","+p.getX()+","+p.getZ());
		    	}
			
		    	else if(Map.plan_p3.distance(centerls.get(i), centerls.get(i+1))==0){
				Point3D p = Map.p3;
				center.add(p);
				//System.out.println("center3D:"+p.getX()+","+p.getX()+","+p.getZ());
		    	}
			
		    	else{
			/*
		    		System.out.println("Map.plan_p1:"+Map.plan_p1.x+","+Map.plan_p1.y);
		    		System.out.println("Map.plan_p2:"+Map.plan_p2.x+","+Map.plan_p2.y);
		    		System.out.println("Map.plan_p3:"+Map.plan_p3.x+","+Map.plan_p3.y);
		    		
		    		System.out.println("Map.p1:"+Map.p1.getX()+","+Map.p1.getY()+","+Map.p1.getZ());
		    		System.out.println("Map.p2:"+Map.p2.getX()+","+Map.p2.getY()+","+Map.p2.getZ());
		    		System.out.println("Map.p3:"+Map.p3.getX()+","+Map.p3.getY()+","+Map.p3.getZ());
		    		
		    		System.out.println("Map.plan_p12:"+Map.plan_p1.distance(Map.plan_p2));
		    		System.out.println("Map.plan_p23:"+Map.plan_p2.distance(Map.plan_p3));
		    		System.out.println("Map.plan_p13:"+Map.plan_p1.distance(Map.plan_p3));
		    		System.out.println("Map.p12:"+Map.p1.distance(Map.p2));
		    		System.out.println("Map.p23:"+Map.p2.distance(Map.p3));
		    		System.out.println("Map.p13:"+Map.p1.distance(Map.p3));
			
				System.out.println("center2D:"+centerls.get(i)+","+centerls.get(i+1));
				*/
					Point2D.Double center2D = new Point2D.Double(centerls.get(i), centerls.get(i+1));
					
					
					System.out.println("center2D : "+center2D.getX()+","+center2D.getY());
					
					
		    		Point3D p =
					Point2D_to_Point3D.Calcute(Map, center2D);
					center.add(p);
					
					
					
			System.out.println("center3D:"+p.getX()+","+p.getY()+","+p.getZ());
			
			
			//}
			//System.out.println("center2D:"+centerls.get(i)+","+centerls.get(i+1));
			
			
	//		fw1.write(center.getX()+","+center.getY()+","+center.getZ());
		//	System.out.println("CT3D:"+CT.toLatitude_and_Longitude(center.getX(),center.getY()).getX()+","+CT.toLatitude_and_Longitude(center.getX(),center.getY()).getY()+","+center.getZ());
		    }
		    
		}    
		    
	//		fw1.close();
		//System.out.println("stop" + stop);
/////////////////////////////////////////////////////////////////////////////////////	
			
			
			
	/*	
		if(centerls.size()>0){
			System.out.println("centerls");
		for(int cls=0;cls<centerls.size();cls++){
			System.out.println("center:"+centerls.get(cls) + ",");
					
		}
		System.out.println("number of bs.size():"+centerls.size()/2);
		}
	*/	
/////////////////////////////////////////////////////////////////////////////////////		
		
		BufferedWriter fw = null;
		
			
			try {
		
			//File file = new File("center.csv");
			FileWriter fw1 = new FileWriter("center.txt",true);
			
			for (i = 0; i < center.size(); i++) {
				
				
				//CT.toLatitude_and_Longitude(centerls.get(i), centerls.get(i+1)).getX();
				
				String b =  String.valueOf(center.get(i).getX());
				String bb =  String.valueOf(center.get(i).getY());
				String bbb =  String.valueOf(center.get(i).getZ())
						;
				//String b = String.valueOf(CT.toLatitude_and_Longitude(centerls.get(i), centerls.get(i+1)).getX());
				//String bb =  String.valueOf(CT.toLatitude_and_Longitude(centerls.get(i), centerls.get(i+1)).getY());
				//fw1.write(b+" "+bb+" "+bbb+"\r\n");
				fw1.write(center.get(i).getX()+" "+center.get(i).getY()+" "+center.get(i).getZ() +"\r\n");
				
			}
			fw1.close();
		} catch (Exception e) {

			e.printStackTrace();
		
		} finally {

			
			if (fw != null) {

				try {

					fw.close();

				} catch (IOException e) {

					e.printStackTrace();

				}
			}
		}
	
	
	//////////////////////////////////////////////////////////	
			
		
			
			try {
		
			//File file = new File("center.csv");
			FileWriter fw1 = new FileWriter("center_ LngLat.txt",true);
			
			for (i = 0; i < center.size(); i++) {
				
				
				//CT.toLatitude_and_Longitude(centerls.get(i), centerls.get(i+1)).getX();
				
				
				String b =  String.valueOf(CT.convert(center.get(i).getX(),center.get(i).getY()).getX());
				String bb =  String.valueOf(CT.convert(center.get(i).getX(),center.get(i).getY()).getY());
				String bbb =  String.valueOf(center.get(i).getZ())
						;
				//String b = String.valueOf(CT.toLatitude_and_Longitude(centerls.get(i), centerls.get(i+1)).getX());
				//String bb =  String.valueOf(CT.toLatitude_and_Longitude(centerls.get(i), centerls.get(i+1)).getY());
				//fw1.write(b+" "+bb+" "+bbb+"\r\n");
				fw1.write(b+" "+bb+" "+bbb +"\r\n");
				
			}
			fw1.close();
		} catch (Exception e) {

			e.printStackTrace();
		
		} finally {

			
			if (fw != null) {

				try {

					fw.close();

				} catch (IOException e) {

					e.printStackTrace();

				}
			}
		}
			
			
	///////////////////////////////////////////////////////////		
	}

	
	public static Line2D.Double line3D_to_2D(Line3D l3d, TIN_Point TP){
		
		Point3D tmp = new Point3D(TP.getX(),TP.getY(),TP.getZ());
		Line2D.Double l2d = new Line2D.Double(mesh_and_plan.project(l3d.origin().distance(TP.getX(),TP.getY(),TP.getZ()), tmp, l3d.origin())
				,mesh_and_plan.project(l3d.end().distance(TP.getX(),TP.getY(),TP.getZ()), tmp, l3d.end()));
		
		//System.out.println(mesh_and_plan.project(l3d.origin().distance(TP.getX(),TP.getY(),TP.getZ()), tmp, l3d.origin().x()));
		
		return l2d;
	}
	
	
	public static Point2D.Double find_leftmost(ArrayList<Line2D.Double> L_list){
		
		Point2D.Double leftmost = new Point2D.Double();
		
		for(int i=0; i<L_list.size();i++){
			if(L_list.get(i).getX1()<leftmost.getX()){
				leftmost.x = L_list.get(i).getX1();
				leftmost.y = L_list.get(i).getY1();
			}
			else if(L_list.get(i).getX2()<leftmost.getX()){
				leftmost.x = L_list.get(i).getX2();
				leftmost.y = L_list.get(i).getY2();
			}
		
		}
		return leftmost;
	}
	
	
	static void line(double x1, double y1, double x2, double y2) // 兩點求線方程式
	{

		double a, b, c;
		a = (y2 - y1) / (x2 - x1);
		b = -1;
		//c = y1 - a * x1;
		c=-1*(a*x1+b*y1);
		ls.clear();
		ls.add(a);
		ls.add(b);
		ls.add(c);
	}

	
	
	public static double dist(double x1, double y1, double x2, double y2) {
		
		//Point2D.Double.distance(x1, y1, x2, y2);
		//double dist1;
		//if (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) != 0)
		//	dist1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		//else
		//	dist1 = 0.0000001;
		 
		
		return Point2D.Double.distance(x1, y1, x2, y2);
		//return dist1;
	}

	
	public static double angle(double x1, double y1, double x2, double y2,
			double x3, double y3)// 角x2, y2
	{
		double d;
		double a, b, c;
		if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2))
				* Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2)) != 0) {
			d = ((x1 - x2) * (x3 - x2) + (y1 - y2) * (y3 - y2))
					/ (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) * Math
							.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2)));
			a = (x1 - x2) * (x3 - x2) + (y1 - y2) * (y3 - y2);
			b = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
			c = Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2);
			b = Math.sqrt(b);
			// b=(double)((int)(b*point)/point);
			c = Math.sqrt(c);
			// c= (double)((int)(c*point)/point);
			d = a / (b * c);

			if (d < -1 && d > -2)
				d = -1;
			if (d > 1 && d < 2)
				d = 1;

			d = (double) ((int) (d * point) / point);
			d = Math.acos(d);
			d = d * (180.0 / 3.1415926);
			// ca= (double)((int)(d*point)/point);
			return d;
		} else
			return 0;
	}

	
	static void angular(double x1, double y1, double x2, double y2, double x3,
			double y3, double a1, double b1, double c1, double a2, double b2,
			double c2) // 角平分線 (三點座標和兩線)double &a, double &b, double &c
	{
		double t, a = 0, b = 0, c = 0;

		t = angle(x1, y1, x2, y2, x3, y3);
        //double tt= t%180;
        
		//System.out.println("t: " +t);
		
		if (t <= 90) // 銳角
		{
			a = a1 * (Math.sqrt(a2 * a2 + b2 * b2)) + a2
					* (Math.sqrt(a1 * a1 + b1 * b1));
			// i=a*point;
			// a=i/point;

			b = b1 * (Math.sqrt(a2 * a2 + b2 * b2)) + b2
					* (Math.sqrt(a1 * a1 + b1 * b1));
			// i=b*point;
			// b=i/point;

			c = c1 * (Math.sqrt(a2 * a2 + b2 * b2)) + c2
					* (Math.sqrt(a1 * a1 + b1 * b1));
			// i=c*point;
			// c=i/point;
		}

		if (t > 90) // 鈍角
		{
			a = a1 * (Math.sqrt(a2 * a2 + b2 * b2)) - a2
					* (Math.sqrt(a1 * a1 + b1 * b1));
			// i=a*point;
			// a=i/point;
			b = b1 * (Math.sqrt(a2 * a2 + b2 * b2)) - b2
					* (Math.sqrt(a1 * a1 + b1 * b1));
			// i=b*point;
			// b=i/point;
			c = c1 * (Math.sqrt(a2 * a2 + b2 * b2)) - c2
					* (Math.sqrt(a1 * a1 + b1 * b1));
			// i=c*point;
			// c=i/point;
		}
		ls.clear();
		ls.add(a);
		ls.add(b);
		ls.add(c);
		//System.out.println("abc"+a1+" , "+b1+" , "+c1);
	}

	static Point2D.Double center_on_angular(double a, double b, double c, double h,
			double k, double r, double x1, double y1, double x2, double y2,
			double x3, double y3) // 求分角線上之圓心
									// 由上下點與焦點產生角選較大的, double &X, double &Y
	{
		Point2D.Double p =new Point2D.Double();
		System.out.println("center_on_angular");
		// int tj;
		double A, B, C, t, t1, xx, yy, x, y, cx, cy, kk, bb;

		t = Math.sqrt(3.0) * r; // 根號3r
		

		cx = h;
		cy = k;
		kk = -1 * a / b;
		bb = -1 * c / b;

		//System.out.println("a : "+ a);
		//System.out.println("b : "+ b);
		
		A = (1 + kk * kk); // (1 + k*k);
		B = (2 * cx - 2 * kk * (bb - cy)); // (2*cx - 2*k*(b - cy))
		C = cx * cx + (bb - cy) * (bb - cy) - t * t; // cx*cx + (b - cy)*(b- cy)
		
		// -r*r

		t1 = Math.sqrt(B * B - 4 * A * C);

		x = (B + t1) / (2 * A); // x1 = ( b1 + tmp )/(2*a);
		y = kk * x + bb; // y1 = k*x1 + b;

		xx = (B - t1) / (2 * A);
		yy = kk * xx + bb;

		t = angle(x1, y1, xx, yy, x3, y3);
		t1 = angle(x1, y1, x, y, x3, y3);

		if (t > t1) {
			// int i, j;
			// i=x*point;
			// j=y*point;
			ls.clear();
			ls.add(xx);
			ls.add(yy);
			p.x = xx;
			p.y = yy;
			return p;
		}

		else {
			// int i, j;
			ls.clear();
			ls.add(x);
			ls.add(y);
			
			p.x = xx;
			p.y = yy;
			return p;

			// X=i/point;
			// Y=j/point;
		}
	}

	
	static void rotation_0(double x, double y, double h, double k) // double
	// &x1,
	// double
	// &y1
{
// int tj;
// int i, j;
double x1, y1;
x = x-h ;
y = y-k ;

x1 = x * Math.cos(270 * (3.14159265 / 180.0)) + y
* Math.sin(270 * (3.14159265 / 180.0)) + h;
// i=x1*point;
// x1=i/point;

y1 = -x * Math.sin(270* (3.14159265 / 180.0)) + y
* Math.cos(270 * (3.14159265 / 180.0)) + k;
// j=y1*point;
// y1=j/point;

ls.clear();
ls.add(x1);
ls.add(y1);
}
	
	
	static void rotation_1(double x, double y, double h, double k) // double
																	// &x1,
																	// double
																	// &y1
	{
		// int tj;
		// int i, j;
		double x1, y1;
		x = x - h;
		y = y - k;

		x1 = x * Math.cos(60 * (3.14159265 / 180.0)) + y
				* Math.sin(60 * (3.14159265 / 180.0)) + h;
		// i=x1*point;
		// x1=i/point;

		y1 = -x * Math.sin(60 * (3.14159265 / 180.0)) + y
				* Math.cos(60 * (3.14159265 / 180.0)) + k;
		// j=y1*point;
		// y1=j/point;

		ls.clear();
		ls.add(x1);
		ls.add(y1);
	}

	static void rotation_2(double x, double y, double h, double k) // , double
																	// &x1,
																	// double
																	// &y1
	{
		double x1, y1;
		x = x - h;
		y = y - k;

		x1 = x * Math.cos(300 * (3.14159265 / 180.0)) + y
				* Math.sin(300 * (3.14159265 / 180.0)) + h;
		// i=x1*point;
		// x1=i/point;
		y1 = -1 * x * Math.sin(300 * (3.14159265 / 180.0)) + y
				* Math.cos(300 * (3.14159265 / 180.0)) + k;
		// j=y1*point;
		// y1=j/point;

		ls.clear();
		ls.add(x1);
		ls.add(y1);
	}

	static void intersect(double a, double b, double c, double h, double k,
			double r) // 求圓與線之交點 double &X, double &Y, double &XX, double &YY
						// 兩個交點
	{

		double A, B, C, t1, cx, cy, kk, bb;
		double X, Y, XX, YY;
		cx = h;
		cy = k;
		kk = -a / b;
		bb = -c / b;

		A = (1 + kk * kk); // (1 + k*k);
		B = (2 * cx - 2 * kk * (bb - cy)); // (2*cx - 2*k*(b - cy))
		C = cx * cx + (bb - cy) * (bb - cy) - r * r; // cx*cx + (b - cy)*(b- cy)
														// -r*r

		if (B * B - 4 * A * C <= 0) {
			X = -1;
			Y = -1;
			XX = -1;
			YY = -1;
		} else {
			t1 = Math.sqrt(B * B - 4 * A * C);

			X = (B + t1) / (2 * A); // x1 = ( b1 + tmp )/(2*a);
			Y = kk * X + bb; // y1 = k*x1 + b;

			XX = (B - t1) / (2 * A);
			YY = kk * XX + bb;
		}
		
		
		ls.clear();
		ls.add(X);
		ls.add(Y);
		ls.add(XX);
		ls.add(YY);
	}

	static void cover(double x_str, double y_str, double x_end, double y_end,
			double h, double k, double r)
	// double &line_x, double &line_y, double &line_xx, double &line_yy,double
	// &line_x1, double &line_y1, double &line_xx1, double &line_yy1
	{
		//System.out.println("cover funtion");
		double a, b, c, x, y, xx, yy;
		ls.clear();
		line(x_str, y_str, x_end, y_end);
		//System.out.println("line");
		a = ls.get(0);
		b = ls.get(1);
		c = ls.get(2);
		intersect(a, b, c, h, k, r); // 兩交點 x,y ; xx, yy
		//System.out.println("lntersect");
		x = ls.get(0);
		y = ls.get(1);
		xx = ls.get(2);
		yy = ls.get(3);
		//coverls.clear();
		// h,k 圓心;
		if (x == -1 && y == -1 && xx == -1 && yy == -1) // 無交點
		{
			//System.out.println("case1");
			coverls.clear();
			coverls.add(x_str);
			coverls.add(y_str);
			coverls.add(x_end);
			coverls.add(y_end);
			// coverls.add(-1.0);
			// coverls.add(-1.0);
			// coverls.add(-1.0);
			// coverls.add(-1.0);
			// line_x= x_str; // ----------------
			// line_y= y_str; // ----------------
			// line_x1= x_end; // ----------------
			// line_y1= y_end; // ----------------
			// line_xx= -1; // ----------------
			// line_yy= -1; // ----------------
			// line_xx1= -1; // ----------------
			// line_yy1= -1;
		}

		else if (dist(x_str, y_str, h, k) <= r
				&& (dist(x_end, y_end, h, k) <= r))// 兩端點皆被覆蓋
		{
			//System.out.println("case2");
			coverls.clear();
		} // 兩端點皆被覆蓋

        else if(dist(x, y, h, k) > r &&dist(xx, yy, h, k) > r && dist(x_str, y_str, h, k) > r&& dist(x_end, y_end, h, k) > r){
			
			coverls.clear();
			coverls.add(x_str);
			coverls.add(y_str);
			coverls.add(x_end);
			coverls.add(y_end);
		}
		
		else if ((dist(x_str, y_str, h, k) > r && dist(x_end, y_end, h, k) > r) // 兩端點未被覆蓋
				&& ((x >= x_end && x <= x_str) || (x >= x_str && x <= x_end))
				&& ((xx >= x_end && xx <= x_str) || (xx >= x_str && xx <= x_end)) 
				&&((dist(x, y, h, k) <= r)&&(dist(xx, yy, h, k) <= r))// 2交點在端點之間
		) // 圓交兩點皆在線段內且不覆蓋兩端點
		{
			if (dist(x, y, x_str, y_str) <= dist(x, y, x_end, y_end)) // ----------------
			{ // -----------------
				//System.out.println("case3");
				coverls.clear();
				coverls.add(x_str);
				coverls.add(y_str);
				coverls.add(x);
				coverls.add(y);
				coverls.add(xx);
				coverls.add(yy);
				coverls.add(x_end);
				coverls.add(y_end);
				
			} // ----------------
			else if(dist(x, y, x_str, y_str) > dist(x, y, x_end, y_end)){
				coverls.clear();
				coverls.add(x_str);
				coverls.add(y_str);
				coverls.add(xx);
				coverls.add(yy);
				coverls.add(x);
				coverls.add(y);
				coverls.add(x_end);
				coverls.add(y_end);
			}
			else if (dist(xx, yy, x_str, y_str) < dist(xx, yy, x_end, y_end)) // ----------------
			{ // ----------------
				//System.out.println("case4");
				coverls.clear();
				coverls.add(x_str);
				coverls.add(y_str);
				coverls.add(xx);
				coverls.add(yy);
				coverls.add(x);
				coverls.add(y);
				coverls.add(x_end);
				coverls.add(y_end);
			} // ----------------
			else if (dist(xx, yy, x_str, y_str) > dist(xx, yy, x_end, y_end)) // ----------------
			{ // ----------------
				//System.out.println("case4");
				coverls.clear();
				coverls.add(x_str);
				coverls.add(y_str);
				coverls.add(x);
				coverls.add(y);
				coverls.add(xx);
				coverls.add(yy);
				coverls.add(x_end);
				coverls.add(y_end);
			}
			
			else {
				coverls.clear();
				coverls.add(x_str);
				coverls.add(y_str);
				coverls.add(x_end);
				coverls.add(y_end);
			}
			
			
		}// 圓交兩點皆在線段內且不覆蓋兩端點 // ----------------

		else if (((x <= x_end && x >= x_str) || (x <= x_str && x >= x_end)) // -----
				&& ((xx >= x_end && xx >= x_str) || (xx <= x_str && xx <= x_end)) // *
		) // x,y 在兩端點間 xx, yy 不在 //*
		{ // *
			// *
			if (dist(x_str, y_str, h, k) <= r // *
					 // str 被cover end 沒被cover
														// //*
			) // *
			{ // *
				//System.out.println("case5");
				coverls.clear();
				coverls.add(x);
				coverls.add(y);
				coverls.add(x_end);
				coverls.add(y_end);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// line_x= x; //*
				// line_y= y; //*
				// line_x1= x_end; //*
				// line_y1= y_end; //*
				// line_xx= -1; //*
				// line_yy= -1; //*
				// line_xx1= -1; //*
				// line_yy1= -1; //*
			} // -----

			else if (dist(x_str, y_str, h, k) > r
					) // //end 被cover str
														// 沒被cover
			{
				//System.out.println("case6");
				coverls.clear();
				coverls.add(x);
				coverls.add(y);
				coverls.add(x_str);
				coverls.add(y_str);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// line_x= x;
				// line_y= y;
				// line_x1= x_str;
				// line_y1= y_str;
				// line_xx= -1;
				// line_yy= -1;
				// line_xx1= -1;
				// line_yy1= -1;
			}
			
			else {
				coverls.clear();
				coverls.add(x_str);
				coverls.add(y_str);
				coverls.add(x_end);
				coverls.add(y_end);
			}
			
		}// x,y 在兩端點間 xx, yy 不在

		else if (((xx >= x_end && xx <= x_str) || (xx >= x_str && xx <= x_end))
				&& ((x >= x_end && x >= x_str) || (x <= x_str && x <= x_end))) // xx,yy
																				// 在兩端點間
																				// x,
																				// y
																				// 不在
		{
			if (dist(x_str, y_str, h, k) <= r ) // str
																				// 被cover
																				// end
																				// 沒被cover
		{

				//System.out.println("case7");
				coverls.clear();
				coverls.add(xx);
				coverls.add(yy);
				coverls.add(x_end);
				coverls.add(y_end);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// line_x= xx;
				// line_y= yy;
				// line_x1= x_end;
				// line_y1= y_end;
				// line_xx= -1;
				// line_yy= -1;
				// line_xx1= -1;
				// line_yy1= -1;
			}

			else if (dist(x_str, y_str, h, k) > r
					) // //end 被cover str
														// 沒被cover
			{
				//System.out.println("case8");
				coverls.clear();
				coverls.add(xx);
				coverls.add(yy);
				coverls.add(x_str);
				coverls.add(y_str);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// coverls.add(-1.0);
				// line_x= xx;
				// line_y= yy;
				// line_x1= x_str;
				// line_y1= y_str;
				// line_xx= -1;
				// line_yy= -1;
				// line_xx1= -1;
				// line_yy1= -1;
			}
			
			else {
				
				coverls.clear();
				coverls.add(x_str);
				coverls.add(y_str);
				coverls.add(x_end);
				coverls.add(y_end);
			}
           
		} // xx,yy 在兩端點間 x, y 不在

		else {
			//System.out.println("case9");
			coverls.clear();
			coverls.add(x_str);
			coverls.add(y_str);
			coverls.add(x_end);
			coverls.add(y_end);
		}

	}

	
public Point2D.Double getIntersectionPoint(Line2D.Double line1, Line2D.Double line2) {
		    if (! line1.intersectsLine(line2) )
		    	{
		    	System.out.println(" line1.intersectsLine(line2) :" + line1.intersectsLine(line2));
		    	return null;
		    	}
		      
		    double px = line1.getX1(),
		            py = line1.getY1(),
		            rx = line1.getX2()-px,
		            ry = line1.getY2()-py;
		      double qx = line2.getX1(),
		            qy = line2.getY1(),
		            sx = line2.getX2()-qx,
		            sy = line2.getY2()-qy;

		      double det = sx*ry - sy*rx;
		      
		      if (det == 0) {
		    	  System.out.println("det :+++++++++++++++++++ "+det);
		        return null;
		      }
		      
		      else {
		        double z = (sx*(qy-py)+sy*(px-qx))/det;
		        System.out.println("z : "+z);
		        
		        if (z==0 ||  z==1){ 
		        System.out.println(px+" , "+py);
		        System.out.println(rx+" , "+ry);
		        System.out.println(qx+" , "+qy);
		        System.out.println(sx+" , "+sy);
		        
		        if(rx ==0 && ry==0)
		        {
		        	Point2D.Double p =  new Point2D.Double((px), (py));
		        	return p;
		        }
		        else
		        {
		        	Point2D.Double p =  new Point2D.Double((qx), (qy));
		        	return p;
		        	  // intersection at end point!
		        }
		        }
		        
		        Point2D.Double p =  new Point2D.Double(
		          (px+z*rx), (py+z*ry));
		        
		        p.x =  (px+z*rx);
		        p.y = (py+z*ry);
		        
		        return p;
		      }
		 }




    public static List<Point2D.Double> getCircleLineIntersectionPoint(Point2D.Double pointA,
    		Point2D.Double pointB, Point2D.Double center, double radius) {
        double baX = pointB.x - pointA.x;
        double baY = pointB.y - pointA.y;
        double caX = center.x - pointA.x;
        double caY = center.y - pointA.y;

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return Collections.emptyList();
        }
        // if disc == 0 ... dealt with later
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        Point2D.Double p1 = new Point2D.Double(pointA.x - baX * abScalingFactor1, pointA.y
                - baY * abScalingFactor1);
        if (disc == 0) { // abScalingFactor1 == abScalingFactor2
            return Collections.singletonList(p1);
        }
        Point2D.Double p2 = new Point2D.Double(pointA.x - baX * abScalingFactor2, pointA.y
                - baY * abScalingFactor2);
        
        System.out.println("getCircleLineIntersectionPoint : ");
        System.out.println(pointA.getX()+" , "+ pointA.getY());
        System.out.println(pointB.getX()+" , "+ pointB.getY());
        System.out.println(p1.getX()+", "+p1.getY());
        System.out.println(p2.getX()+", "+p2.getY());
        System.out.println();
        
        return Arrays.asList(p1, p2);
    }

 




}
