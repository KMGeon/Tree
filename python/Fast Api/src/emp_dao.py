from pymysql import cursors
import pymysql
from pymysql.cursors import DictCursor


class EmpDaO:

    def __init__(self):
       self.conn = pymysql.connect(host='localhost', port=3305, user='root', passwd='1234', db='python', charset='utf8')
       self.curs = self.conn.cursor()

    def selects(self):
        sql = "select e_id,e_name,sex,addr from emp"
        self.curs.execute(sql)
        rows = self.curs.fetchall()
        return rows
    
    # def select(self, e_id):
    #     sql = f"""
    #     select
    #          e_id,
    #          e_name,
    #          sex,
    #          addr 
    #      from 
    #          emp 
    #      where e_id ='{e_id}' 
    #     """
    #     self.curs.execute(sql)
    #     rows = self.curs.fetchall()
    #     return rows[0]
    
    # def inset(self, e_id, e_name , sex, addr):
    #     sql = f"""
    #     insert into emp
    #     (e_id,e_name,sex,addr)
    #     values
    #     ('{e_id}','{e_name}','{sex}','{addr}')
    #     """
    #     cnt = self.curs.execute(sql)
    #     self.conn.commit()
    #     return cnt
    
    # def update(self, e_id, e_name, sex, addr):
    #     sql = f"""
    #     UPDATE emp
    #     SET
    #         e_name='{e_name}',
    #         sex = '{sex}',
    #         addr = '{addr}'
    #     WHERE
    #         e_id='{e_id}'
    #     """
    #     cnt = self.curs.execute(sql)
    #     self.conn.commit()
    #     return cnt
    #
    # def delete(self, e_id):
    #     sql = f"""
    #     delete from emp
    #         where
    #             e_id='{e_id}'
    #     """
    #     cnt = self.curs.execute(sql)
    #     self.conn.commit()
    #     return cnt
    #
    # def __del__(self):
    #     self.curs.close()
    #     self.conn.close()


if __name__ == '__main__':
    ed = EmpDaO()
    rows = ed.selects()
    # emp = ed.select('1')
    # cnt = ed.inset('3', '3', '3', '3')
    print(rows)

