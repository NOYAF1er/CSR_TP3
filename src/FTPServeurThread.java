import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Etend Thread
 * Quand le thread est lancé, les streams nécessaires à la communication avec le client 
 * sont créés, et une instance de FileExplorerProtocol est initialisée 
 * avec un chemin par défaut. Finalement, une boucle d’écoute du client est mise en place. 
 * Quand un message du client est réçu, le serveur émet pour réponse la chaîne de caractères
 * produite par la méthode processInput(inputString) de FileExplorerProtocol qui se charge 
 * de l’analyse de la commande
 * 
 * @author NOYAF-PC
 *
 */
public class FTPServeurThread extends Thread {
	private FileExplorerProtocol feProt;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public FTPServeurThread(ObjectOutputStream output, ObjectInputStream input){
		this.output = output;
		this.input = input;
		feProt = new FileExplorerProtocol("/");
	}

	public void run(){
		try {
			while(true){
				// receiving and sending
				String question = (String) input.readObject();
				System.out.println("Question received: " + question);
		
				// thinking
				Thread.sleep(3000);
				
				String response = feProt.processInput(question);
				output.writeObject(new String(response));
				System.out.println("Response sent.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
