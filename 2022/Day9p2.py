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


kX=[0 for knot in 10*'*']
kY=[0 for knot in 10*'*']
tailPoses=set()
tailPoses.add("0 0")
for instruction in inp.split('\n'):
    for i in range(int(instruction.split()[1])):
        if instruction.split()[0]=='R':
            kX[0]+=1
        elif instruction.split()[0]=='L':
            kX[0]-=1
        elif instruction.split()[0]=='U':
            kY[0]+=1
        else:
            kY[0]-=1
        for knotID in range(1,10):
            if ((kX[knotID-1]-kX[knotID])**2+(kY[knotID-1]-kY[knotID])**2)**.5>=2:
                if kX[knotID-1]>kX[knotID]:
                    kX[knotID]+=1
                elif kX[knotID-1]<kX[knotID]:
                    kX[knotID]-=1
                if kY[knotID-1]>kY[knotID]:
                    kY[knotID]+=1
                elif kY[knotID-1]<kY[knotID]:
                    kY[knotID]-=1
        tailPoses.add(str(kX[9])+" "+str(kY[9]))
print(len(tailPoses))