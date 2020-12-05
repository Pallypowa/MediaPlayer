import json

class Machine:
    def __init__(self,id,time):
        self.id=id
        self.time=time

confName=input("A gyártósor konfiguráció neve: ")
machines=[]
id=input("Gép azonosító: ")
while id:
    time=input("Szükséges idő ")
    machines.append(Machine(id,time))
    id=input("Gép azonosító: ")
f=open(confName+".json","w")
json_string=json.dumps([m.__dict__ for m in machines])
f.write(json_string)