import math

from flask import Flask, jsonify, current_app, request
from authlib.jose import jwt, JoseError
import pymysql
import json
import configparser
import time
import datetime

config = configparser.ConfigParser()
config.read('./config.ini')


def generate_token(user, **kwargs):
    """生成用于邮箱验证的JWT（json web token）"""
    SECRET_KEY = 'kkkkk'
    # 签名算法

    header = {'alg': 'HS256'}
    # 设置过期时间 现在时间 + 有效时间    示例5分钟
    # expire = datetime.utcnow() + timedelta(minutes=5)
    data = {'name': user}
    # 生成token
    encoded_jwt = jwt.encode(header, payload=data, key=SECRET_KEY)
    return str(encoded_jwt, encoding="utf-8")


def sql_connect():
    conn = pymysql.connect(
        password='20010308',
        user='root',
        host='localhost',
        database='food_detect',
        charset='utf8',
    )
    return conn


app = Flask(__name__)


@app.after_request
def cors(environ):
    environ.headers['Access-Control-Allow-Origin'] = '*'
    environ.headers['Access-Control-Allow-Method'] = '*'
    environ.headers['Access-Control-Allow-Headers'] = 'x-requested-with,content-type'
    return environ


@app.route('/')
def hello_world():  # put application's code here
    return 'Hello World!'


@app.route('/login', methods=['POST'])
def login():
    data = request.data
    if len(data) == 0:
        return 'ok'
    else:
        data_json = json.loads(data)
        username = data_json['username']
        password = data_json['password']
    conn = sql_connect()
    cursor = conn.cursor()
    sql = 'select * from user where username="' + str(username) + '" and password="' + str(password) + '";'
    cursor.execute(sql)
    a = cursor.fetchone()
    try:
        if len(a) == 2:
            token = generate_token(username)
            res = {
                'code': '20000',
                'message': '成功取到用户信息',
                'token': token,
                # 'data': {
                #     'menu':[
                #         {
                #             'path':'/home',
                #             'name':'home',
                #             'label':'首页',
                #             'url':''
                #         }
                #     ]
                # }
            }
        else:
            res = {'code': '20001', 'message': '未获取到用户信息'}
    except TypeError:
        res = {'code': '20002', 'message': '用户名或密码错误'}
    return json.dumps(res)


