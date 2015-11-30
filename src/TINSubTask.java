import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.jlab.geom.prim.Point3D;


public  class TINSubTask implements Runnable {

   	  
	public TINSubTask(InputStream istream)
   	  {
		 
   	    if (istream == null)
   	      iReader = new BufferedReader(new InputStreamReader(System.in));
   	    else
   	      iReader = new BufferedReader(new InputStreamReader(istream));
   	  }
   	  
   	  public  void run()
   	  {
   		
   		
   	    		try {
   	    			
   	    			String	input ;
   	    			int i = 0;
					 while ((input = iReader.readLine()) != null)
			   	      {
				/*		
			   	       	System.out.println(input);	
			   	     try 
			   	  { 
			   	  Thread.sleep(3000);//¹q¸£¼È°±¤@¬íÄÁ 
			   	  } catch(Exception e){} 
				*/
					
					//	if (i!=0)
				//		{
						 String tempArray[] = input.split(",");
//			   	       		System.out.println(i);
			   	    		Point3D p = new Point3D();
			   	    		//tempArray.length
			   	    		p.set(Double.parseDouble(tempArray[0]), Double.parseDouble(tempArray[1]), Double.parseDouble(tempArray[2]));
			   	    		//p.show(); 
			   	    		TIN_List.add(p);
			   	    //		i++;
					//	}	
					//	i++;
			   	      }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    	
   	       //n++;
   	      
   	    
   	    		
   	    
   	    
   	   
   	  }
   	public ArrayList<Point3D> isRunning()
    {
      return TIN_List;
    }
   	  private BufferedReader iReader;
   	  private  ArrayList<Point3D> TIN_List = new ArrayList<Point3D>(); ;
   	}

