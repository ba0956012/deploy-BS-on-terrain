import java.awt.List;
import java.awt.Point;
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
	  
	    public boolean contains(double x, double y) {
	        if (npoints <= 2) {
	        	System.out.println("npoints <= 2");
	            return false;
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
	                if (x > lastx) {
	                    continue;
	                }
	                leftx = curx;
	            } else {
	                if (x > curx) {
	                    continue;
	                }
	                leftx = lastx;
	            }
	  
	            double test1, test2;
	            if (cury < lasty) {
	                if (y < cury || y > lasty) {
	                    continue;
	                }
	                if (x <= leftx) {
	                    hits++;
	                    continue;
	                }
	                test1 = x - curx;
	                test2 = y - cury;
	            } 
	            else {
	                if (y < lasty || y > cury) {
	                    continue;
	                }
	                if (x <= leftx) {
	                    hits++;
	                    continue;
	                }
	                test1 = x - lastx;
	                test2 = y - lasty;
	            }
	  
	            if (test1 <= (test2 / (lasty - cury) * (lastx - curx))) {
	                hits++;
	            }
	        }
	  
	        return ((hits & 1) != 0);
	    }
	  
	    public boolean contains(Point2D p) {
	        return contains(p.getX(), p.getY());
	    }	
}

