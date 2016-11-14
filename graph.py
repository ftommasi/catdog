class Vertex:
  def __init__(self):
    self._edges = [] 
class Edge:
  
  def __init__(self,u=None,v=None,c=1):
    self._u = u
    self._v = v
    self._capacity = c

  def getU():
    return self._u
  
  def getV():
    return self._v

  def getCap():
    return self._capacity

  def setU(u):
    self._u = u
  
  def setV(v):
    self._v = v

  def setCap(c):
    self._capacity=c

if __name__ == "__main__":
  v = Vertex()
  e = Edge()


