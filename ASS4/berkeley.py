import socket
import time

# Lists to store client connections and their reported times
client_sockets = []
client_times = []

def start_server():
    host = '127.0.0.1'
    port = 8080

    # Create and bind server socket
    server_socket = socket.socket()
    server_socket.bind((host, port))
    server_socket.listen(5)

    print("Server started. Waiting for 3 clients to connect...")       

    # Accept 3 clients
    for i in range(3):
        conn, addr = server_socket.accept()
        print(f"Client {i+1} connected from {addr}")
        client_sockets.append(conn)

    # Receive each client's local time
    for conn in client_sockets:
        client_time = float(conn.recv(1024).decode())
        client_times.append(client_time)
        print(f"Received client time: {client_time:.2f}")

    # Get server's own time (master clock)
    master_time = time.time()
    print(f"Server (Master) time: {master_time:.2f}")
    client_times.append(master_time)

    # Calculate average of all clocks (including server)
    average_time = sum(client_times) / len(client_times)
    print(f"Calculated average synchronized time: {average_time:.2f}")

    # Send synchronized time back to each client
    for conn in client_sockets:
        conn.send(str(average_time).encode())

    print("Synchronized time sent to all clients. Closing connections.")
    for conn in client_sockets:
        conn.close()

if __name__ == '__main__':
    start_server()
