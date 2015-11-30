

import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.jlab.geom.prim.Point3D;

public class Coordinate_Transform {
	int ncols;
	int ycols;
	double xllcorner;//左下角x經度
	double yllcorner;//左下角x緯度
	double cellsize; //grid 間隔多少度
	//1度 == 111000公尺
	
public Point2D to_grid(double d, double e){
	Point2D.Double p = new Point2D.Double();
	
	p.x = (d-this.xllcorner)/ cellsize;
	p.y = ycols - (e-this.yllcorner)/cellsize;	
	
	//p.x = d;
	//p.y = e;
	//System.out.println("Point2D to_grid :");
	//System.out.println("Point2D :" + d +","+e);
	//System.out.println("grid :" + p.x +","+p.y);
	return p;
}
	
public Point2D toLatitude_and_Longitude(double x, double y){
	Point2D.Double p = new Point2D.Double();
	
	p.x = (x*cellsize)+xllcorner;
	p.y = ((y-ycols)*cellsize)+yllcorner;
	//System.out.println("Point2D toLatitude_and_Longitude :");
	//System.out.println("Point2D :" + x +","+y);
	//System.out.println("Latitude_and_Longitude :" + p.x +","+p.y);
	return p;
}

public double range(double r){
	return (r/111000)/cellsize;
	
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
