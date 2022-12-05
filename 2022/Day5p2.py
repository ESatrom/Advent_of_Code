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



crates=[]
go=0
for line in inp.split('\n'):
    if '[' in line:
        temp=line[1:][::4]
        for i in range(len(temp)):
            if temp[i]==' ':continue
            try:
                crates[i]+=[temp[i]]
            except:
                while len(crates)<i+1:crates+=[[]]
                crates[i]=[temp[i]]
    elif go:
        command=line.split()
        amt=int(command[1])
        src=int(command[3])
        trg=int(command[5])
        crates[trg]+=crates[src][-amt:]
        crates[src]=crates[src][:-amt]
    elif line=='':
        go=1
        crates=[None]+[stack[::-1]for stack in crates]
        print(crates)
print(''.join(stack[-1] for stack in crates[1:]))