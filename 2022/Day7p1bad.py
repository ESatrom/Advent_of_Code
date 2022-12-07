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



dirs={"/":{}}
activePath=[]
for line in [line.split() for line in inp.split('\n')]:
    if line[0]=='$':
        if line[1]=='cd':
            if line[2]=='..':
                activePath=activePath[:-1]
            else:
                activePath+=[line[2]]
        elif line[1]=='ls':
            pass
    else: # Must be part of an ls
        script="dirs"+''.join('["'+path+'"]'for path in activePath)+'["'+line[1]+'"]'
        if line[0]=='dir':
            try: #A potentially redundant test to ensure I don't overwrite existing directories
                exec(script+"==1")
            except:
                print("dir doesn't exist: "+script)
                exec(script+"={}")
        else: # Must be a file
            exec(script+"="+line[0])

# print('\n'.join(dir for dir in dirs.values()))
print(convertToSizes(dirs))