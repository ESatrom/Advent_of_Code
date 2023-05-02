# Reading Input to inp
import os
from datetime import date
import sys
fileName=os.path.abspath(__file__).split('\\')
sys.path.append('\\'.join(fileName[:-2]))
import InputFetcher
inp_file_name='\\'.join(fileName[:-1]+["inputs",fileName[-1][3:-5]+".txt"])
if not os.path.isfile(inp_file_name):
    InputFetcher.run(date(day=int(fileName[-1][3:-5]), month=12, year=int(fileName[-2])))
inp_file=open(inp_file_name)
inp=inp_file.read()
inp_file.close()
#End input reading



def dist(pointA, pointB):return abs(pointA[0]-pointB[0])+abs(pointA[1]-pointB[1])

grid={}
for i in range(0,4000000+1):
    grid[i]=[0,4000000]
sensors = []
for line in inp.split("\n"):
    Xs=line.split("x=") #["Sensor at ", "2, y=18: closest beacon is at ", "-2, y=15"]
    Ys=[segment.split(", y=") for segment in Xs] #[["Sensor at "], ["2", "18: closest beacon is at "], ["-2", "15"]]
    points=[[Ys[1][0],Ys[1][1].split(":")[0]],Ys[2]]
    sensor=[int(i) for i in points[0]]
    beacon=[int(i) for i in points[1]]
    distance=dist(sensor,beacon)
    sensors+=[[sensor[0],sensor[1],distance]]

print(list(filter(lambda x: not x[1] in grid[i], [[i,j] for i in range(0,4000000+1) for j in range(1,4000000+1)])))