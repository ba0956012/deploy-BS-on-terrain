import java.awt.List;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Polygon;
import java.awt.Shape;



	public class Polygon2D {
		  
	    private int npoints;
	  
	    private double xpoints[];
	  
	    private double ypoints[];
	  
	    private static final int MIN_LENGTH = 3;
	  
	    public Polygon2D() {
	        xpoints = new double[MIN_LENGTH];
	        ypoints = new double[MIN_LENGTH];
	    }
	  

	    public Polygon2D(double xpoints[], double ypoints[], int npoints) {
	        if (npoints > xpoints.length || npoints > ypoints.length) {
	            throw new IndexOutOfBoundsException("npoints > xpoints.length || "+
	                                                "npoints > ypoints.length");
	        }
	        if (npoints < 0) {
	            throw new NegativeArraySizeException("npoints < 0");
	        }
	        this.npoints = npoints;
	        this.xpoints = Arrays.copyOf(xpoints, npoints);
	        this.ypoints = Arrays.copyOf(ypoints, npoints);
	    }
	  
	    public void reset() {
	        npoints = 0;
	    }
	  
	    public double get_xpoint(int i){
	    	
	    	return this.xpoints[i];
	    }
	    
	  
	    public double get_ypoint(int i){
	    	
	    	return this.ypoints[i];
	    }
	    
	    
	    public void addPoint(double x, double y) {
	        if (npoints >= xpoints.length || npoints >= ypoints.length) {
	            int newLength = npoints * 2;
	            // Make sure that newLength will be greater than MIN_LENGTH and
	            // aligned to the power of 2
	            if (newLength < MIN_LENGTH) {
	                newLength = MIN_LENGTH;
	            } else if ((newLength & (newLength - 1)) != 0) {
	                newLength = Integer.highestOneBit(newLength);
	            }
	  
	            xpoints = Arrays.copyOf(xpoints, newLength);
	            ypoints = Arrays.copyOf(ypoints, newLength);
	        }
	        xpoints[npoints] = x;
	        ypoints[npoints] = y;
	        npoints++;
	    }
	  
	    public boolean contains(Point p) {
	        return contains(p.x, p.y);
	    }
	  
	    public boolean contains(int x, int y) {
	        return contains((double) x, (double) y);
	    }
	  
	    /*
	    public boolean contains(double x, double y) {
	    	if (npoints <= 2) {
	    	    return false;
	    	}
	    	
	    	double tolerance = 1.0; //若點在邊上判斷誤差容許值
	    	
	    	Line2D.Double l = new Line2D.Double();
	        for(int j=0; j<2; j++){
	        	l.setLine(this.xpoints[j],this.ypoints[j], this.xpoints[j+1], this.ypoints[j+1]);
	        	if(l.ptLineDist(x,y) <= tolerance){
	        		return true;
	        	}
	        }
	        l.setLine(this.xpoints[2],this.ypoints[2], this.xpoints[0], this.ypoints[0]);
	        if(l.ptLineDist(x,y) <= tolerance){
	        	return true;
        	}
	    	
	    	int hits = 0;

	    	double lastx = xpoints[npoints - 1];
	    	double lasty = ypoints[npoints - 1];
	    	double curx, cury;

	    	// Walk the edges of the polygon
	    	for (int i = 0; i < npoints; lastx = curx, lasty = cury, i++) {
	    	    curx = xpoints[i];
	    	    cury = ypoints[i];

	    	    if (cury == lasty) {
	    		continue;
	    	    }

	    	    double leftx;
	    	    if (curx < lastx) {
	    		if (x >= lastx) {
	    		    continue;
	    		}
	    		leftx = curx;
	    	    } else {
	    		if (x >= curx) {
	    		    continue;
	    		}
	    		leftx = lastx;
	    	    }

	    	    double test1, test2;
	    	    if (cury < lasty) {
	    		if (y < cury || y >= lasty) {
	    		    continue;
	    		}
	    		if (x < leftx) {
	    		    hits++;
	    		    continue;
	    		}
	    		test1 = x - curx;
	    		test2 = y - cury;
	    	    } else {
	    		if (y < lasty || y >= cury) {
	    		    continue;
	    		}
	    		if (x < leftx) {
	    		    hits++;
	    		    continue;
	    		}
	    		test1 = x - lastx;
	    		test2 = y - lasty;
	    	    }

	    	    if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
	    		hits++;
	    	    }
	    	}

	    	
	    	
	    	return ((hits & 1) != 0);
	    }
	  */
	    
	    
	    public boolean contains(Point2D p) {
	        return contains(p.getX(), p.getY());
	    }	
	    
	    
	   
	    
	    
	    public boolean contains(double x, double y) { //Point2D test, 
	        int i;
	        int j;
	       double tmp_xpoints[] = new double[3];
	  	  
		   double tmp_ypoints[] = new double[3];
	        
		   
		   double tx = 0 , ty=0;
		   
		   for(int k=0 ; k<xpoints.length;k++){
			   
			   if(xpoints[k]<tx)
			   {
				   tx= xpoints[k];
			   }
			   if(x<tx)
				   tx = x;
			   
		   }
		   
		   for(int k=0 ; k<ypoints.length;k++){
			   
			   if(ypoints[k]<ty)
			   {
				   ty= ypoints[k];
			   }
			   if(y<ty)
				   ty = y;
			   
		   }
	        
		   
		   if(tx<0){
	        	for(int k=0 ; k<xpoints.length;k++)
	        		tmp_xpoints[k] = xpoints[k]-tx+1;
	        	x = x-tx+1;
	        }
	        
	        else{
	        	for(int k=0 ; k<xpoints.length;k++)
	        		tmp_xpoints[k] = xpoints[k];
	        }
	        
	        
	        if(ty<0){
	        	for(int k=0 ; k<ypoints.length;k++)
	        		tmp_ypoints[k] = ypoints[k]-ty+1;
	        	y = y-ty+1;
	        }
	      
	      
	        
	        else{
	        	for(int k=0 ; k<ypoints.length;k++)
	        		tmp_ypoints[k] = ypoints[k];
	        }
	        
	        
	        
	        double tolerance = 1.0; //若點在邊上判斷誤差容許值
	    	
	     	Line2D.Double l = new Line2D.Double();
	        for(int k=0; k<2; k++){
	        	l.setLine(tmp_xpoints[k],tmp_ypoints[k], tmp_xpoints[k+1], tmp_ypoints[k+1]);
	        	if(l.ptLineDist(x,y) <= tolerance&&
	        			((x<tmp_xpoints[k] && x>tmp_xpoints[k+1])
	        			  ||(x<tmp_xpoints[k+1] && x>tmp_xpoints[k])) 
	        	  ){
	        		return true;
	        	}
	        }
	        l.setLine(tmp_xpoints[2],tmp_ypoints[2], tmp_xpoints[0], tmp_ypoints[0]);
	        if(l.ptLineDist(x,y) < tolerance&&((x<tmp_xpoints[0] && x>tmp_xpoints[2])||(x<tmp_xpoints[2] && x>tmp_xpoints[0])) ){
	        	return true;
        	}
	        
	        
	        
	        
	        
	        if (npoints <= 2) {
	    	    return false;
	    	}
	    	
	    	
	        
	        boolean result = false;
	        for (i = 0, j = xpoints.length - 1; i < xpoints.length; j = i++) {
	          if ((tmp_ypoints[i] > y) != (tmp_ypoints[j] > y) &&
	              (x < (tmp_xpoints[j] - tmp_xpoints[i]) * (y - tmp_ypoints[i]) / (tmp_ypoints[j]-tmp_ypoints[i]) + tmp_xpoints[i])) {
	            result = !result;
	           }
	        }
	        return result;
	      }
	
	    
	    
	
}

