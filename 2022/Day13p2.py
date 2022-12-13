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



# def recSum(ls):
#     return sum(i if isinstance(i, int) else recSum(i) for i in ls)

def test(ls1, ls2):
    try:
        for i in range(len(ls1)):
            if isinstance(ls1[i], list) or isinstance(ls2[i], list):
                X=test(ls1[i] if isinstance(ls1[i], list) else [ls1[i]], ls2[i] if isinstance(ls2[i], list) else [ls2[i]])
                if X==0:continue
                else:return X
            else:
                if ls1[i]==ls2[i]:continue
                elif ls1[i]<ls2[i]:return 1
                else: return -1
        return len(ls2)>len(ls1)
    except:
        return -1



packets=list(filter(lambda x: x!="", inp.split("\n")))+["[[2]]","[[6]]"]
for j in range(len(packets)):

    for i in range(len(packets)-1):
        happy=False
        exec("""if test("""+packets[i]+""", """+packets[i+1]+""")>0:
                    happy=True""")
        if not happy:
            packets=packets[:i]+[packets[i+1]]+[packets[i]]+packets[i+2:]
print((1+packets.index('[[2]]'))*(1+packets.index('[[6]]')))