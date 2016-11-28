import java.io.IOException;

public class CatDog {
	
    private static boolean findConflict(String c, String d){
        String firststart = c.substring(0,1);
        String firstend =   c.substring(3,4);
        String secondstart = d.substring(0,1);
        String secondend = d.substring(3,4);
        if(firststart.compareTo(secondend) == 0 || firstend.compareTo(secondstart) ==0 ){
            return true;
        }
        return false;
    }
    
   
    private static boolean bpm(boolean bpGraph[][], int u, boolean seen[],
                int matchR[])
    {
        int N = bpGraph.length;
        int M = bpGraph[0].length; 
        
        for (int v = 0; v < N; v++)
        {
            
            if (bpGraph[u][v] && !seen[v])
            {
                seen[v] = true; // Mark v as visited
 
                // If job 'v' is not assigned to an applicant OR
                // previously assigned applicant for job v (which
                // is matchR[v]) has an alternate job available.
                // Since v is marked as visited in the above line,
                // matchR[v] in the following recursive call will
                // not get job 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
                                         seen, matchR))
                {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
 
    // Returns maximum number of matching from M to N
    private static int maxBPM(boolean bpGraph[][])
    {
        int N = bpGraph.length;
        int M = bpGraph[0].length;
        
        int matchR[] = new int[N];
 
        
        for(int i=0; i<N; ++i)
            matchR[i] = -1;
 
        int result = 0; 
        for (int u = 0; u < M; u++)
        {
           
            boolean seen[] =new boolean[N] ;
            for(int i=0; i<N; ++i)
                seen[i] = false;
 
            
            if (bpm(bpGraph, u, seen, matchR))
                result++;
        }
        return result;
    }
 
    
    public static void main(String[] args) throws IOException{
		
		int n = Integer.getInteger(System.console().readLine());
		for(int ITERATIONS=0;ITERATIONS<n;ITERATIONS++){
			
			int c,d,v;
                        String line = System.console().readLine();
                        String[] chars = line.split(" ");
			c = Integer.getInteger(chars[0]);
			d = Integer.getInteger(chars[1]);
			v = Integer.getInteger(chars[2]);
                        String[] catlovers = new String[c];
                        String[] doglovers = new String[d];
                        boolean[][] voters = new boolean[c][d];
                        for(int i=0; i< c; i++){
                           for(int j=0; j<d;j++){
                               voters[i][j] = false;
                           }
                        }
                        int currcat =0;
                        int currdog=0;
                        int currvote=0;
			for(int votes =0; votes<v;votes++){
                            String choice = System.console().readLine();
                            if(choice.startsWith("C")){
                                catlovers[currcat] = choice;
                                currcat++;
                            }
                            else{
                                doglovers[currdog] = choice;
                                currdog++;
                            }
                            //voters[currcat][currdog] = choice;
			}                        
                       for(int i=0; i< c; i++){
                           for(int j=0; j<d;j++){
                               if(findConflict(catlovers[i],doglovers[j])){
                                   voters[i][j] = true;
                               }
                           }
                       }
                       
                       int result = v - maxBPM(voters);
		}
	}
}
