import java.util.ArrayList;

public class Polygon2D_to_Triangle {
	
	public static Triangel Polygon2D_to_Triangle(Polygon2D p, ArrayList<TIN_Point> p_List){
	Triangel t = new Triangel();
	ArrayList<Integer> id = new ArrayList<Integer>(); 
	
	for(int i=0;i<3;i++){
		//p.get_xpoint(i);
		
		for(int j=0;j<p_List.size();j++){
		  if(p.get_xpoint(i)==p_List.get(j).getX()&&p.get_ypoint(i)==p_List.get(j).getY())
			  id.add(j);
		  
		}
		
	 }
	
	
	for(int k=0;k<3;k++)
	{	
		t.p_id[k]=id.get(k);
		//System.out.println("id.get(k):"+id.get(k));
	}
	return t;
	}

}
