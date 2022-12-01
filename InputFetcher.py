import requests
from datetime import date
import os

def run(TODAY):
    COOKIE='***REMOVED***'
    YEAR=TODAY.year
    DAY=TODAY.day

    if DAY>25 or TODAY.month!=12:
        print("Failed to fetch input: It's not a day of advent!")
        return
    YEAR=str(YEAR)
    DAY=str(DAY)
    try:
        file = open(os.path.dirname(os.path.abspath(__file__))+"\\"+YEAR+"\\inputs\\"+DAY+".txt", "x")
        file.write(requests.get('https://adventofcode.com/'+YEAR+'/day/'+DAY+'/input', headers={'cookie':'session='+COOKIE}).text[:-1])
        file.close()
    except Exception as e:
        print("Failed to write new input file. Are you perhaps trying to pull input for a day you've already completed?")
        print(e)
def run_today():run(date.today())