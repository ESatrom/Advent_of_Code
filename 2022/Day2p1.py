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


elf="ABC"
me="XYZ"
points = 0
for round in inp.split('\n'):
    points+=me.index(round.split()[1])+1 #Points based on what I throw
    points+=0 if ((me.index(round.split()[1])+1)%3)==elf.index(round.split()[0]) else 3 if me.index(round.split()[1])==elf.index(round.split()[0]) else 6 #0 loss, 3 tie, 6 win
    pass
print(points)