from graph import *

def findConflicts(c,d):
  for v1 in c:
    for v2 in d:
      if v1.choice == v2.choice:
        createEdge(v1,v2)

#parse input
entries = input()
for i in xrange(entries):
  c,d,v = raw_input().split()
  catlovers = []
  doglovers =[]
  for j in xrange(int(v)):
    stay,leave= raw_input().split()
    #print "stay",stay,"leave",leave
    if stay[0] == "C":
      v = Vertex()
      catlovers.append(v)
    elif stay[0] == "D":
      doglovers.append(v)
  findConflicts(catlovers,doglovers)
