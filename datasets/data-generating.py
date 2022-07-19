# -*- coding: utf-8 -*-
"""
Created on Sun Jul 10 16:32:30 2022

@author: LuoHuaQin
E-mail: 2226369682@qq.com
"""

import pandas as pd
import numpy as np
from datetime import datetime
from faker import Faker
fake = Faker(locale='zh_CN')
from collections import OrderedDict
import random

def crteateOrder(datetime,temp):
   num = fake.random_int(min=300,max=500)
   users = user_id.copy()
   for i in range(num):
      while 1:
         t = fake.random_int(min=0,max=999999)
         if t not in order_id:
            order_id.append(t)
            break
      user = random.choice(users)
      users.remove(user)
      order_user_id.append(user)

      while 1:
         t = fake.random_int(min=0,max=999999)
         if t not in order_menu_id:
            order_menu_id.append(t)
            break
      date_time.append(datetime+" "+fake.random_element(temp))
      #随机生成点菜数量
      food_num = fake.random_int(min=2,max=4)
      total = 0.0
      for f in range(food_num):
         menu_id.append(order_menu_id[-1])
         food_id.append(fake.random_element(menu["菜品id"]))
         mark.append(fake.random_int(min=3,max=10))
         total+=sum(menu[menu.菜品id==food_id[-1]]["价格"])
      total_money.append(total)

gender_dict = OrderedDict([(0, 0.48), (1, 0.52)])#0为女，1为男

user_id = []
gender = []
age = []

num = 500

for i in range(num):
   while 1:
      t = fake.random_int(min=0,max=999999)
      if t not in user_id:
         user_id.append(t)
         break
   age.append(fake.random_int(min=16,max=25))
   gender.append(fake.random_element(gender_dict))

#点餐表menu
menu_id =[]
food_id = []
mark = []
#生成一个月订单的信息
order_id = []
order_user_id = []
order_menu_id = []
total_money = []
date_time = []


menu = pd.read_excel('营养信息表（新）.xls')
morning = pd.date_range(start =datetime.strptime('06:00:00','%H:%M:%S'),periods=240,freq='T').strftime("%H:%M:%S")
noon = pd.date_range(start =datetime.strptime('10:00:00','%H:%M:%S'),periods=240,freq='T').strftime("%H:%M:%S")
evening = pd.date_range(start =datetime.strptime('16:00:00','%H:%M:%S'),periods=300,freq='T').strftime("%H:%M:%S")

times= pd.date_range(start =datetime.strptime('20220601','%Y%m%d'),periods=30,freq='D').strftime("%Y-%m-%d")
for time in times:
   crteateOrder(str(time),morning)
   crteateOrder(str(time),noon)
   crteateOrder(str(time),evening)

userData = {'user_id': user_id,'gender':gender,'age':age}
user = pd.DataFrame.from_dict(userData)

menuData = {'menu_id':menu_id,'food_id':food_id,'mark':mark}
Menu = pd.DataFrame.from_dict(menuData)

orderData = {'order_id':order_id,'user_id':order_user_id,'menu_id':order_menu_id,'total_money':total_money,'time':date_time}
orderform = pd.DataFrame.from_dict(orderData)
orderform.to_csv("orderform.csv",index=None)
Menu.to_csv("menu.csv",index=None)
user.to_csv("user.csv",index=None)