# -*- coding=utf-8 -*-

import socket
import car
ip_port = ('',8888)
server = socket.socket()
server.bind(ip_port)
server.listen(1)

GO='1'
BACK='2'
LEFT='3'
RIGHT='4'
PAUSE='5'
STOP='6'

print '服务开启，等待连接'

conn,addr = server.accept()
while True:
    data = conn.recv(1024)
    if data == GO:
        pass
    elif data == BACK:
        pass
    elif data == LEFT:
        pass
    elif data == RIGHT:
        pass
    elif data == PAUSE:
        pass
    elif data == STOP:
        pass

server.close()



if __name__ == '__main__':
    Car()
