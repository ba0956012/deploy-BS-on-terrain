
import javafx.geometry.Point3D;

public class TIN_Point extends Point3D{
		public TIN_Point(double x, double y, double z) {
			super(x, y, z);
			// TODO Auto-generated constructor stub
		}
		int id;
		
	public TIN_Point clone(){
		
		TIN_Point clone = new TIN_Point(this.getX(),this.getY(),this.getZ());
		clone.id = this.id;
		return clone;		
	}
			
	
	public double distance(org.jlab.geom.prim.Point3D point3d){
			
		
		return this.distance(point3d.x(), point3d.y(), point3d.z());	
	}
	
}
