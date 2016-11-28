#include <iostream>
#include <string>
#include <vector>

using namespace std;

#define DUMP 0

bool findConflicts(string c, string d){
  string catstart = c.substr(0,2);
  string dogstart = d.substr(0,2);
  string catend = c.substr(3,2);
  string dogend = d.substr(3,2);
  //cerr << "in " << c << " " << d << endl << "checking if " << cats  
  if(catstart.compare(dogend) == 0 || catend.compare(dogstart) == 0)
    return true;

  return false;

}

bool bpm(vector<vector<bool> >&bpGraph, int u, bool seen[], int matchR[])
{
    for (int v = 0; v < bpGraph[0].size(); v++)
    {
        
        if (bpGraph[u][v] && !seen[v])
        {
            seen[v] = true;  
            
            if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR))
            {
                matchR[v] = u;
                return true;
            }
        }
    }
    return false;
}
 
int maxBPM(vector<vector<bool> >&bpGraph)
{
    int matchR[bpGraph[0].size()];
 
    for(int i=0; i<bpGraph[0].size(); i++){
      matchR[i] = -1;
    } 
    int result = 0; 
    for (int u = 0; u < bpGraph.size(); u++)
    {
        bool seen[bpGraph[0].size()];
 
        for(int i=0; i<bpGraph[0].size(); i++){
          seen[i] = 0;
        }
        if (bpm(bpGraph, u, seen, matchR))
            result++;
    }
    return result;
}

void initMatrix(vector<vector<bool> > &bpGraph,int rows, int cols){
  for(int i=0; i < rows; i ++){
      vector<bool> temp(cols,false);
      bpGraph.push_back(temp);
  }
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
    vector<vector<bool> > bip;

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
    initMatrix(bip,cats.size(),dogs.size());
    for(int i=0; i < cats.size(); i++){
      for(int j=0; j < dogs.size(); j++){
        if(findConflicts(cats[i],dogs[j])){
          bip[i][j] = true;    
        } 
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
    for(int i=0; i<bip[0].size();i++){
      cerr << dogs[dogcounter] << " |  ";
      dogcounter = (++dogcounter > dogs.size() ?dogs.size() : dogcounter);
      for(int j=0; j<bip.size();j++){
        cerr  <<  bip[j][i] << "  |  ";
      }
      cerr  << endl;
    }

      cerr << endl<< "------------------------------------------";
      cerr << endl;
#endif
      int result = v - maxBPM(bip);

#if DUMP
      cerr << endl<< "------------------------------------------" << endl;
      cerr << "RESULT IS: " << endl;
#endif
      cout << result << endl;
#if DUMP
      cerr << endl<< "------------------------------------------" << endl;
#endif
  }
}


