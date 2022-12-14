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



def grain(grid, xDim, yDim):
    gX=500-xDim[0]
    gY=yDim[0]
    while gY<yDim[1]:
        try:
            if grid[gY+1][gX]=='.':
                gY+=1
            elif grid[gY+1][gX-1]=='.':
                gY+=1
                gX-=1
            elif grid[gY+1][gX+1]=='.':
                gY+=1
                gX+=1
            else:
                grid[gY][gX]='o'
                return True
        except:
            return False
    return False

def draw(line, grid, xDim, yDim):
    points = [[int(i) for i in point.split(',')] for point in line.split(" -> ")]
    points = [[point[0]-xDim[0],point[1]-yDim[0]] for point in points]
    for i in range(len(points)-1):
        if points[i][0]==points[i+1][0]:
            x=points[i][0]
            for y in range(min(points[i][1],points[i+1][1]),max(points[i][1],points[i+1][1])+1):
                grid[y][x]='#'
        else:
            y=points[i][1]
            for x in range(min(points[i][0],points[i+1][0]),max(points[i][0],points[i+1][0])+1):
                grid[y][x]='#'



xDim=[463,520]
yDim=[0,177]
grid=[['.'for x in range(xDim[0],xDim[1])]for y in range(yDim[0],yDim[1])]
for line in inp.split('\n'):
    draw(line, grid, xDim, yDim)

sand=0
while grain(grid, xDim, yDim):
    sand+=1
    
print('\n'.join(''.join(line)for line in grid))
print(sand)