@app.route('/home', methods=['POST', 'GET'])
def home():
    data = {}
    conn = sql_connect()
    cursor = conn.cursor()

    sql = "select id,DATE_FORMAT(time, '%H:%i:%S'),price,uid,state,machine from `order` order by time DESC limit 5;"  # 或许应该用时间排序
    cursor.execute(sql)
    a = cursor.fetchall()
    order_table = []
    # 要加0判断
    for i in a:
        order = {}
        order['id'] = i[0]
        order['time'] = i[1]
        order['price'] = i[2]
        order['uid'] = i[3]
        order['state'] = i[4]
        order['machine'] = i[5]
        order_table.append(order)

    data['order'] = order_table

    # sql = "select DATE_FORMAT(time, '%Y-%m-%d') Day,sum(price) as sum from  `order` where time BETWEEN '2022-05-13 00:08:25' AND  '2022-05-17 20:08:25' group by Day;"
    # cursor.execute(sql)
    # a = cursor.fetchall()
    # data['revenue'] = a

    now = datetime.datetime.now()
    p1 = now.strftime("%Y-%m-%d 0:0:0")
    p2 = now.strftime("%Y-%m-%d 23:59:59")
    sql = "select count(id) as order_num from `order` where time between '" + p1 + "' and '" + p2 + "';"
    cursor.execute(sql)
    a = cursor.fetchone()
    data['TodayNum'] = a[0]
    num = a[0]

    sql = "select sum(price)  as order_sum from `order` where time between '" + p1 + "' and '" + p2 + "';"
    cursor.execute(sql)
    a = cursor.fetchone()
    if a[0] == None:
        data['TodayMoney'] = 0
        data['AvrMoney'] = 0
    else:
        data['TodayMoney'] = a[0]
        data['AvrMoney'] = int(a[0] / num)

    pass5 = datetime.date.today() - datetime.timedelta(5)
    qian = pass5.strftime("%Y-%m-%d 00:00:00")
    hou = datetime.date.today().strftime("%Y-%m-%d 00:00:00")
    sql = "select DATE_FORMAT(time, '%m-%d') Day,sum(price) as sum from  `order` where time between '" + qian + "' and '" + hou + "' group by Day;"
    cursor.execute(sql)
    a = cursor.fetchall()
    date = []
    date.append((datetime.date.today() - datetime.timedelta(5)).strftime("%m-%d"))
    date.append((datetime.date.today() - datetime.timedelta(4)).strftime("%m-%d"))
    date.append((datetime.date.today() - datetime.timedelta(3)).strftime("%m-%d"))
    date.append((datetime.date.today() - datetime.timedelta(2)).strftime("%m-%d"))
    date.append((datetime.date.today() - datetime.timedelta(1)).strftime("%m-%d"))
    ki = {}
    money = []
    for i in date:
        flag = False
        for j in a:
            if j[0] == i:
                # ki[i] = j[1]
                money.append(j[1])
                flag = True
        if flag == False:
            # if i not in ki.keys():
            # ki[i] = 0
            money.append(0)
    five = {}
    five['date'] = date
    five['money'] = money
    data['Five'] = five

    sql = 'select food_id,count(food_id) as num from order_item group by food_id;'
    cursor.execute(sql)
    a = cursor.fetchall()
    a = list(a)
    aa = []
    ab = []
    name = []
    id = []
    cont = []
    for i in a:
        id.append(i[0])
        cont.append(i[1])
    for i in id:
        sql = "select name from food where id = " + str(i) + ";"
        cursor.execute(sql)
        a = cursor.fetchall()
        line = a[0]
        name.append(line[0])
    aaa = {}
    aaa['dishid'] = name
    aaa['cont'] = cont
    data['Dish'] = aaa
    cursor.close()
    conn.close()
    return json.dumps(data)


@app.route('/sec', methods=['GET'])
def sec():
    conn = sql_connect()
    cursor = conn.cursor()
    sql = "select id,DATE_FORMAT(time, '%Y-%m-%d %H:%i:%S'),price,uid,state,machine,picture,rate from `order` order by time DESC"
    cursor.execute(sql)
    a = cursor.fetchall()
    tab = []
    for i in a:
        dict = {}
        dict['id'] = i[0]
        dict['date'] = i[1]
        dict['price'] = i[2]
        dict['uid'] = i[3]
        dict['state'] = i[4]
        dict['machine'] = i[5]
        dict['picture'] = i[6]
        dict['rate'] = i[7]
        tab.append(dict)
    ll = len(a)
    ll = math.ceil(ll / 10) * 10
    return json.dumps([tab, ll])


@app.route('/more', methods=['GET', 'POST'])
def more():
    conn = sql_connect()
    cursor = conn.cursor()
    data = request.data
    data_json = json.loads(data)
    re_id = data_json['id']
    # data_json[]
    sql = "select id,DATE_FORMAT(time, '%Y-%m-%d %H:%i:%S'),price,uid,state,machine ,picture,rate from `order` where id=" + str(
        re_id) + " ;"
    cursor.execute(sql)
    a = cursor.fetchall()
    sql = "select food_id,amount from order_item where order_item.order_id=" + str(re_id) + " ;"
    cursor.execute(sql)
    b = cursor.fetchall()
    foo = []
    am = []
    for i in b:
        foo.append(i[0])
        am.append(i[1])
    foo = tuple(foo)
    sql = "select * from food where food.id in{}".format(foo)
    cursor.execute(sql)
    c = cursor.fetchall()
    tab1 = []
    order_item_tab = []
    food_tab = []
    for i in a:
        dict = {}
        dict['id'] = i[0]
        dict['time'] = i[1]
        dict['price'] = i[2]
        dict['uid'] = i[3]
        dict['state'] = i[4]
        dict['machine'] = i[5]
        dict['picture'] = i[6]
        dict['rate'] = i[7]
        tab1.append(dict)
    for i in b:
        dict = {}
        dict['food_id'] = i[0]
        dict['amount'] = i[1]
        order_item_tab.append(dict)
    for n, i in enumerate(c):
        dict = {}
        dict['id'] = i[0]
        dict['name'] = i[1]
        dict['price'] = i[2]
        dict['picture'] = i[3]
        dict['state'] = i[4]
        dict['category'] = i[5]
        dict['calorie'] = i[6]
        dict['carbohydrate'] = i[7]
        dict['fat'] = i[8]
        dict['protein'] = i[9]
        dict['vitamin'] = i[10]
        dict['amount'] = am[n]
        food_tab.append(dict)
    return json.dumps({'tab1': tab1, 'order_tab': order_item_tab, 'food_tab': food_tab})


