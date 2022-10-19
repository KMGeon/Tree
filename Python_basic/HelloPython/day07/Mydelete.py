import pymysql

conn = pymysql.connect(host='localhost', port=3305, user='root', passwd='1234', db='python', charset='utf8')

cursor = conn.cursor()

# statement
sql = "DELETE FROM emp WHERE e_id='%s'"

# 5. SQL 구문 실행하기
rows = cursor.execute(sql, ('99'))
print("cursor", cursor.rowcount)

conn.commit()
conn.close()
cursor.close()
