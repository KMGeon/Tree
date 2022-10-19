import pymysql

conn = pymysql.connect(host='localhost', port=3305, user='root', passwd='1234', db='python', charset='utf8')

cursor = conn.cursor()

# statement
sql = """INSERT INTO emp (e_id, e_name, sex, addr) 
      VALUES( %s, %s, %s, %s)"""



# 5. SQL 구문 실행하기
cnt = cursor.execute(sql, ('7', '7', '7', '7'))
print("cursor", cursor.rowcount)


conn.commit()
conn.close()
cursor.close()
