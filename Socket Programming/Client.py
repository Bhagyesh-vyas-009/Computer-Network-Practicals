import socket
host = '127.0.0.1'
port = 5001
client = socket.socket()
client.connect((host,port))
message = input("type message: ")
while message != 'quit':
   client.send(message.encode())
   data = client.recv(1024).decode()
   print ('Received from server: ' + data)
   message = input("Message: ")
client.close()