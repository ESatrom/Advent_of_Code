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


mem={}
def dirSize(dir, dirs, mem):
    try:
        return mem[dir]
    except:
        mem[dir]=sum(fileOrDir if isinstance(fileOrDir, int) else dirSize(fileOrDir, dirs, mem) for fileOrDir in dirs[dir].values())
        return mem[dir]
dirs={"/":{}}
activePath=""
for line in [line.split() for line in inp.split('\n')]:
    if line[0]=='$':
        if line[1]=='cd':
            if line[2]=='..':
                activePath='/'+'/'.join(activePath.split('/')[1:-1])
            else:
                if line[2][0]=='/':
                    activePath+=line[2]
                else:
                    activePath+='/'+line[2]
        elif line[1]=='ls':
            pass
    else: # Must be part of an ls
        if line[0]=='dir':
            if  not activePath+'/'+line[1]in dirs: # Ensure not overwriting a dir
                dirs[activePath+'/'+line[1]]={}
            dirs[activePath][line[1]]=activePath+'/'+line[1]
        else: # Must be a file
            dirs[activePath][line[1]]=int(line[0])

# print('\n'.join(dir for dir in dirs.values()))
dirSizes=[dirSize(dir, dirs, mem) for dir in dirs]
size=70000000-max(dirSizes)
needed=30000000-size
print(sorted(list(filter(lambda x: x > needed, dirSizes)))[0])