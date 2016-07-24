package Shannon;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.Math;

/**
 * 
 * @author tcwangshiqi
 * @category Shannon
 * @since 2015.10.16
 */
public class Shannon {
	
	public static final int length = 16;
	public static final String dataPath = "C:\\Users\\Pro\\Desktop\\a.txt";
	
	public static void main(String args[]) throws Exception {
		
		ArrayList<Node1> list = new ArrayList<Node1>();
	    double[] value = new double[length];
	    double sum = 0;
	    for(int i=0;i<length;i++){  
	        value[i]=(int) (Math.random()*100);  
	        sum += value[i];  
	    }  
	    for(int i=0;i<length;i++){  
	        value[i] = value[i]/sum; 
	        Node1 n = new Node1(value[i],i); 
	        list.add(n);
	        for(int j=0;j<i;j++) {
	        	n.P += list.get(j).weigh;
	        }
	    }  
	    FileOutputStream fs = new FileOutputStream(new File(dataPath));
		PrintStream p = new PrintStream(fs);
		p.println("ÏãÅ©±àÂë");
	    for(int i=0;i<length;i++){
	    	list.get(i).cal();
	    	System.out.println("P["+(list.get(i).num+1)+"]="+list.get(i).weigh+",k="+list.get(i).K+",P="+list.get(i).P+",string="+list.get(i).s);
	    	 
			p.println("P["+(list.get(i).num+1)+"]="+list.get(i).weigh+",k="+list.get(i).K+",P="+list.get(i).P+",string="+list.get(i).s); 
	    }
	    
		 
		
		p.close();
	    
	}
	
	
	public static class Node1 {
		public double weigh;
		public double P;
		public int K;
		public int num;
		
		public String s = "";
		public Node1(double value , int i) {
			this.weigh = value;
			this.num = i;
			K = (int)(- Math.log((double)weigh)/Math.log((double)2))+1;
			
		}
		public void cal() {
			for(int j=0;j<K;j++) {
				P = P*2;
				
				if(P<1) {
					s += "0";
				}
				else {
					s += "1";
					P -= 1;
				}
			}
		}
		
	}
}


 