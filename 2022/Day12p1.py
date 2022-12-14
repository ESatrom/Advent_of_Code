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


mem=[]
def test(coord, grid, end, mem):
    if coord == end:print("Yay")
    if coord in mem:return []
    val=grid[coord[0]][coord[1]]
    y=coord[0]
    x=coord[1]
    options=[]
    if grid[y][x+1]<=val+1:
        options+=test(grid[y][x+1])
    if grid[y][x-1]<=val+1:
        options+=test(grid[y][x+1])
    if grid[y+1][x]<=val+1:
        options+=test(grid[y][x+1])
    if grid[y-1][x]<=val+1:
        options+=test(grid[y][x+1])
    return options



lines=inp.split('ln')

Start=[]
End=[]
for y in range(len(lines)):
    if 'S' in lines[y]:
        Start=[y,lines[y].index('S')]
    if 'E' in lines[y]:
        End=[y,lines[y].index('E')]
print('\n'.join(lines))
grid = [[(0 if c=='S' else 25)if c in 'SE' else 'abcdefghijklmnopqrstuvwxyz'.index(c) for c in line]for line in lines]



test(Start, grid, End)

print(inp)