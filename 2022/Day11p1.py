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



monkeys={}
monkey=""
for line in inp.split('\n'):
    if len(line)>0 and line[0]!=' ':
        monkey = line[:-1].lower()
        monkeys[monkey]={"inspections":0}
    elif "Starting items" in line:
        monkeys[monkey]["items"]=[int(i) for i in line.split(": ")[1].split(", ")]
    elif "Operation" in line:
        monkeys[monkey]["operation"] = line.split("new = ")[1]
    elif "Test" in line:
        monkeys[monkey]["divisor"]=int(line.split("divisible by ")[1])
    elif "If true" in line:
        monkeys[monkey][True]=line.split("throw to ")[1].lower()
    elif "If false" in line:
        monkeys[monkey][False]=line.split("throw to ")[1].lower()



for round in range(20):
    for turn in monkeys.keys():
        # print("\n"+"\n".join(monkey+": "+str(monkeys[monkey]["items"]) for monkey in monkeys.keys()))
        for item in monkeys[turn]["items"]:
            monkeys[turn]["inspections"]+=1
            x=lambda old:eval(monkeys[turn]["operation"])
            temp=x(item)
            temp//=3
            # print(temp)
            monkeys[monkeys[turn][temp%monkeys[turn]["divisor"]==0]]["items"]+=[temp]
        monkeys[turn]["items"]=[]
monk=sorted([monkey["inspections"] for monkey in monkeys.values()], reverse=True)[:2]
print(monk[0]*monk[1])