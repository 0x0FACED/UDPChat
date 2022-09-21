import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {
    private static InetAddress inetAddress;
    private static int port;
    private static DatagramSocket clientSocket;
    public static void main(String[] args) throws SocketException, UnknownHostException, InterruptedException {
        doConnection();

        ReadMessage readMessage = new ReadMessage(port, inetAddress, clientSocket);
        WriteMessage writeMessage = new WriteMessage(inetAddress, port, clientSocket);
        readMessage.start();
        writeMessage.start();
        writeMessage.join();
    }

    public static void doConnection() throws UnknownHostException, SocketException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hostname: ");
        inetAddress = InetAddress.getByName(scanner.nextLine());
        System.out.print("Enter port: ");
        port = scanner.nextInt();
        clientSocket = new DatagramSocket();

    }
}