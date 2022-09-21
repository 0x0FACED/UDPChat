import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WriteMessage extends Thread{
    private static String userName;
    private InetAddress address;
    private int port;
    private DatagramSocket socket;

    public WriteMessage(InetAddress address, int port, DatagramSocket socket){
        this.address = address;
        this.port = port;
        this.socket = socket;
        this.userName = "Unknown";
    }

    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String message = scanner.nextLine();
            if (message.charAt(0) == '@'){
                commandMenu(message);
            } else {
                sendMessage(message);
            }
        }
    }

    public void sendMessage(String message){
        ServerTime.setTime(new Date());
        ServerTime.setDt1(new SimpleDateFormat("HH:mm:ss"));
        ServerTime.setDtime(ServerTime.getDt1().format(ServerTime.getTime()));
        message = "[" + ServerTime.getDtime() + "] " + getUserName() + ": " + message;
        byte[] buffer;
        buffer = message.getBytes();
        DatagramPacket outputPacket = new DatagramPacket(buffer, message.length(), address, port);
        try {
            socket.send(outputPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void commandMenu(String message){

        String[] options;
        options = message.split(" ");
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (options[0].equals("@name")) {
                System.out.println("Your new username: ");
                WriteMessage.setUserName(options[1]);
                System.out.println(options[1]);
                break;
            } else if (options[0].equals("@send")) {
                sendMessage(options[1]);
                break;
            } else if (options[0].equals("@exit")) {
                System.exit(0);
            } else {
                System.out.println("Wrong command. Please, try again!");
            }
        }
    }
    public String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        WriteMessage.userName = userName;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }
}