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

}
