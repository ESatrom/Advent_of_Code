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
    print(str(ls1)+"\t"+str(ls2))
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



F=inp.split("\n")[::3]
S=inp.split("\n")[1:][::3]
res=0
for i in range(len(F)):
    temp=res
    exec("""if test("""+F[i]+""", """+S[i]+""")>0:
                res+=i+1""")
    if res-temp>0:
        print(i)
    # if(i==1):break
print(res)