# berkeley_server.py
import socket
import time
import threading

client_sockets = []
client_times = []
lock = threading.Lock()
connection_window = 40  # seconds to wait for clients

def handle_client(conn, addr):
    try:
        client_time = float(conn.recv(1024).decode())
        print(f"[{addr}] Reported time: {client_time:.2f}")
        with lock:
            client_sockets.append(conn)
            client_times.append(client_time)
    except:
        conn.close()

def start_server():
    host = '0.0.0.0'  # Accept connections from other machines
    port = 8080
    server_socket = socket.socket()
    server_socket.bind((host, port))
    server_socket.listen(5)

    print("Server started. Waiting for clients to connect...\n")

    start_time = time.time()
    threads = []

    while time.time() - start_time < connection_window:
        server_socket.settimeout(connection_window - (time.time() - start_time))
        try:
            conn, addr = server_socket.accept()
            print(f"Client connected from {addr}")
            thread = threading.Thread(target=handle_client, args=(conn, addr))
            thread.start()
            threads.append(thread)
        except socket.timeout:
            break

    for t in threads:
        t.join()

    if not client_sockets:
        print("No clients connected. Exiting.")
        return

    master_time = time.time()
    print(f"\nServer (Master) current time: {master_time:.2f}")

    with lock:
        client_times.append(master_time)

    average_time = sum(client_times) / len(client_times)
    print(f"Calculated synchronized time: {average_time:.2f}")

    # Send synchronized time to all clients
    for conn in client_sockets:
        try:
            conn.send(str(average_time).encode())
            conn.close()
        except:
            continue

    print("Synchronized time sent to all clients. Server done.")

if __name__ == '__main__':
    start_server()
