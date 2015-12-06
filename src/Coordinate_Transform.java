

import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.jlab.geom.prim.Point3D;

public class Coordinate_Transform {
	int ncols;
	int ycols;
	double xllcorner;//撌虫�閫蝬漲
	double yllcorner;//撌虫�閫蝺臬漲
	double cellsize; //grid ��憭�摨�
	//1摨�== 111000�砍偕
	
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
	//return (r/111000)/cellsize;
	return r;
}
/*
public Point3D ASCtoLatitude_and_Longitude(double x, double y, double z){
	Point3D p = new Point3D();
	p.set((x*cellsize)+xllcorner, (y*cellsize)+yllcorner, z);
	
	return p;
}
*/

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


public Point3D ascGrid_to_meter_coor(double lat1, double lon1, Point3D P) //1 is base  to 2 target lat 嚙緯嚙踝蕭(y)
// lat1 is (y), lon1 is x, double lat2 (y), double lon2 (x))
{
	double lat2 = this.yllcorner+P.y()*this.cellsize;
	double lon2 = this.xllcorner+P.x()*this.cellsize;
	
	
	
	Point3D p = new Point3D();
	p.setY(distance2(lat1, lon1, lat2, lon1));
	p.setX(distance2(lat1, lon1, lat1, lon2));
	p.setZ(P.z());
	

	return p;
}

public Point2D ascGrid_to_meter_coor(double lat1, double lon1, Point2D P) //1 is base  to 2 target lat 嚙緯嚙踝蕭(y)
//lat1 is (y), lon1 is x, double lat2 (y), double lon2 (x))
{
	double lat2 = this.yllcorner+P.getY()*this.cellsize;
	double lon2 = this.xllcorner+P.getX()*this.cellsize;
	
	
	
	Point2D.Double p = new Point2D.Double();
	p.setLocation(distance2(lat1, lon1, lat1, lon2),distance2(lat1, lon1, lat2, lon1) );

	//p.setZ(P.z());
	

	return p;
}


public double measure(double lat1, double lon1, double lat2, double lon2){  // generally used geo measurement function
	double radLat1 = rad(lat1);
    double radLat2 = rad(lat2);
    double a = radLat1 - radLat2;
    double b = rad(lon1) - rad(lon2);
    double s = 2.0 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2.0),2.0) + 
     Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2.0),2.0)));
    s = s * EARTH_RADIUS;
    s = Math.round(s * 1000000.0) / 1000000.0;
    return s;
}
private static final double EARTH_RADIUS = 6378.137;
private static double rad(double d)
{	
   return d * Math.PI / 180.0;
}


public static double distance2( double lat1, double lon1, double lat2, double lon2){  // generally used geo measurement function
	    double R = 6378.137; // Radius of earth in KM
	    double dLat = (lat2 - lat1) * Math.PI / 180;
	    double dLon = (lon2 - lon1) * Math.PI / 180;
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
	    Math.sin(dLon/2) * Math.sin(dLon/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double d = R * c;
	    return d * 1000; // meters
	}



public double distVincenty(double lat1 ,double long1 ,double lat2 ,double long2) {
	double a = 6378137.0;
	double b = 6356752.314245;
	double f = 1/298.257223563;
	double L = Math.toDegrees(long2 - long1);
	double U1 = Math.atan((1 - f) * Math.tan(Math.toDegrees(lat1)));
	double U2 = Math.atan((1 - f) * Math.tan(Math.toDegrees(lat2)));
	double sinU1 = Math.sin(U1);
	double cosU1 = Math.cos(U1);
	double sinU2 = Math.sin(U2);
	double cosU2 = Math.cos(U2);
	double lambda = L;
	double iterLimit = 100.0;
	double cos2SigmaM = 0;
	double lambdaP = 0.0 , cosSqAlpha =0.0, sinSigma = 0.0, cosSigma = 0.0, sigma = 0.0;
    do{
		double sinLambda = Math.sin(lambda);
		double cosLambda = Math.cos(lambda);
    	 sinSigma = Math.sqrt((cosU2 * sinLambda) * (cosU2 * sinLambda) + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
        if( sinSigma == 0 ) {
            return 0;
        }
         cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
         sigma = Math.atan2(sinSigma, cosSigma);
        double sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
         cosSqAlpha = 1 - sinAlpha * sinAlpha;
        if( cosSqAlpha == 0 ) {
        	 cos2SigmaM = 0;
        } else {
        	 cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
        }
        double C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
         lambdaP = lambda;
        lambda = L + (1 - C) * f * sinAlpha * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
    }
    while(Math.abs(lambda - lambdaP) > 0.000000000001 && --iterLimit > 0);
    if( iterLimit == 0 ) {
        return 0;
    }
    double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
    double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
    double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
    double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
    return b * A * (sigma - deltaSigma);
}

}
