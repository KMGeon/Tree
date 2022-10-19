import pymysql

conn = pymysql.connect(host='localhost', port=3305, user='root', passwd='1234', db='python', charset='utf8')

cursor = conn.cursor(pymysql.cursors.DictCursor)

# statement
sql = "SELECT * FROM emp"

# 5. SQL 구문 실행하기
cursor.execute(sql)

result = cursor.fetchall()
for record in result:
    print(record)
conn.close()
cursor.close()
