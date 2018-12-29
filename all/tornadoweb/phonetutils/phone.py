import   re

def  checkPCorMobile(info):
     phoneheads=["android","mac","windowphone"];

     for  data  in  phoneheads:
         #print(data)
         #info头里面包含data
         val=re.search(data,info.lower())
        # print(val)
         if  val is None:
             return "PC"
         else:
             return "Mobile"


