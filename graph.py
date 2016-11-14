#! /usr/bin/env python
class Vertex:
  def __init__(self,choice = ""):
    self._edges = []
    self._choice = choice
  def addEdge(self,e):
    self._edges.append(e)
  
  def getEdges(self):
    return self._edges
  
  def viewNeighbors(self):
    neigh = []
    for edge in self._edges:
      if not edge.getU() is not self:
        if edge.getU() not in neigh:
          neigh.append(edge.getU())
      
      elif edge.getV() is not self:
        if edge.getV() not in neigh:
          neigh.append(edge.getV())
    
    return neigh

  def getChoice(self):
    return self._choice
  
  def setChoice(self,c):
    self._choice = c

  def __str__(self):
    result = self._choice
    for edge in self._edges:
      if edge.getU() is not self and edge.getU() is not None :
        result += str(edge)
        result += str(edge.getU().getChoice())+"\n"
      
      elif edge.getV() is not self and edge.getU() is not None:
        result += str(edge)
        result += str(edge.getV().getChoice())+"\n"
    

    return result

  #def __repr__(self):
   # return

class Edge:
  
  def __init__(self,u=None,v=None,c=1):
    self._u = u
    self._v = v
    self._capacity = c

  def getU(self):
    return self._u
  
  def getV(self):
    return self._v

  def getCap(self):
    return self._capacity

  def setU(self,u):
    self._u = u
  
  def setV(self,v):
    self._v = v

  def setCap(self,c):
    self._capacity=c

  def __str__(self):
    return "--%s-->"%(self._capacity)

if __name__ == "__main__":
  v = Vertex("V")
  e = Edge()
  v.addEdge(e)
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
  print e2.__str__()
#ENDEDGE
