# -*- coding=utf-8 -*-

import socket
from car import Car

ip_port = ('',8888)
server = socket.socket()
server.bind(ip_port)
server.listen(5)

GO='1'
BACK='2'
LEFT='3'
RIGHT='4'
PAUSE='5'
STOP='6'

picar = Car()

conn,addr = server.accept()
print '服务开启，等待连接'
print addr
while True:
    data = str(conn.recv(1024))
    print data
    if data == GO:
        picar.forward()
    elif data == BACK:
        picar.back()
    elif data == LEFT:
        picar.left()
    elif data == RIGHT:
        picar.right()
    elif data == PAUSE:
        picar.pause()
    elif data == STOP:
        break 

server.close()



