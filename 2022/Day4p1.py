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



C=0
for line in inp.split("\n"):
    Elves=[[int(num)for num in elf.split('-')]for elf in line.split(",")]
    range0=range(Elves[0][0],Elves[0][1]+1)
    range1=range(Elves[1][0],Elves[1][1]+1)
    if ((Elves[0][0] in range1) and (Elves[0][1] in range1)) or ((Elves[1][0] in range0) and (Elves[1][1] in range0)):C+=1
print(C)