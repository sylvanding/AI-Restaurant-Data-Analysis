# -*- coding: utf-8 -*-
"""
Created on Fri May 13 21:19:22 2022

@author: LuoHuaQin
E-mail: 2226369682@qq.com
"""

from lxml import etree
import requests
from requests import RequestException
import pandas as pd

base_url1 = "https://www.boohee.com/food/search?keyword="
base_url2 = "https://www.boohee.com"

nutrition_columns = ["卡路里(大卡)","碳水化合物(克)","脂肪(克)","蛋白质(克)","维生素(克)",'份量']

nutrition_content = []

def crawler(menu,base_url1,base_url2):
   #构造请求头
   headers = {"User-Agent": "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Mobile Safari/537.36"}
   #构造search_urls
   search_urls = [base_url1+iterm for iterm in menu]

   #遍历请求访问网址
   for i in range(len(search_urls)):
      try:
         res = requests.get(search_urls[i],headers = headers)
         if res.status_code == 200:
            res.encoding = 'utf-8'   #以utf-8编码
         else:
            raise RequestException
         res.encoding = 'utf-8'
         html = etree.HTML(res.text)   #生成lxml对象
         temp = html.xpath('//*[@id="main"]/div/div[1]/ul/li[1]/div[2]/h4/a/@href')[0]
         target_url = base_url2 + temp #得到第一个菜品的营养信息页面的url
         print(target_url)
         res = requests.get(target_url,headers = headers)
         if res.status_code == 200:
            res.encoding = 'utf-8'   #以utf-8编码
         else:
            raise RequestException
         html = etree.HTML(res.text)   #生成lxml对象
         target = html.xpath('//span[contains(text(),"热量(大卡)")]/following::span[@class="dd" or @class="stress red1"]/text()')[0:5]
         print(num)
         nutrition_content.append(target)
      except RequestException:
         print("访问失败:"+url)
if __name__ == '__main__':
   file = pd.read_excel('菜品信息表.xls')
   menu = file['菜名'].tolist()
   crawler(menu,base_url1,base_url2)

   temp = pd.DataFrame(columns = nutrition_columns,data = nutrition_content,index=None)

   new_file = pd.concat([file,temp],axis = 1)
   new_file.to_excel('营养信息表.xls')