
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package executable;
import java.io.*;
import java.util.*;
import boxes.*;
import elements.*;

public class Main{
	/**
	 * Main method of the program. 
	 * It takes two arguments, one is the input file and other one is the output file. 
	 * Compatible to the input format, main method uses a Scanner object to scan the next token of input.
	 * First token is an Integer which states the number of users in the program. All users are created and kept in an Java's ArrayList object.
	 * Second token states the number of queries in the input file. There is a for loop that runs number of queries time.
	 * Third token states the capacity of the server.
	 * Every time the loop runs, it takes the next token as an input and that token states which operation is going to be run.
	 * Main method uses a PrintStream object of Java to print to the output file.
	 * There is a integer value named time, which is the current time of the program. Every operation increments time by one except reading messages operations.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner x = new Scanner(new File(args[0]));
		PrintStream y = new PrintStream(new File(args[1]));
		
		int numOfusers = x.nextInt();
		int numOfqueries = x.nextInt();
		int capacity = x.nextInt();
		int time = 0;
		
		ArrayList<User> users = new ArrayList<User>();
		for(int i=0; i<numOfusers; i++) {
			users.add(new User(i));
		}
		
		Server server = new Server(capacity);
		
		for(int i=0; i<numOfqueries; i++) {
			int query = x.nextInt();
			
			if(query==0) {
				int senderId = x.nextInt();
				int receiverId = x.nextInt();
				String body = x.nextLine().substring(1);
				
				users.get(senderId).sendMessage(users.get(receiverId), body, time, server);
				server.checkServerLoad(y);
				time++;
			}
			else if(query==1) {
				int receiverId = x.nextInt();
				
				users.get(receiverId).getInbox().receiveMessages(server, time);
				server.checkServerLoad(y);
				time++;
			}
			else if(query==2) {
				int receiverId = x.nextInt();
				int num = x.nextInt();
				
				time+= users.get(receiverId).getInbox().readMessages(num, time);
				
			}
			else if(query==21) {
				int receiverId = x.nextInt();
				int senderId = x.nextInt();
				
				time+= users.get(receiverId).getInbox().readMessages(users.get(senderId), time);
				
			}
			else if(query==22) {
				int receiverId = x.nextInt();
				int messageId = x.nextInt();
				users.get(receiverId).getInbox().readMessage(messageId, time);
				time++;
			}
			else if(query==3) {
				int user1 = x.nextInt();
				int user2 = x.nextInt();
				
				users.get(user1).addFriend(users.get(user2));
				time++;
			}
			else if(query==4) {
				int user1=x.nextInt();
				int user2 = x.nextInt();
				
				users.get(user1).removeFriend(users.get(user2));
				time++;
			}
			else if(query==5) {
				server.flush();
				time++;
			}
			else if(query==6) {
				y.print("Current load of the server is "+ server.getCurrentSize() + " characters.\n" );
				time++;
			}
			else if(query==61) {
				int userId = x.nextInt();
				if(users.get(userId).getInbox().LastMessage()!=null) 
				y.print(users.get(userId).getInbox().LastMessage());
				time++;
				
			
			}
		}
	}		
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

