from graph import *


source = Vertex("S");
sink = Vertex("T");
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
  voters =(list(),list())
  capacity = (list(),list())
  flow = (list(),list())
  tree = [] #adjacency list where tree[i] indicates the edge between i and tree[i]
  
  #initialize flow and cap to zero
  for n in xrange(int(v)): 
    flow[0].append(0)
    capacity[0].append(0)
  for n in xrange(int(v)):
    flow[1].append(0)
    capacity[1].append(0)
  
  for j in xrange(int(v)):
    stay,leave= raw_input().split()
    #print "stay",stay,"leave",leave
    v = Vertex()
    v.setChoice(stay + " " + leave)
    if stay[0] == "C":
      catlovers.append(v)
      #createEdge(source,v)
    elif stay[0] == "D":
      doglovers.append(v)
      #createEdge(v,sink)
    print "--------------------" 
    print "cats"
    for n in catlovers:
      print str(n)
    print "dogs"
    for n in doglovers:
     print str(n)
    print "--------------------" 
    
    
    for cat in catlovers:
      for dog in doglovers:
        print "C:",cat.choice
        print "D:",dog.choice
        if cat.choice.split()[0] == dog.choice.split()[1] or at.choice.split()[1] == dog.choice.split()[0]:
          voters[0].append(cat)
          voters[1].append(dog)
    print len(voters[0]),len(voters[1])
    for vote in voters:
      pass
     

'''
  #--------------------
  print "cats"
  for n in voters[0]:
    print str(n)
  print "dogs"
  for n in voters[1]:
    print str(n)
  #
  
  findConflicts(catlovers,doglovers)
  #print [str(n) for n in source.viewNeighbors()]
  for n in source.viewNeighbors():
    print str(n)
#  print [str(n) for n in sink.viewNeighbors()]
  for n in sink.viewNeighbors():
    print str(n)'''
