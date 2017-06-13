#-*- coding=utf-8 -*-

import RPi.GPIO as GPIO
import time

class Car(object):

    def __init__(self):
        GPIO.setmode(GPIO.BCM)
        self.IN1 = 18
        self.IN2 = 17
        self.IN3 = 27
        self.IN4 = 22
        GPIO.setup(IN1,GPIO.OUT)
        GPIO.setup(IN2,GPIO.OUT)
        GPIO.setup(IN3,GPIO.OUT)
        GPIO.setup(IN4,GPIO.OUT)


    def back(self):
        GPIO.output(self.IN1,GPIO.HIGH)
        GPIO.output(self.IN2,GPIO.LOW)
        GPIO.output(self.IN3,GPIO.HIGH)
        GPIO.output(self.IN4,GPIO.LOW) 

    def forward(self):
        GPIO.output(self.IN1,GPIO.LOW)
        GPIO.output(self.IN2,GPIO.HIGH)
        GPIO.output(self.IN3,GPIO.LOW)
        GPIO.output(self.IN4,GPIO.HIGH) 
    
    def left(self):
        GPIO.output(self.IN1,GPIO.HIGH)
        GPIO.output(self.IN2,GPIO.LOW)
        GPIO.output(self.IN3,False)
        GPIO.output(self.IN4,False) 

    def right(self):
        GPIO.output(self.IN1,GPIO.LOW)
        GPIO.output(self.IN2,GPIO.HIGH)
        GPIO.output(self.IN3,False)
        GPIO.output(self.IN4,False) 


    def pause(self):
        GPIO.output(self.IN1,False)
        GPIO.output(self.IN2,False)
        GPIO.output(self.IN3,False)
        GPIO.output(self.IN4,False)

    def stop(self):
        GPIO.cleanup()



if __name__ == '__main__':
    forward()
    time.sleep(3)
    back()
    time.sleep(3)
    left()
    time.sleep(3)
    right()
    time.sleep(3)
    GPIO.cleanup()


