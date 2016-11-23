import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Gère l’ouverture d’un socket serveur sur un port déterminé sur localhost,
 * puis crée une instance de FTPServeurThread à chaque fois qu’un client se
 * connecte, et lance cette instance.
 * 
 * @author NOYAF-PC
 *
 */
public class FTPServeur {
	private InetAddress localAddress;
	private ServerSocket serverSocket;
	private Socket localSocket;

	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	private FTPServeurThread svThread;

	public void work() {
		try {
			serverSocket = new ServerSocket(7777, 1);
			System.out.println("Server socket created, waiting for connection...");
			
			localSocket = serverSocket.accept();
			System.out.println("I have a new connection.");
			
			// getting the streams
			output = new ObjectOutputStream(localSocket.getOutputStream());
			input = new ObjectInputStream(localSocket.getInputStream());
			
			svThread = new FTPServeurThread(output, input);			
			svThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // work

    public static void main (String argv[])
    {
      new FTPServeur().work();
    }
	
}
