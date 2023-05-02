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



def monk0(ls:list):
    if not 17 in ls:
        ls+=[17]
def monk1(ls:list):pass
def monk2(ls:list):
    ls=[i+7 for i in ls]
    fix(ls)
def monk3(ls:list):
    ls=[i+4 for i in ls]
    fix(ls)
def monk4(ls:list):
    ls=[i+5 for i in ls]
    fix(ls)
def monk5(ls:list):
    ls=[i+6 for i in ls]
    fix(ls)
def monk6(ls:list):
    if not 13 in ls:
        ls+=[13]
def monk7(ls:list):
    ls=[i+2 for i in ls]
    fix(ls)

def fix(ls:list):
    temp=[]
    for i in ls:
        temp+=factors(i)
    ls=list(set(temp))
    return ls

def factors(i:int):return list(filter(lambda x: i%x==0, range(2,int(i**.5)+1)))

monkeys = [
    {"items":[],"operate":monk0,"divisor":2,True:2,False:6,"inspections":0},
    {"items":[],"operate":monk1,"divisor":7,True:0,False:2,"inspections":0},
    {"items":[],"operate":monk2,"divisor":13,True:7,False:6,"inspections":0},
    {"items":[],"operate":monk3,"divisor":5,True:4,False:5,"inspections":0},
    {"items":[],"operate":monk4,"divisor":3,True:1,False:5,"inspections":0},
    {"items":[],"operate":monk5,"divisor":19,True:1,False:0,"inspections":0},
    {"items":[],"operate":monk6,"divisor":11,True:3,False:7,"inspections":0},
    {"items":[],"operate":monk7,"divisor":17,True:4,False:3,"inspections":0}
]
starting_items = """85, 79, 63, 72
53, 94, 65, 81, 93, 73, 57, 92
62, 63
57, 92, 56
67
85, 56, 66, 72, 57, 99
86, 65, 98, 97, 69
87, 68, 92, 66, 91, 50, 68""".split('\n')
for i in range(8):monkeys[i]["items"]=[fix([int(i)]) for i in starting_items[i].split(', ')]



for round in range(500):
    if round%10==0:print("\n"+"\n".join(str(monkey)+": "+str(monkeys[monkey]["items"]) for monkey in range(8)))
    for monkey in monkeys:
        for item in monkey["items"]:
            monkey["inspections"]+=1
            temp=monkey["operate"](item)
            monkeys[monkey[monkey["divisor"] in temp]]["items"]+=[temp]
        monkey["items"]=[]
monk=sorted([monkey["inspections"] for monkey in monkeys.values()], reverse=True)[:2]
print(monk[0]*monk[1])