import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Gère le côté client des communications. Elle crée les streams nécessaires à
 * la communication avec le serveur et crée un canal de communication avec le
 * serveur. Ensuite, en boucle, elle accepte une ligne en entée du client sur
 * System.in et l’envoie au serveur, puis réçoit la réponse du serveur.
 * 
 * @author NOYAF-PC
 *
 */
public class FTPClient {

	private InetAddress serverAddress;
	private Socket clientSocket;

	private ObjectOutputStream output;
	private ObjectInputStream input;

	public void work() {

		try {

			// connecting to the server
			System.out.print("Connecting to the server...");
			serverAddress = InetAddress.getByName("localhost");
			clientSocket = new Socket(serverAddress, 7777);
			System.out.println(" done.");

			// getting the streams
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			input = new ObjectInputStream(clientSocket.getInputStream());

			while(true){
				// thinking
				Thread.sleep(2000);
				
				//Saisie de la commande user
				Scanner sc = new Scanner(System.in);
				System.out.println("Veuillez saisir votre requête :");
				String userCmd = sc.nextLine();
				
				// sending a message			
				output.writeObject(userCmd);
				System.out.println("Message sent, waiting for the response...");
	
				String resp = (String) input.readObject();
				System.out.println("Response received: " + resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // work
	
    public static void main (String argv[])
    {
	     new FTPClient().work();
    }

}
