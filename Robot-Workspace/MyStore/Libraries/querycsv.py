import sys
import os.path
import getopt
import csv
import sqlite3

def GetData(sql_cmd):
    # query FROM part contains the DB (i.e. file) name
    dbPath="Data/"
    queryParts = sql_cmd.lower().split()
    fileNamePartIndex = queryParts.index('from') + 1
    fileName = dbPath + queryParts[fileNamePartIndex] + '.csv'
    return querycsv(fileName, sql_cmd)

def querycsv(csvfile, sql_cmd):
    conn = sqlite3.connect(":memory:")
    inhead, intail = os.path.split(csvfile)
    tablename = os.path.splitext(intail)[0]
    csv_to_sqldb(conn, csvfile, tablename)
    #
    # Execute the SQL
    datarows = qsqldb( conn, sql_cmd)
    #
    # Clean up.
    conn.close()
    return datarows
    
def qsqldb( sqldb, sql_cmd):
    # Execute SQL
    curs = sqldb.cursor()
    curs.execute(sql_cmd)
        
    datarows = curs.fetchall()
    headers = [ item[0] for item in curs.description ]

    results = []
    for row in datarows:
        results.append(dict(zip(headers,row)))
    
    return results 

def csv_to_sqldb(sqldb, infilename, table_name):
    dialect = csv.Sniffer().sniff(open(infilename, "rt").readline())
    inf = csv.reader(open(infilename, "rt"), dialect)
    column_names = next(inf)
    # original text ->  column_names = inf.next()
    colstr = ",".join(column_names)
    try:
        sqldb.execute("drop table %s;" % table_name)
    except:
        pass
    sqldb.execute("create table %s (%s);" % (table_name, colstr))
    for l in inf:
        sql = "insert into %s values (%s);" % (table_name, quote_list_as_str(l))
        sqldb.execute(sql)
    sqldb.commit()

def quote_str(str):
    if len(str) == 0:
        return "''"
    if len(str) == 1:
        if str == "'":
            return "''''"
        else:
            return "'%s'" % str
    if str[0] != "'" or str[-1:] != "'":
        return "'%s'" % str.replace("'", "''")
    return str

def quote_list(l):
    return [quote_str(x) for x in l]

def quote_list_as_str(l):
    return ",".join(quote_list(l))    
