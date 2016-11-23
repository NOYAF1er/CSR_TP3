import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Etend Thread
 * Quand le thread est lanc�, les streams n�cessaires � la communication avec le client 
 * sont cr��s, et une instance de FileExplorerProtocol est initialis�e 
 * avec un chemin par d�faut. Finalement, une boucle d��coute du client est mise en place. 
 * Quand un message du client est r��u, le serveur �met pour r�ponse la cha�ne de caract�res
 * produite par la m�thode processInput(inputString) de FileExplorerProtocol qui se charge 
 * de l�analyse de la commande
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
