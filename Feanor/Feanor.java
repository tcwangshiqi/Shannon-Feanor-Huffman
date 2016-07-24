package Feanor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * 
 * @author tcwangshiqi
 * @category Feanor
 * @since 2015.10.16
 */

public class Feanor {
	public static final int length = 16;
	public static final String dataPath = "C:\\Users\\Pro\\Desktop\\b.txt";
	
	public static void main(String args[]) throws FileNotFoundException {
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
	    }
	    
	    sort(list);
	    cal(list);
	    for(int i=0;i<list.size();i++) {
	    	System.out.println("P["+(i+1)+"]="+list.get(i).weigh+"  s="+list.get(i).s);
	    }
	    FileOutputStream fs = new FileOutputStream(new File(dataPath));
		PrintStream p = new PrintStream(fs);
		p.println("费诺编码：");
		for(int i=0;i<list.size();i++) {
	    	p.println("P["+(i+1)+"]="+list.get(i).weigh+"  s="+list.get(i).s);
	    }
		p.close();
	    
	   
	}
	public static class Node1 {
		public double weigh;
		
		public int K;
		public int num;
		
		public String s = "";
		public Node1(double value , int i) {
			this.weigh = value;
			this.num = i;
		}		
	}
	
	public static void sort(ArrayList<Node1> list) {
		Node1 temp;
	    for(int i=0;i<list.size();i++){//趟数
	      for(int j=0;j<list.size()-i-1;j++){//比较次数
	        if(list.get(j).weigh<list.get(j+1).weigh){
	          temp = list.get(j);
	          list.set(j,list.get(j+1));
	          list.set(j+1,temp);
	          
	        }
	      }
	    }
	}
	
	
	
	static void cal(ArrayList<Node1> list) {
		if(list.size() == 1) {
			return;
		}
		
		double sum = 0.0;//总和
		double sum1 = 0.0;//记录到一半和的值
		int k = 0;//记录分组的num
		for(int i=0;i<list.size();i++) {
			sum += list.get(i).weigh;
		}
		for(int i=0;i<list.size();i++) {
			sum1 += list.get(i).weigh;
			if(sum1>=0.5*sum) {
				k = i;
				break;
			}
			if((sum1-0.5*sum)*(sum1+list.get(i+1).weigh-0.5*sum)<0) {
				k = i;
				if((0.5*sum-sum1)>(sum1+list.get(i+1).weigh-0.5*sum)) {
					k = i+1;
				}
				break;
			}
		}
		ArrayList<Node1> list1 = new ArrayList<Node1>();
		ArrayList<Node1> list2 = new ArrayList<Node1>();
		for(int i=0;i<k+1;i++) {
			list1.add(list.get(i));
			list.get(i).s += "0";
		}
		for(int i=k+1;i<list.size();i++) {
			list2.add(list.get(i));
			list.get(i).s += "1";
		}
		cal(list1);
		cal(list2);
		
		
	}
	
	 
}