@app.route('/rate', methods=['GET'])
def rate():
    conn = sql_connect()
    cursor = conn.cursor()
    sql = "select * from(select id,order_id,food_id,CAST(AVG(score)as SIGNED)as avg_score from `food_rate` GROUP BY food_id) as b order by b.avg_score"
    cursor.execute(sql)
    a = cursor.fetchall()
    id = []
    name = []
    score = []
    for i in a:
        line = i[2]
        # line=i[3]
        id.append(line)
    for i in a:
        line1 = i[3]
        score.append(line1)
    for i in id:
        sql = "select name from food where id = " + str(i) + ";"
        cursor.execute(sql)
        a = cursor.fetchall()
        line = a[0]
        name.append(line[0])

    res = {'dishid': name, 'cont': score}

    return json.dumps(res)


@app.route('/remark', methods=['GET'])
def remark():
    conn = sql_connect()
    cursor = conn.cursor()
    sql = "select id,uid,order_id,content,result from `remark` "
    cursor.execute(sql)
    a = cursor.fetchall()
    tab = []
    for i in a:
        dict = {}
        dict['id'] = i[0]
        dict['uid'] = i[1]
        dict['order_id'] = i[2]
        dict['content'] = i[3]
        dict['result'] = i[4]
        tab.append(dict)
    return json.dumps(tab)


@app.route('/one', methods=['GET', 'POST'])
def one():
    conn = sql_connect()
    cursor = conn.cursor()
    data = request.data
    data_json = json.loads(data)
    id = data_json['id']
    # sql = """select id,DATE_FORMAT(time, '%H:%i:%S'),price,uid,state,machine from `order` order by time DESC limit 2"""
    sql = """select id,DATE_FORMAT(time, '%H:%i:%S'),price,uid,state,machine from `order` order by time DESC limit 5"""
    cursor.execute(sql)
    a = cursor.fetchall()
    order_table = []
    # 要加0判断
    for i in a:
        order = {}
        order['id'] = i[0]
        order['time'] = i[1]
        order['price'] = i[2]
        order['uid'] = i[3]
        order['state'] = i[4]
        order['machine'] = i[5]
        order_table.append(order)

    now = datetime.datetime.now()
    p1 = now.strftime("%Y-%m-%d 0:0:0")
    p2 = now.strftime("%Y-%m-%d 23:59:59")
    sql = "select count(id) as order_num from `order` where time between '" + p1 + "' and '" + p2 + "';"
    cursor.execute(sql)
    a = cursor.fetchone()
    TodayNum = a[0]
    num = a[0]

    sql = "select sum(price)  as order_sum from `order` where time between '" + p1 + "' and '" + p2 + "';"
    cursor.execute(sql)
    a = cursor.fetchone()
    if a[0] == None:
        TodayMoney = 0
        AvrMoney = 0
    else:
        TodayMoney = a[0]
        AvrMoney = int(a[0] / num)

    return json.dumps({'data': order_table, 'tn': TodayNum, 'tm': TodayMoney, 'av': AvrMoney})

    # sql = ""


if __name__ == '__main__':
    app.run()
