import java.io.IOException;
import java.util.ArrayList;

import org.jlab.geom.prim.Point3D;

public class call_ReadTIN {


	public static ArrayList<Point3D> ReadTIN(String Read_Path, String TIN_Path) throws IOException, InterruptedException{
		
		
		   String[] cmd = new String[3];
		    cmd[0] = "cmd.exe" ;
		    cmd[1] = "/C" ;
		    //填入執行程式路徑
		    cmd[2] = Read_Path+" "+TIN_Path;
		    
		    
		    
		    Process process = Runtime.getRuntime().exec(cmd);
		    
		    TINSubTask ST = new TINSubTask(process.getInputStream());
		    		
		    ST.run();
		    process.waitFor();
		    
		    ArrayList<Point3D> TIN_List = new ArrayList<Point3D>();
		    TIN_List = ST.isRunning();
		    return TIN_List;
		    //new Thread(new SubTask(process.getInputStream())).start();
		    //new Thread(new SubTask(process.getErrorStream())).start();
		
		/*
		String[] cmd = new String[3];
		    cmd[0] = "cmd.exe" ;
		Process process = new ProcessBuilder(Read_Path, TIN_Path).start();
		Thread t = new Thread(new SubTask(process.getInputStream()));
		  ST.run();
		    process.waitFor();
		    
		    ArrayList<Point3D> TIN_List = new ArrayList<Point3D>();
		    TIN_List = ST.isRunning();
		t.start();	
		t.join();
		return TIN_List;
		*/
	}
	
	//public  PointList
/*
    public static void main(String[] args) throws IOException, InterruptedException{
    	ArrayList<Point3D> TIN_List = new ArrayList<Point3D>();
    	TIN_List = 
    	call_ReadTIN.ReadTIN("C:/Users/sclab/Desktop/readTIN.exe", "C:/Users/sclab/Desktop/N23/N23.tin");
    	TIN_File TF = new TIN_File();
    	TF.create_TIN(TIN_List);
    	TF.create_TIN_File("C://Users/sclab/Desktop/N23/JAVA_N231.tin");
    	//TIN_List.get(1).show();
    	
 }
 */   
    
}
