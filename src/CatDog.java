import java.awt.Point;
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
        if(firststart.compareTo(secondend) == 0 || firstend.compareTo(secondstart) ==0 ){
            return true;
        }
        return false;
    }
        
 //standard bfs on adjacency matrix using queue   
 static boolean bfs(int residualGraph[][], int s, int t, int parent[],int Vsize){
	 int V = Vsize;
	 boolean visited[] = new boolean[V];
	 for(int i=0; i<V; i++)
		 visited[i]=false;
	 LinkedList<Integer> queue = new LinkedList<Integer>();
	 queue.add(s);
	 visited[s] = true;
	 parent[s]=-1;
	 while (!queue.isEmpty()){
		 int u = queue.poll();
		 for (int v=0; v<V; v++) {
			 if (visited[v]==false && residualGraph[u][v] > 0){
				 queue.add(v);
				 parent[v] = u;
				 visited[v] = true;
			 }
		 }
	 }
	 return (visited[t] == true);
 }


 static int  fordFulkerson(int graph[][], int s, int t,int Vsize,ArrayList<String> voters){
	 int u, v;
	 int V = Vsize;

	 int residualGraph[][] = new int[V][V];

	 for (u = 0; u < V; u++)
		 for (v = 0; v < V; v++)
			 residualGraph[u][v] = graph[u][v];
	 int parent[] = new int[V];

	 int max_flow = 0;

	 while (bfs(residualGraph, s, t, parent,Vsize)){
		 int path_flow = Integer.MAX_VALUE;
		 for (v=t; v!=s; v=parent[v]){
			 u = parent[v];
			 path_flow = Math.min(path_flow, residualGraph[u][v]);
                         
                                 
		 }
		 for (v=t; v != s; v=parent[v]){
			 u = parent[v];
			 residualGraph[u][v] -= path_flow; // reduce capacity
			 residualGraph[v][u] += path_flow; //update reverse flow
                         
		 }

		 max_flow += path_flow;
	 }
         /*
         System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.print("S | ");
         for(int i=0; i< voters.size(); i++){
             System.out.print(voters.get(i)+" | ");
         }
         System.out.print("T     |");
         System.out.println("");
           for(int i=0; i < V; i++){
                for(int j=0; j < V; j++){
                    System.out.print( residualGraph[i][j]+ " |     ");
                }
                System.out.println("");
            }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      
         for(int i=1;i<V-2;i++){
             
                 if(residualGraph[0][i] == 1){ // check vertices that
                     for(int j =1; j<V-2; j++){
                         if(residualGraph[j][i] == 0){
                            System.out.println("Keeping " + voters.get(i).substring(0, 2));
                         }
                     }
                     
                 }
                 if(residualGraph[i][V-1] == 1){
                     for(int j =0; j<V; j++){
                         if(residualGraph[j][i] == 0){
                            System.out.println("Keeping " + voters.get(i).substring(3, 5));
                         }
                     }
                 }
             
         }
         */
	 return max_flow;
 }
    
    public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(System.in);		
    int n = Integer.parseInt(sc.next());
    //System.err.println("N is " + Integer.toString(n));
    for(int ITERATIONS=0;ITERATIONS<n;ITERATIONS++){
			
        int c,d,v;            
        c = Integer.parseInt(sc.next());
        //System.err.println("C is " + Integer.toString(c));
        d = Integer.parseInt(sc.next());
        //System.err.println("D is " + Integer.toString(d));
        v = Integer.parseInt(sc.next());
        //System.err.println("V is " + Integer.toString(v));           

        ArrayList<String> ALvoters = new ArrayList<String>();
        int[][] adjmat = new int[v+2][v+2];// +2 [0] is source [v+1] is sink
       
       
        //initialize to zero
        for(int i=0; i < v+2; i++){
            for(int j=0; j < v+2; j++){
                adjmat [i][j] = 0; 
            }
        }
		       
           
         
           for(int votes =0; votes<v;votes++){
        	   String choice = null;
        	   
        		   
        	   choice = sc.next() + " " +sc.next();
        		  	   
        	   
        	   //System.err.println("Choice is " + choice);
        	  /* 
        	   if(choice.startsWith("C")){
               		catlovers.add(choice);               		
                }
                else{
                	 doglovers.add(choice);              
                }
        	   */
        	   ALvoters.add(choice);
        	   

			}
           //dont need to worry about adjmat[0][v+1] or adjmat[v+1][0] (s & t) 
        for(int i=1; i < v+1; i++){
            //link cat lovers to source (left bipartite)
            if(ALvoters.get(i-1).startsWith("C")){
                adjmat[0][i] = 1;
                
            }
            //connect doglovers to sink (right bipartite)
            if(ALvoters.get(i-1).startsWith("D")){
                adjmat[i][v+1] = 1;
                
            }
        }
        // the source will never have a direct edge to the sink
        //adjmat[0][v+1] = 0 ;
        //adjmat[v+1][0] = 0 ;
        
         //adjmat building (ALvoters.size() == v)
         
        for(int i=1; i < ALvoters.size()+1; i++){
            for(int j = 1; j < ALvoters.size()+1;j++){
                if(i!=j){
                    if(findConflict(ALvoters.get(i-1),ALvoters.get(j-1)) ){                        
                        if(ALvoters.get(i-1).startsWith("C")){
                            adjmat[i][j] = 1;
                        }
                        else{
                            adjmat[j][i] = 1;
                        }
                    }                    
                
                }   
                
            }    	
        }
        
        
         
        
/*
            for(int i=0; i < v+2; i++){
                for(int j=0; j < v+2; j++){
                   System.out.print( adjmat[i][j]+ " ");
                }
                System.out.println("");
            }
            */
            int result = fordFulkerson(adjmat,0,v+1,v+2,ALvoters);     
            //int result = FaustoBPM(ivoters,catlovers.size(),doglovers.size());
            System.out.println(v - result);
           
	}
    }
}
