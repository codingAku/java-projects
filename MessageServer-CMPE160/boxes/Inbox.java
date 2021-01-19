
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;
import java.io.PrintStream;
import java.util.*;
import elements.*;
/**
 * Inbox object which stores the messages that are sent to its owner User. 
 * Stores the read messages in a queue and stores the unread one's in a stack.
 */
public class Inbox extends Box{
	/**
	 * Stack object to store the unread messages.
	 */
	private Stack<Message> unread;
	/**
	 * Queue that stores the read messages.
	 */
	private Queue<Message> read;
	/**
	 * Constructor of Inbox.
	 * Creates the read and unread collections.
	 * @param user Owner of the Inbox.
	 */
	public Inbox(User user) {
	super(user);
	unread = new Stack<Message>();
	read = new LinkedList<Message>();
	
	}
	/**
	 * receives messages that are sent to this Inbox's owner from the server.
	 * Updates the time of received of the messages. All messages get received at the same time.
	 * @param server Server where the messages are kept.
	 * @param time current time.
	 */
	public void receiveMessages(Server server,int time) {
		long keep =0;
		Queue<Message> backup = new LinkedList<Message>();
		for(Message x : server.getMsgs()) {
			if(x.getReceiver().equals(this.getOwner())&&x.getReceiver().isFriendsWith(x.getSender())) {
				x.setTimeStampReceived(time);
				backup.add(x);
				unread.push(x);
				keep+=x.getBody().length();
			}
		}
		server.getMsgs().removeAll(backup);
		server.setCurrentSize(server.getCurrentSize()-keep);
		
	}
	/**
	 * reads a particular amount of messages from the unread stack.
	 * Updates the time of read of the messages. Messages get read in order. Every one of them is read in one time stamp.
	 * @param num number of messages to be read. If 0 or more than the number of unread messages, the method reads them all.
	 * @param time current time.
	 * @returns 1 if only one message is read or no messages have been read. Otherwise, returns the number of messages read.
	 */
	public int readMessages(int num, int time) {
		int numOfMessages=0;
		if(num==0 || num>unread.size()) {
			while(!unread.isEmpty()) {
				Message x = unread.pop();
				x.setTimeStampRead(time++);
				read.add(x);
				numOfMessages++;
				
			}
		}else {
			for(int i=0; i<num; i++) {
				Message x = unread.pop();
				x.setTimeStampRead(time++);
				read.add(x);
				numOfMessages++;
				
			}
		}
		return (numOfMessages==0 || numOfMessages==1) ? 1 : numOfMessages;
		
	}
	/**
	 * reads all the messages that are sent by a particular user.
	 * Updates the time of read of the messages. Messages get read in order. Every one of them is read in one time stamp. 
	 * @param sender User who sent the messages.
	 * @param time current time.
	 * @returns 1 if only one message is read or no messages have been read. Otherwise, returns the number of messages read.
	 */
	public int readMessages(User sender, int time) {
		int numOfMessages = 0;
		Stack<Message> backup = new Stack<Message>();
		for(Message msg : unread) {
			if(msg.getSender().equals(sender)) {
				backup.push(msg);
			}
		}
		unread.removeAll(backup);
		
		while(!backup.isEmpty()) {
			Message x = backup.pop();
			numOfMessages++;
			x.setTimeStampRead(time++);
			read.add(x);
		}
		
		return (numOfMessages==0 || numOfMessages==1) ? 1 : numOfMessages;
		
	}
	/**
	 * reads a particular Message.
	 * Updates the time of read of the message.
	 * @param msgId ID number of the message who is going to be read.
	 * @param time current time
	 */
	public void readMessage(int msgId, int time) {
		for(Message msg : unread) {
			if(msg.getID()==msgId) {
				msg.setTimeStampRead(time);
				read.add(msg);
			}
		}
	}
	/**
	 * 
	 * @returns the last message read by this Inbox.
	 */
	public Message LastMessage() {
		if(read.isEmpty()) {
			return null;
		}
		Iterator<Message> y = read.iterator();
		LinkedList<Message> a = (LinkedList<Message>) read;
		Message x = a.getLast();
		return x;
	}
	
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

