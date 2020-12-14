import json

class sample.Product:
    def __init__(self,name, id,needs):
        self.name=name
        self.id=id
        self.needs=needs


print("Új termék létrehozása")
name=input("Adja meg a termék nevét: ")
id=input("Adja meg a termék azonosítóját: ")
print("Adja meg a szükséges gépeket!")
needs=[]
s=input()
while s:
    needs.append(s)
    s=input()
f=open(id+".json","w")
f.write(json.dumps(sample.Product(name,id,needs).__dict__))