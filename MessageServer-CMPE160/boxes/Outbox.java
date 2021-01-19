
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import java.util.*;
import elements.*;
/**
 * Outbox object which stores the messages sent by its owner. 
 */
public class Outbox extends Box{
	/**
	 * Queue where the sent messages are kept.
	 * regardless of whether a message is received by the User it is sent to, it is stored here as soon as it is sent to the server.
	 */
	private Queue<Message> sent;
	/**
	 * Constructor of the Outbox. 
	 * Creates the sent Queue.
	 * @param owner User who owns the Outbox.
	 */
	public Outbox(User owner) {
		super(owner);
		sent = new LinkedList<Message>();
	}
/**
 * 
 * @returna the sent Queue.
 */
	public Queue<Message> getSent() {
		return sent;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

