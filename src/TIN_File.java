import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jlab.geom.prim.Point3D;

public class TIN_File {

	 ArrayList<TIN_Point> p_List = new ArrayList<TIN_Point>();
     ArrayList<Triangel> t_List = new ArrayList<Triangel>(); 

public void create_TIN_Point(ArrayList<Point3D> TIN_List){
	
	for(int i=0; i<TIN_List.size();i++){
		if(Check_Point(TIN_List.get(i))==false){
			TIN_Point TP = new TIN_Point(TIN_List.get(i).x(),TIN_List.get(i).y(),TIN_List.get(i).z());
			TP.id = p_List.size();
			//TP.point =TIN_List.get(i); 
			p_List.add(TP);
		}
	}

}

public void create_TIN_mesh(ArrayList<Point3D> TIN_List){
	
	for(int i=0; i<TIN_List.size();i=i+3){
		//Find_PointIndex(TIN_List.get(i));
		Triangel T = new Triangel();
		T.p_id[0] = Find_PointIndex(TIN_List.get(i));
		T.p_id[1] = Find_PointIndex(TIN_List.get(i+1));
		T.p_id[2] = Find_PointIndex(TIN_List.get(i+2));
		T.id = t_List.size();
		t_List.add(T);
	}

}

public void create_TIN(ArrayList<Point3D> TIN_List){
	create_TIN_Point(TIN_List);
	create_TIN_mesh(TIN_List);
}

public void create_TIN_File(String FilePath) throws IOException{
	
	FileWriter FW = new FileWriter(FilePath);
	FW.write(p_List.size()+"	"+t_List.size()+"\r\n");
	for(int i=0; i<p_List.size();i++)
	{
		FW.write(p_List.get(i).getX()+"	"+p_List.get(i).getY()+"	"+p_List.get(i).getZ()+"\r\n");
	}
	
	for(int i=0; i<t_List.size();i++)
	{
		FW.write(t_List.get(i).p_id[0]+"	"+t_List.get(i).p_id[1]+"	"+t_List.get(i).p_id[2]+"\r\n");
	}
	FW.close();
}


public boolean Check_Point(Point3D p){
	boolean c = false;	

	for(int i=0; i<p_List.size();i++){
		if(p.x()==p_List.get(i).getX()
			&&p.y()==p_List.get(i).getY()
			&&p.z()==p_List.get(i).getZ())
			c=true;
	}
	return c;
}

public int Find_PointIndex(Point3D p){
	int id = 0;
	for(int i=0; i<p_List.size(); i++){
		if(p.x()==p_List.get(i).getX()&&
				p.y()==p_List.get(i).getY()&&
				p.z()==p_List.get(i).getZ()){
			id = p_List.get(i).id;
			}
	}
	return id;
}

}
