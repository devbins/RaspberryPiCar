# -*- coding=utf-8 -*-

import socket

ip_port = ('',8888)

server = socket.socket()
server.bind(ip_port)
server.listen(1)

print '服务开启，等待连接'

conn,addr = server.accept()
data = conn.recv(1024)
print data

server.close()

