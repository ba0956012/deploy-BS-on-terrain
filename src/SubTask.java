import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jlab.geom.prim.Line3D;
import org.jlab.geom.prim.Point3D;

public  class SubTask implements Runnable {
	public static ArrayList<Point3D> geodesc = new  ArrayList<Point3D>();
   	  
	public SubTask(InputStream istream)
   	  {
   	    if (istream == null)
   	      iReader = new BufferedReader(new InputStreamReader(System.in));
   	    else
   	      iReader = new BufferedReader(new InputStreamReader(istream));
   	  }
   	  
   	  public void run()
   	  {
   		 int n=0;
   	    try {
   	      String input = iReader.readLine();
   	   //FileWriter fw = new FileWriter("geodesic.txt", true);
   	      while (input != null)
   	      {
   	        //若需執行結果可將input傳出
   	    	if(n>8)
   	    	{
   	    		//String str = null;
   	    		String tempArray[] = input.split("\\s");
   	    		  	    		
   	    		//fw.write(tempArray[0]+" "+tempArray[1]+" "+tempArray[2]+"\r\n");
   	    		Point3D p = new Point3D();
   	    		p.set(Double.parseDouble(tempArray[0]), Double.parseDouble(tempArray[1]), Double.parseDouble(tempArray[2]));
   	    		//p.show(); 
   	    		geodesc.add(p);
   	    	}
   	    		input = iReader.readLine();
   	        
   	       n++;
   	      }
   	     // fw.close(); 
   	    }
   	    catch (IOException ioe) {
   	    }
   	  }
   	  private BufferedReader iReader;
   	}

