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

def getScore(grid, y, x):
    height=grid[y][x]
    ty=y+1
    tx=x
    try:
        while grid[ty][tx]<height:
            ty+=1
    except:ty-=1
    score=ty-y
    ty=y-1
    tx=x
    try:
        while grid[ty][tx]<height and ty>0:
            ty-=1
    except:ty+=1
    score*=y-ty
    ty=y
    tx=x+1
    try:
        while grid[ty][tx]<height:
            tx+=1
    except:tx-=1
    score*=tx-x
    ty=y
    tx=x-1
    try:
        while grid[ty][tx]<height and tx>0:
            tx-=1
    except:tx+=1
    score*=x-tx
    return score

grid=[[int(tree) for tree in line] for line in inp.split('\n')]
score=0
for y in range(1,len(grid)-1):
    for x in range(1,len(grid[y])-1):
        # print(str(y)+" "+str(x))
        score=max(score,getScore(grid, y, x))
print(score)