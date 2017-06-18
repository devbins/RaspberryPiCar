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
running = True
print '服务开启，等待连接'
conn,addr = server.accept()
print addr
while running:
    data = str(conn.recv(1024))
    for x in data:
        print x
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
            running = False

server.close()

print "关闭连接！！！"


