package studentCoursesBackup;

import java.util.List;
import java.util.ArrayList;

public class Node implements Cloneable, SubjectI, ObserverI {

	private int bnum;

	private List<String> courses;

	private Node leftChild;
	private Node rightChild;

	private List<ObserverI> observers;

	private Node subject;

	/**
	 * @return Node that is a clone of Node parameter
	 * clones node and returns new copy
	 */
	protected Object clone() {
		Node node = new Node();
		node.setBNum(getBNum());
		for(int i=0; i<getCourses().size(); i++)
			node.addCourse(getCourses().get(i));
		return node;
	}

	/**
	 * @return boolean true if same object, false otherwise
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Node))
			return false;
		if(o == this)
			return true;
		if(o == null)
			return false;
		Node n = (Node) o;

		if(n.getBNum() == this.getBNum())
			return true;
		else
			return false;
	}
		

	/**
	 * @return nothing
	 * registers observer by adding it to list of observers
	 */
	public void registerObserver(ObserverI o) {
		observers.add(o);
	}

	/**
	 * @return nothing
	 * removes observer by removing it from list of observers
	 */
	public void removeObserver(ObserverI o) {
		observers.remove(o);
	}

	/**
	 * @return nothing
	 * notifies observers that state has changed
	 */
	public void notifyObservers(Object o) {
		if(!(o instanceof String))
			return;
		for(int i=0; i<observers.size(); i++) {
			observers.get(i).update(o);
		}
	}

	/**
	 * @return nothing
	 * updates the observer nodes with new state
	 */
	public void update(Object o) {
		if(!(o instanceof String))
			return;
		String course = (String) o;
		boolean removed = this.removeCourse(course);
		if(removed)
			return;
		else
			this.addCourse(course);
	}

	/**
	 * @return subject of this node
	 */
	public Node getSubject() {
		return subject;
	}

	/**
	 * @return nothing
	 * sets new subject for this node
	 */
	public void setSubject(Node n) {
		subject = n;
	}

	/**
	 * @return observers, list of observers nodes
	 */
	public List<ObserverI> getObservers() {
		return observers;
	}

	/**
	 * @return nothing
	 * sets observers to new list
	 */
	public void setObservers(List<ObserverI> obs) {
		observers = obs;
	}

	/**
	 * constructor with no values
	 */
	public Node() {
		bnum = 0;
		courses = new ArrayList<String>();
		leftChild = null;
		rightChild = null;
		observers = new ArrayList<ObserverI>();
	}

	/**
	 * constructor with bnumber and first course
	 */
	public Node(int b, String s) {
		bnum = b;
		courses = new ArrayList<String>();
		courses.add(s);
		leftChild = null;
		rightChild = null;
		observers = new ArrayList<ObserverI>();
	}

	/**
	 * constructor with bnumber only
	 */
	public Node(int b) {
		bnum = b;
		courses = new ArrayList<String>();
		leftChild = null;
		rightChild = null;
		observers = new ArrayList<ObserverI>();
	}


	/**
	 * @return String representation of Node
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(bnum));
		sb.append(":");
		for(int x=0; x<courses.size(); x++) {
			sb.append(" ");
			sb.append(courses.get(x));
		}
		return sb.toString();
	}

	/**
	 * @return b num of this node
	 */
	public int getBNum() {
		return bnum;
	}

	/**
	 * @return nothing
	 * sets b num of this node
	 */
	public void setBNum(int b) {
		bnum = b;
	}

	/**
	 * @return list of courses of this node
	 */
	public List<String> getCourses() {
		return courses;
	}

	/**
	 * @return nothing
	 * sets courses list of this node
	 */
	public void setCourses(List<String> c) {
		courses = c;
	}

	/**
	 * @return leftChild of this node
	 */
	public Node getLeftChild() {
		return leftChild;
	}

	/**
	 * @return nothing
	 * sets left child of this node
	 */
	public void setLeftChild(Node lc) {
		leftChild = lc;
	}

	/**
	 * @return rightChild of this node
	 */
	public Node getRightChild() {
		return rightChild;
	}

	/**
	 * @return nothing
	 * sets right child of this node
	 */
	public void setRightChild(Node rc) {
		rightChild = rc;
	}

	/**
	 * @return boolean representing success/failure of insert
	 * adds new course string to courses list if not already there
	 */
	public boolean addCourse(String s) {
		if(!courses.contains(s)) {
			courses.add(s);
			return true;
		}
		else
			return false;
	}

	/**
	 * @return true if removal was successful, false otherwise
	 * removes specified string from arraylist of courses (strings)
	 */
	public boolean removeCourse(String s) {
		return courses.remove(s);
	}

}
