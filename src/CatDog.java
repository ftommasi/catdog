import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class CatDog {
	
    private static boolean findConflict(String c, String d){
        String firststart = c.substring(0,2);
        String firstend =   c.substring(3,5);
        String secondstart = d.substring(0,2);
        String secondend = d.substring(3,5);
        /*
        System.err.println("----------------------------");
        System.err.println(c +" | "+d);
        System.err.println("first " + firststart +"-"+secondend + " || " +firstend+"-"+secondstart);
        System.err.println(firststart.compareTo(secondend) + " || " +  firstend.compareTo(secondstart)); 
        System.err.println("----------------------------");
        */
        if(firststart.compareTo(secondend) == 0 || firstend.compareTo(secondstart) ==0 ){
            return true;
        }
        return false;
    }
        
    private static boolean bpm(boolean bpGraph[][], int u, boolean seen[], int matchR[],int n,int m){
        int N = n;
        int M = m; 
        
        for (int v = 0; v < N; v++){
            
            if (bpGraph[u][v] && !seen[v]){
                seen[v] = true; 
 
               
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR,N,M)){
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
    
    private static int maxBPM(boolean bpGraph[][],int n, int m){
    	//M is cats, N is dogs
        int N = n;
        int M = m;
        
        //relational array flow from cat to dog
        int matchR[] = new int[N];
 
        
        for(int i=0; i<N; ++i)
            matchR[i] = -1;
 
        int result = 0; 
        for (int u = 0; u < M; u++){
           
            boolean seen[] =new boolean[N] ;
            for(int i=0; i<N; ++i){
                seen[i] = false;
            }
 
            
            if (bpm(bpGraph, u, seen, matchR,N,M)){
                result++;
            }
        }
        return result;
    }
 
 //standard bfs on adjacency matrix using queue   
 boolean bfs(int rGraph[][], int s, int t, int parent[],int Vsize){
	 int V = Vsize;
	 boolean visited[] = new boolean[V];
	 for(int i=0; i<V; ++i)
		 visited[i]=false;
	 LinkedList<Integer> queue = new LinkedList<Integer>();
	 queue.add(s);
	 visited[s] = true;
	 parent[s]=-1;
	 while (queue.size()!=0){
		 int u = queue.poll();
		 for (int v=0; v<V; v++) {
			 if (visited[v]==false && rGraph[u][v] > 0){
				 queue.add(v);
				 parent[v] = u;
				 visited[v] = true;
			 }
		 }
	 }
	 return (visited[t] == true);
 }


 int fordFulkerson(int graph[][], int s, int t,int Vsize){
	 int u, v;
	 int V = Vsize;

	 int rGraph[][] = new int[V][V];

	 for (u = 0; u < V; u++)
		 for (v = 0; v < V; v++)
			 rGraph[u][v] = graph[u][v];
	 int parent[] = new int[V];

	 int max_flow = 0;

	 while (bfs(rGraph, s, t, parent,Vsize)){
		 int path_flow = Integer.MAX_VALUE;
		 for (v=t; v!=s; v=parent[v]){
			 u = parent[v];
			 path_flow = Math.min(path_flow, rGraph[u][v]);
		 }
		 for (v=t; v != s; v=parent[v]){
			 u = parent[v];
			 rGraph[u][v] -= path_flow;
			 rGraph[v][u] += path_flow;
		 }

		 max_flow += path_flow;
	 }
	 return max_flow;
 }
    
    public static void main(String[] args) throws IOException{
    	//System.setIn(new FileInputStream("in.txt")); // for local use only (eclipse)
		Scanner sc = new Scanner(System.in);		
		int n = Integer.parseInt(sc.next());
		System.err.println("N is " + Integer.toString(n));
		for(int ITERATIONS=0;ITERATIONS<n;ITERATIONS++){
			
			int c,d,v;            
			c = Integer.parseInt(sc.next());
			System.err.println("C is " + Integer.toString(c));
			d = Integer.parseInt(sc.next());
			System.err.println("D is " + Integer.toString(d));
			v = Integer.parseInt(sc.next());
			System.err.println("V is " + Integer.toString(v));           
		   
			ArrayList<String> catlovers = new ArrayList<String>();
		    ArrayList<String> doglovers = new ArrayList<String>();
		    ArrayList<String> ALvoters = new ArrayList<String>();
		    int[][] adjmat = new int[v][v];
		    int [] adjlist = new int[v];
		    
		    for(int i=0; i < v; i++){
		    	adjmat [i][i] = -1; //set diagonal to infinity
		    }
		       
           
         
           for(int votes =0; votes<v;votes++){
        	   String choice = null;
        	   
        		   
        	   choice = sc.next() +" " +sc.next();
        		  	   
        	   
        	   System.err.println("Choice is " + choice);
        	   
        	   if(choice.startsWith("C")){
               		catlovers.add(choice);               		
                }
                else{
                	 doglovers.add(choice);              
                }
        	   
        	   ALvoters.add(choice);
        	   

			}
          
           //adjmat building
           for(int i=0; i < ALvoters.size(); i++){
    		   for(int j=0; j < ALvoters.size();j++){
    			   if(i!=j){
    				   if(findConflict(ALvoters.get(i),ALvoters.get(j))){
    					   adjmat[i][j] = 1;
    					   adjmat[j][i] = 1; //mirror accross diagonal
    				   }
    			   }
    		   }
    		   
    	   }
           
           //adjlist building
           
           boolean[][] voters = new boolean[catlovers.size()][doglovers.size()];
           
           for(int i = 0; i < catlovers.size(); i++){
        	   for(int j = 0; j < doglovers.size(); j++){
        		   voters[i][j] = false;
        		   
        	   }
           }
           
           for(int i = 0; i < catlovers.size(); i++){
        	   for(int j = 0; j < doglovers.size(); j++){
        		   if(findConflict(catlovers.get(i),doglovers.get(j))){
           			voters[i][j] = true;
           			
                   }
        		   
               }
        	   
      
           
           /*
           //+1 because 0 = source and size() = sink
           int[][] ivoters = new int[catlovers.size()+1][doglovers.size()+1];   
    	   for(int i = 0; i < catlovers.size()+1; i++){
        	   for(int j = 0; j < doglovers.size()+1; j++){
        		   ivoters[i][j]=0;
        	   }
        	}
    	   //set flow from super sink and super source to 1
    	   for(int j = 0; j < doglovers.size()+1; j++){
    		   ivoters[0][j] = 1;
    		   ivoters[catlovers.size()][j] = 1;
    	   }
    	   
    	   for(int i = 0; i < catlovers.size(); i++){
        	   for(int j = 0; j < doglovers.size(); j++){
        		   if(findConflict(catlovers.get(i),doglovers.get(j))){
           			ivoters[i+1][j+1] = 1;
           			
                   }
        		   
               }
               */
    	   }
            /*
            System.err.print("xxxxx| ");
            for(int i=0; i< currcat; i++){
            	System.err.print(catlovers.get(i) + " | ");
            }
            System.err.println("");
            int dogs = 0;
            for(int i=0; i< currcat; i++){
            	//System.err.print(doglovers.get(dogs) + "| ");
            	int newdogs = dogs+1;
            	dogs = newdogs >= doglovers.size()-1 ? doglovers.size(): newdogs;
            	for(int j=0; j<currdog;j++){
              			System.err.print(voters[i][j] + "  | ");
              			
                    }
            	System.err.println("");
                }
              */       
            int result = maxBPM(voters,doglovers.size(),catlovers.size());            
            System.err.print(v +" ");  
            System.err.print(result + " ");            
            System.out.println(v-result);
           
		}
	}
}
