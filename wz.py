# -*- coding: UTF-8 -*-  
  
from aip import AipOcr  
import json  
import win32api,win32con
from tkinter import *

import os

# 定义常量  
APP_ID = '14744250'  
API_KEY = 's6ePT98xVEjfvm605SqsxB20'  
SECRET_KEY = 'uuRRRsA8NYbZR2DNRhnPTTEw5dbLQUmf'  
  
# 初始化AipFace对象  
aipOcr = AipOcr(APP_ID, API_KEY, SECRET_KEY)  
  
# 读取图片  
#filePath = "3.jpg"
rootdir=os.getcwd()
list=os.listdir(rootdir)
#print(list)

def get_file_content(filePath):  
    with open(filePath, 'rb') as fp:  
        return fp.read()  
  
# 定义参数变量  
options = {  
  'detect_direction': 'true',  
  'language_type': 'CHN_ENG',  
}  
s=''  
# 调用通用文字识别接口  
for i in range(0,len(list)):
    filename=list[i].find('.')
    
    filePath=os.path.join(rootdir,list[i])
    #print(os.path.splitext(filePath)[1])
    if os.path.splitext(filePath)[1]!='.jpg':
        continue
    result = aipOcr.basicGeneral(get_file_content(filePath), options)
    wid=10
    k=3
    print(result)
    for n in result['words_result']:
        print(n['words'])
        s+=n['words']
        s+='\n'
        k+=1
        if(len(n['words'])>wid):
           wid=len(n['words'])
    
    #win32api.MessageBox(0,s,"读到的文本内容：",win32con.MB_OK)
    root=Tk()
    root.title(list[i])
    #root.geometry('400x400')
    xls_text=StringVar()
    #l1=Entry(root,textvariable=xls_text,width='50')

    xls_text.set(s)
    #l1.pack(side='left')
    l2=Text(root,width=wid*2,height=k)
    l2.insert('end',s)
    l2.pack()
    tetfiel=list[i][:filename]+'.txt'
    
    val=open(tetfiel,'w')
    val.write(s)
    val.close()
    s=''

    root.mainloop()
#os.system('pause')
