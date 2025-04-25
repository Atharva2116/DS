# berkeley_client.py
import socket
import time
import random

def start_client():
    server_ip = '192.168.183.203'  # Replace with actual IP of server machine
    port = 8080

    client_socket = socket.socket()
    client_socket.connect((server_ip, port))

    # Simulate a clock with skew: +/- 5 seconds
    local_time = time.time() + random.uniform(-5, 5)
    print(f"Client local time before sync: {local_time:.2f}")
    client_socket.send(str(local_time).encode())

    # Receive synchronized time from server
    sync_time = float(client_socket.recv(1024).decode())
    print(f"Synchronized time received: {sync_time:.2f}")

    client_socket.close()

if __name__ == '__main__':
    start_client()
