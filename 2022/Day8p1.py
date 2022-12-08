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



grid=[[int(tree) for tree in line] for line in inp.split('\n')]
nView=[[-1 for tree in line] for line in grid]
sView=[[-1 for tree in line] for line in grid]
eView=[[-1 for tree in line] for line in grid]
wView=[[-1 for tree in line] for line in grid]
for y in range(len(grid)):
    for x in range(len(grid[y])):
        nView[y][x]=max(round(nView[y-1][x]),grid[y][x]-.1)
        sView[len(sView)-(y+1)][x]=max(round(sView[(len(sView)-y) if y>0 else 0][x]),grid[len(sView)-(y+1)][x]-.1)
        wView[y][x]=max(round(wView[y][x-1]),grid[y][x]-.1)
        eView[y][len(eView[y])-(x+1)]=max(round(eView[y][(len(eView[y])-x) if x>0 else 0]),grid[y][len(eView[y])-(x+1)]-.1)
print(sum(sum(grid[y][x]>min(nView[y][x], sView[y][x], wView[y][x], eView[y][x])for x in range(len(grid[y])))for y in range(len(grid))))