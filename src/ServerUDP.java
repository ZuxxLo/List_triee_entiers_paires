import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
    static byte buffer[] = new byte[1024];

    public static void main(String argv[]) throws Exception {
        DatagramSocket socket = new DatagramSocket(1000);
        while (true) {
            System.out.println("Server is running...");
            DatagramPacket data = new DatagramPacket(buffer, buffer.length);
            socket.receive(data);

            String receivedString = new String(data.getData(), 0, data.getLength());
            String[] numbersString = receivedString.split(",");
            int[] numbers = new int[numbersString.length];
            for (int i = 0; i < numbersString.length; i++) {
                numbers[i] = Integer.parseInt(numbersString[i]);
            }

            StringBuilder responseBuilder = new StringBuilder();
            for (int number : numbers) {
                if (number % 2 == 0) {
                    responseBuilder.append(number).append(",");
                }
            }
            String responseData = responseBuilder.toString();

            DatagramPacket response = new DatagramPacket(responseData.getBytes(), responseData.length(), data.getAddress(), data.getPort());
            socket.send(response);
        }
    }
}
