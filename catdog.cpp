#include <iostream>
#include <string>
#include <vector>
#include <string.h>

using namespace std;

#define DUMP 1

bool findConflicts(string c, string d){
  string catstart = c.substr(0,2);
  string dogstart = d.substr(0,2);
  string catend = c.substr(3,2);
  string dogend = d.substr(3,2);
  
  if(catstart.compare(dogstart) == 0 || catend.compare(dogstart) == 0)
    return true;

  return false;

}


int main(){
  int n;
  cin >> n;
#if DUMP
    cerr << n << endl;
#endif

  for(int ITERATIONS = 0; ITERATIONS < n; ITERATIONS++){
    int c,d,v;
    cin >> c >> d >> v; 
#if DUMP
    cerr << c << " " << d << " " << v << endl;
#endif
    bool bip[c][d];
    for(int i=0; i<c;i++){
      for(int j=0; j<d;j++){
        bip[i][j]=0;
      }
    }

    vector<string> cats;
    vector<string> dogs;
    vector<string> voters;
    for(int VOTERS =0; VOTERS < v; VOTERS++){
      string stay,leave;
      cin >> stay >>leave;

      string choice = stay + " " + leave;
#if DUMP
      cerr << choice << endl;
#endif
      voters.push_back(choice);
      if(choice[0] == 'C'){
        cats.push_back(choice);
      }
      else{
        dogs.push_back(choice);
      }
        
    }
#if DUMP
    //BEGIN DUMP
    cerr << endl;
    cerr << "xxxxx |";
    for(int i=0; i < cats.size(); i++){
      cerr << cats[i] << "|";
    }
    cerr << endl;
    int dogcounter =0;
    for(int i=0; i<d;i++){
      cerr << dogs[dogcounter] << " |  ";
      dogcounter = (++dogcounter > dogs.size() ?dogs.size() : dogcounter);
      for(int j=0; j<c;j++){
        cerr  <<  bip[j][i] << "  |  ";
      }
      cerr  << endl;
    }

      cerr << endl<< "------------------------------------------";
      cerr << endl;
#endif
      int result = 0;

      //to get maximum bipartite matching, we must consider vertices with less number of edges first. recursively assess

      cout << result;

  }
}

 #if 0  
      //bfs/dfs
      bool visited[V];
      memset(visited, 0, sizeof(visited));
 
    // Create a queue, enqueue source vertex and mark source vertex
    // as visited
    queue <int> q;
    q.push(s);
    visited[s] = true;
    parent[s] = -1;
 
    // Standard BFS Loop
    while (!q.empty())
    {
        int u = q.front();
        q.pop();
 
        for (int v=0; v<V; v++)
        {
            if (visited[v]==false && rGraph[u][v] > 0)
            {
                q.push(v);
                parent[v] = u;
                visited[v] = true;
            }
        }
    }
      
    //end dfs
    //
int fordFulkerson(int graph[6][6], int s, int t)
{
    int u, v;
    int rGraph[6][6];  
    for (u = 0; u < 6; u++)
    {
        for (v = 0; v < 6; v++)
        {
            rGraph[u][v] = graph[u][v];
        }
    }
    int parent[6];
    int max_flow = 0;
    while (bfs(rGraph, s, t, parent))
    {
        int path_flow = INT_MAX;
        for (v = t; v != s; v = parent[v])
        {
            u = parent[v];
            path_flow = min(path_flow, rGraph[u][v]);
        }
        for (v = t; v != s; v = parent[v])
        {
            u = parent[v];
            rGraph[u][v] -= path_flow;
            rGraph[v][u] += path_flow;
        }
        max_flow += path_flow;
    }
    return max_flow;
}
#endif

#if 0
//bpGraph[M][N]
bool bpm(bool **bpGraph,int M, int N, int u, bool seen[], int matchR[])
{
    for (int v = 0; v < N; v++)    {
        if (bpGraph[u][v] && !seen[v])        {
            seen[v] = true; // Mark v as visited
            if (matchR[v] < 0 || bpm(bpGraph,M,N, matchR[v], seen, matchR)){
                matchR[v] = u;
                return true;
            }
        }
    }
    return false;
}
 
// Returns maximum number of matching from M to N
int maxBPM(bool bpGraph[M][N])
{

    int matchR[N];
 
    memset(matchR, -1, sizeof(matchR));
 
    int result = 0; 
    for (int u = 0; u < M; u++)
    {

        bool seen[N];
        memset(seen, 0, sizeof(seen));
 

        if (bpm(bpGraph, u, seen, matchR))
            result++;
    }
    return result;
}
#endif

   

