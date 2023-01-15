import pymysql


class MemberDao:
    
    def __init__(self):
        self.conn = pymysql.connect(host='localhost', port=3305, user='root', passwd='1234',
                      db='python', charset='utf8')

        self.curs = self.conn.cursor(pymysql.cursors.DictCursor)
    
    def selects(self):
        sql = "SELECT m_seq , m_name , tel , army_yn FROM member"
        self.curs.execute(sql)
        rows = self.curs.fetchall()
        return rows
    
    def select(self, e_id):
        sql = f"""
            SELECT 
                m_seq,
                m_name,
                tel,
                army_yn
            FROM 
                member
            WHERE
                m_seq = '{m_seq}'
        """
        self.curs.execute(sql)
        rows = self.curs.fetchall()
        return rows[0]
    
    def insert(self, e_id, e_name, sex, addr):
        sql = f"""
            INSERT
            INTO 
            EMP(
                e_id,
                e_name,
                sex,
                addr
                )
            VALUES 
                ('{e_id}', '{e_name}', '{sex}', '{addr}')
        """
        cnt = self.curs.execute(sql)
        self.conn.commit()
        return cnt
    
    def update(self, e_id, e_name, sex, addr):
        sql = f"""import pymysql


class MemberDao:

    def __init__(self):
        self.conn = pymysql.connect(host='localhost', user='root', password='python', port=3305,
                               db='python', charset='utf8')
        self.curs = self.conn.cursor(pymysql.cursors.DictCursor)
        
    def selects(self):
        sql = "select m_seq,m_name,tel,army_yn from member"
        self.curs.execute(sql)
        rows = self.curs.fetchall()
        return rows
    
    def select(self, m_seq):
         sql = f"""
             select 
                m_seq,
                m_name,
                tel,
                army_yn 
            from 
                member
            where 
                m_seq = '{m_seq}'
        """
        self.curs.execute(sql)
        rows = self.curs.fetchall()
        return rows[0]
    
    def insert(self, m_name, tel, army_yn):
        sql = f"""
            insert into member 
            (m_name, tel, army_yn)
            values 
            ('{m_name}', '{tel}', '{army_yn}')
        """
        cnt = self.curs.execute(sql)
        self.conn.commit() 
        return cnt
    
    def update(self, m_seq, m_name, tel, army_yn):
        sql = f"""
            update member
            set
                m_name = '{m_name}',
                tel = '{tel}',
                army_yn = '{army_yn}'
            where
                m_seq = '{m_seq}'
        """
        cnt = self.curs.execute(sql)
        self.conn.commit() 
        return cnt
    
    def delete(self, m_seq):
        sql = f"""
            delete from member
            where
                m_seq = '{m_seq}'
        """
        cnt = self.curs.execute(sql)
        self.conn.commit() 
        return cnt

    def __del__(self):
        self.curs.close()
        self.conn.close()
        
        
if __name__ == '__main__':
    ed = MemberDao()
    cnt = ed.delete('3')
    print(cnt)
    
    
            UPDATE emp
             SET    e_name = '{e_name}'
                  , sex = '{sex}'
                  , addr = '{addr}'
             WHERE  e_id = '{e_id}'"""
             
        row = self.curs.execute(sql)
        self.conn.commit()
        return row
    
    def delete(self, e_id):
        sql = f"""
            DELETE FROM emp 
            WHERE e_id = '{e_id}'"""
         
        row = self.curs.execute(sql)
        self.conn.commit()
        return row
    
    def __del__(self):
        self.curs.close()
        self.conn.close()
        
    
if __name__ == '__main__':
    ed = memDao()
    row = ed.delete('4')
    print(row)
    
