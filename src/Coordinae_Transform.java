

import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.jlab.geom.prim.Point3D;

public class Coordinae_Transform {
	double xllcorner;//左下角x經度
	double yllcorner;//左下角x緯度
	double cellsize; //grid 間隔多少度
	
public Point2D toC_coordinate(double d, double e){
	Point2D.Double p = new Point2D.Double();
	
	p.x = d*cellsize*(1.0/111000.0);
	p.y = e*cellsize*(1.0/111000.0);
	//p.x = d;
	//p.y = e;
	return p;
}
	
public Point2D toLatitude_and_Longitude(double x, double y){
	Point2D.Double p = new Point2D.Double();
	
	p.x = (x*0.00000900900901)+xllcorner;
	p.y = (y*0.00000900900901)+yllcorner;
	return p;
}

public Point3D ASCtoLatitude_and_Longitude(double x, double y, double z){
	Point3D p = new Point3D();
	p.set((x*cellsize)+xllcorner, (y*cellsize)+yllcorner, z);
	
	return p;
}


public void GeneratePath(int Num_of_Line, int ncols,int ycols) throws IOException{
	FileWriter FW = new FileWriter("path.txt");
	
	for(int i =0; i<Num_of_Line;i++){
	Random ran = new Random();
    int xx = ran.nextInt(ncols*10);
    int yy =  ran.nextInt(ycols*10);
    double x = xx/10*cellsize+xllcorner;
    double y = yy/10*cellsize+yllcorner;
    FW.write(x+" "+y+"\r\n");
	}
    FW.close();
}

public void G_Path_grid(int Num_of_Line, int ncols,int ycols) throws IOException{
	FileWriter FW = new FileWriter("Path_grid.txt");
			
	for(int i =0; i<Num_of_Line;i++){
	Random ran = new Random();
    int x = ran.nextInt(ncols);
    int y =  ran.nextInt(ycols);
    
    FW.write(x+" "+y+"\r\n");
	}
    FW.close();
}


}
