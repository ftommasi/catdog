#include <vector>
#include <string>
#include "vertex.cpp"
using namespace std;

#define EDGE_H

class Vertex;

class Edge{
  private:
    Vertex u;
    Vertex v;
    int capacity;
  public:
    Edge(){
      
    }
    Edge(Vertex nu, Vertex nv, int nc){
      u = nu;
      v = nv;
      c = nc;
    }
    
    int getCapacity(){
      return capacity;
    }

    void  setCapacity(int cap){
      capacity = cap;
    }

    void setU(Vertex newU){
      u = newU;
    }
    
    Vertex getU(){
      return u; 
    }
    
    void setV(Vertex newV){
       v = newV;
    }
    
    Vertex getV(){
      return v; 
    }

    string print(){
      return "---"+string(capacity)+"--->";
    }
};

   

  
/*
if __name__ == "__main__":
  v = Vertex("V")
  e = Edge()
  #v.addEdge(e)
  v.getEdges()
  v2 = Vertex("V1")
  e2 = Edge(v,v2,1,)
#VERTEX
  v.addEdge(e2)
  v2.addEdge(e2)
  v.getEdges()
  v2.getEdges()
  v.viewNeighbors()
  v.getChoice()
  v.setChoice("NEW V")
  print v.__str__()
#END VERTEX

#EDGE
  e.getU()
  e.getV()
  e.getCap()
  e.setU(v)
  e.setV(v2)
  e.setCap(10)
  #print e2.__str__()
#ENDEDGE
*/
