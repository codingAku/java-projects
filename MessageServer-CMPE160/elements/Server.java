
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

import java.io.*;
import java.util.*;
import boxes.*;
/**
 * Server object which stores the messages sent by users.
 * Messages are kept in a Queue.
 * Current size of the server is the summation of all characters of the messages' bodies.
 * Has a capacity, which means the limit of characters it can store. When exceeded, all messages are deleted.
 * Also gives warnings about being 50% full and 80% full. However, Server should not give unnecessary warnings,
 * for example, it should print a warning if it's 49% full already and become 56% full, but should not give a warning when it is 56% full and becomes 65%(any number less than 80%).
 * To provide this, there is boolean field values of server which are updated every time server gives a warning. 
 * If a User's Inbox demands to receive messages sent to it's owner, that messages get transferred to Inbox's unread stack and removed from the server. 
 *
 */
public class Server{
	/**
	 * the maximum number of characters that Server can store.
	 */
	private long capacity;
	/**
	 * the summation of all characters in the bodies of messages which are currently stored in server.
	 */
	private long currentSize;
	/**
	 * Queue that stores messages.
	 */
	private Queue<Message> msgs;
	/**
	 * a boolean value which shows if the server should give a warning about being 50% full. If server has already given one, it becomes false.
	 */
	private boolean fiftyWarning = true;
	/**
	 * a boolean value which shows if the server should give a warning about being 80% full. If server has already given one, it becomes false.
	 */
	private boolean eightyWarning = true;
	/**
	 * Constructor of Server.
	 * Creates the Queue of messages.
	 * @param capacity limit of server.
	 */
	public Server(long capacity) {
		this.capacity=capacity;
		msgs = new LinkedList<Message>();
	}
	/**
	 * checks Server's current load and prints a warning if it exceeded 50% or 80%. If it is reached at its capacity, all messages in the Queue are deleted.
	 * @param printer Print Stream object for server to write to output file.
	 */
	public void checkServerLoad(PrintStream printer) {
		long eight = (capacity*80)/100;
		long five = (capacity*50)/100;
		if(currentSize<five) {
			fiftyWarning = true;
			eightyWarning = true;
		}
		else if(currentSize>=five && currentSize<eight && fiftyWarning) {
			printer.printf("%s","Warning! Server is 50% full.\r\n");
			fiftyWarning = false;
			eightyWarning = true;
		}
		else if(currentSize>=eight && currentSize<capacity && eightyWarning) {
			printer.printf("%s","Warning! Server is 80% full.\r\n");
			fiftyWarning = true;
			eightyWarning = false;	
		}
		else if(currentSize>=capacity) {
			printer.print("Server is full. Deleting all messages...\r\n");
			this.flush();
		}
	}
	/**
	 * clears all messages in the Queue.
	 */
	public void flush() {
		msgs.clear();
		currentSize =0;
		fiftyWarning=true;
		eightyWarning=true;
	}
	/**
	 * 
	 * @returns the Queue of messages.
	 */
	
	public Queue<Message> getMsgs() {
		return msgs;
	}
	/**
	 * 
	 * @returns current load of server.
	 */
	public long getCurrentSize() {
		return currentSize;
	}
	/**
	 * 
	 * @param size long value to set current size.
	 */
	public void setCurrentSize(long size) {
	currentSize = size;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

