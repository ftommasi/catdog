public class Vertex{
  private List<Edge> edges; 
  public  List<Edge> getEdges(){
    return edges;
  }

  public  void  setEdges(List<Edge> e){
    edges = e;
  }

  public void addEdge(Edge e){
    edges.add(e);
  }
}


public class Edge{
  private Vertex u;
  private Vertex v;
  private int capacity;
}
