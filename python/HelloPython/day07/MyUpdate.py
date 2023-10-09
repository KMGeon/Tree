import pymysql

conn = pymysql.connect(host='localhost', port=3305, user='root', passwd='1234', db='python', charset='utf8')

cursor = conn.cursor()

# statement
sql = """UPDATE emp
	SET
		e_name='%s',
		sex='%s',
		addr='%s'
	WHERE e_id=%s"""

# 5. SQL 구문 실행하기
cnt = cursor.execute(sql, ('6', '6', '6', '99'))
print("cursor", cnt)

conn.commit()
conn.close()
cursor.close()
