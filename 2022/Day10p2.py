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



class Reader:
    X=1
    cycle=0
    CRT=[]
    
    def addx(self, amt):
        self.cycle+=1
        self.test()
        self.cycle+=1
        self.test()
        self.X+=amt

    def noop(self):
        self.cycle+=1
        self.test()

    def test(self):
        print(str(self.cycle)+": "+str(self.X)+" - "+str(self.CRT))
        self.CRT+=[self.X]



    def run(self):
        for line in inp.split("\n"):
            if line.split()[0]=='addx':
                self.addx(int(line.split()[1]))
            else:
                self.noop()
        print('\n'.join([''.join(['#' if abs(self.CRT[(y*40)+x]-x)<2 else ' ' for x in range(40)])for y in range(6)]))

Reader().run()