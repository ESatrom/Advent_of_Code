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


hX=0
hY=0
tX=0
tY=0
tailPoses=set()
tailPoses.add("0 0")
for instruction in inp.split('\n'):
    for i in range(int(instruction.split()[1])):
        if instruction.split()[0]=='R':
            hX+=1
        elif instruction.split()[0]=='L':
            hX-=1
        elif instruction.split()[0]=='U':
            hY+=1
        else:
            hY-=1
        if ((hX-tX)**2+(hY-tY)**2)**.5>=2:
            if hX>tX:
                tX+=1
            elif hX<tX:
                tX-=1
            if hY>tY:
                tY+=1
            elif hY<tY:
                tY-=1
        tailPoses.add(str(tX)+" "+str(tY))
print(len(tailPoses))