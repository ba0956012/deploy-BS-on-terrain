import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class call_geotest {

	public static void new_TIN(int Num_of_Point, int Num_of_triangle, ArrayList<TIN_Point> P, ArrayList<Triangel> T) throws IOException{
		FileWriter fw = new FileWriter("newTIN.txt");
		fw.write(Num_of_Point+" "+Num_of_triangle+"\r\n");
		for(int i=0;i<P.size();i++){
			fw.write(P.get(i).getX()+"	"+P.get(i).getY()+"	"+P.get(i).getZ()+"\r\n");
		}
		for(int i=0;i<T.size();i++){
			fw.write(T.get(i).p_id[0]+"	"+T.get(i).p_id[1]+"	"+T.get(i).p_id[2]+"\r\n");
		}
		fw.close();
	}
	
	
	public static void geotest(int Num_of_Point, int Num_of_triangle, ArrayList<TIN_Point> P, ArrayList<Triangel> T) throws IOException, InterruptedException{
		new_TIN(Num_of_Point,Num_of_triangle,P,T);	
		 int sv;
		 sv =P.size()-2;
		 int ev;
		 ev = P.size()-1;
		    
	    
	    String[] cmd = new String[3];
	    cmd[0] = "cmd.exe" ;
	    
	    
	    Process process = new ProcessBuilder("geotest.exe","newTIN.txt",Integer.toString(sv), Integer.toString(ev)).start();
	   
	    Thread t = new Thread(new SubTask(process.getInputStream()));
	    t.start();
	    t.join();
	    
	    
	    try {
			process.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   
	
}

}
