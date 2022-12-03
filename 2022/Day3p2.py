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



prio=0
score=" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
inp=inp.split('\n')
for elves in [inp[3*i:(3*i)+3]for i in range(len(inp)//3)]:
    for item in elves[0]:
        if item in elves[1] and item in elves[2]:
            prio+=score.index(item)
            break
print(prio)