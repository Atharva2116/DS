import socket
import time
import random

def start_client():
    host = '127.0.0.1'
    port = 8080

    # Create a socket and connect to server
    client_socket = socket.socket()
    client_socket.connect((host, port))

    # Simulate local time drift using random offset
    local_time = time.time() + random.uniform(-5, 5)
    print(f"Client local time (before sync): {local_time:.2f}")

    # Send local time to the server
    client_socket.send(str(local_time).encode())

    # Receive synchronized time from the server
    sync_time = float(client_socket.recv(1024).decode())
    print(f"Synchronized time received from server: {sync_time:.2f}")

    client_socket.close()

if __name__ == '__main__':
    start_client()
