#include <vector>
#include <string>
#include "edge.cpp"
using namespace std;

#define VERTEX_H

class Edge;

class Vertex{
  private: 
    vector<Edge> edges;
    string choice;
  public:
    Vertex(){
      choice = "";
    }
    
    Vertex(string c){
      choice = c;
    }


    vector<Edge> getEdges(){
     return edges;
    }

  
    void  setEdges(List<Edge> e){
      edges = e;
    }

  

    vector<Edge> getEdges(){
      return edges;
    }
    
    void addEdge(Edge e){
      edges.push_back(e);
    }
    
    void  setChoice(string c){
      choice = c;
    }


    string getChoice(){
      return choice;
    }
    
    
    
    vector<Vertex> viewNeighbors(){
      vector<Vertex> neighbors();
      for(int i=0; i<edges.size();i++){
        if(edges[i].getU() != this){
          neighbors.push_back(edges[i].getU());
        }
        else if(edges[i].getV() != this){
          neighbors.push_back(edges[i].getV());
        }
      }
      return neighbors;
    }

    string print(){
      string subtree = choice;
      for(int i=0; i<edges.size();i++){
        if(edges[i].getU() != this){
          subtree += edges[i].print();
          subtree += edges[i].getU().getChoice();
        }
        else if(edges[i].getV() != this){
          subtree += edges[i].print();
          subtree += edges[i].getV().getChoice();
        }
      }
      return subtree;
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
