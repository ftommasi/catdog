class Vertex{
  private: 
    vector<Edge> edges; 
  public:
    vector<Edge> getEdges(){
     return edges;
    }

  void  setEdges(List<Edge> e){
    edges = e;
  }

  void addEdge(Edge e){
    edges.push_back(e);
  }
};


public class Edge{
  private:
    Vertex u;
    Vertex v;
    int capacity;
  public:
    int getCapacity(){
      return capacity;
    }
};
