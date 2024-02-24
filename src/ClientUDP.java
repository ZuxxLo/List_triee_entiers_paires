import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String argv[]) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Demander à l'utilisateur de saisir les valeurs du tableau
        System.out.print("Entrez la liste des entiers séparés par des espaces: ");
        String input = scanner.nextLine();
        String[] values = input.split("\\s+");

        // Convertir les valeurs en entiers et les stocker dans un tableau
        int[] tab = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            tab[i] = Integer.parseInt(values[i]);
        }

        InetAddress serveur = InetAddress.getByName("localhost");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {
            stringBuilder.append(tab[i]);
            if (i != tab.length - 1) {
                stringBuilder.append(",");
            }
        }
        String message = stringBuilder.toString();

        byte buffer[] = message.getBytes();
        DatagramPacket donner_envoyer = new DatagramPacket(buffer, buffer.length, serveur, 1000);
        DatagramSocket socket = new DatagramSocket();
        socket.send(donner_envoyer);

        DatagramPacket donner_recu = new DatagramPacket(new byte[1024], 1024);
        socket.receive(donner_recu);
        System.out.println("Données paires reçues : " + new String(donner_recu.getData(), 0, donner_recu.getLength()));
        System.out.println("De : " + donner_recu.getAddress() + ":" + donner_recu.getPort());

        // Fermer le scanner
        scanner.close();
    }
}


