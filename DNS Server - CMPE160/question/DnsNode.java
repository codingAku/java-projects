
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
/**
 * Node object of the DNS Tree.
 * All nodes have a set of ip addresses and a boolean value to set if they are valid domain names or not. 
 * For example, "example.com" is a valid domain name where it leads to a actual website, however, ".com" is not.
 * If this node is a valid domain name, Its set of ip addresses should not be empty.
 * This also has a map object where the children of this node are kept. 
 */
public class DnsNode {
	/**
	 * Map object which keeps the children of this node., 
	 * This map provides the utility of being a tree structure.
	 * For example, "example.com" is the child of the node ".com" where "this.example.com" is the child of the node "example.com".
	 */
	private Map<String, DnsNode> childNodeList;
	/**
	 * boolean value to state if this node is a valid website.
	 */
	private boolean validDomain;
	/**
	 *Set of ip addresses. 
	 */
	private Set<String> ipAddresses;
	/**
	 * A Queue of Ip addresses to provide the Round Robin algorithm. 	
	 */
	private Queue<String> ipQueue;
	/**
	 * 
	 * @returns child node list of this node.
	 */
	public Map<String, DnsNode> getChildNodeList() {
		return childNodeList;
	}
	/**
	 * 
	 * @returns the set of Ip addresses.
	 */
	public Set<String> getIpAddresses() {
		return ipAddresses;
	}
	/**
	 * Constructor of Dns node.
	 * Creates the set and queue of Ip addresses and sets the isValidDomain boolean value false.
	 */
	public DnsNode() {
		setValidDomain(false);
		childNodeList = new HashMap<String, DnsNode>();
		ipAddresses = new TreeSet<String>();
		ipQueue = new LinkedList<String>();

		
	}
	/**
	 * adds a new Ip address to set and queue of Ip addresses.
	 * @param IpAddress Ip address to be added.
	 */
	public void addIpAddress(String IpAddress) {
		ipAddresses.add(IpAddress);
		setValidDomain(true);
		ipQueue.add(IpAddress);
	}
	/**
	 * 
	 * @returns if this node is valid domain.
	 */
	public boolean isValidDomain() {
		return validDomain;
	}
	/**
	 * 
	 * @param validDomain sets the valid domain true or false.
	 */
	public void setValidDomain(boolean validDomain) {
		this.validDomain = validDomain;
	}
	/**
	 * 
	 * @returns the queue of Ip addresses.
	 */
	public Queue<String> getIpQueue() {
		return ipQueue;
	}




	
	


}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

