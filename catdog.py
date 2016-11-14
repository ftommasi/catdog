from graph import *


source = Vertex();
sink = Vertex();
def findConflicts(c,d):
  for v1 in c:
    for v2 in d:
      if v1.getChoice() == v2.getChoice():
        createEdge(v1,v2)

def createEdge(v1, v2):
  e = Edge(v1,v2,1)
  v1.addEdge(e)
  v2.addEdge(e)

#parse input
entries = input()
for i in xrange(entries):
  c,d,v = raw_input().split()
  catlovers = []
  doglovers =[]
  for j in xrange(int(v)):
    stay,leave= raw_input().split()
    #print "stay",stay,"leave",leave
    v = Vertex()
    v.setChoice(stay + " " + leave)
    if stay[0] == "C":
      catlovers.append(v)
      createEdge(source,v)
    elif stay[0] == "D":
      doglovers.append(v)
      createEdge(v,sink)
  findConflicts(catlovers,doglovers)
  print n for n in source.getNeighbors()
  print n for n in sink.getNeighbors()
