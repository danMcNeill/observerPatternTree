import java.util.List;
import java.util.ArrayList;

public class Node {

	private int bnum;

	private List<String> courses;

	private Node leftChild;
	private Node rightChild;

	/**
	 * constructor with no values
	 */
	public Node() {
		bnum = 0;
		courses = new ArrayList<String>();
		leftChild = null;
		rightChild = null;
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
	}

	/**
	 * constructor with bnumber only
	 */
	public Node(int b) {
		bnum = b;
		courses = new ArrayList<String>();
		leftChild = null;
		rightChild = null;
	}

	/**
	 * constructor to clone a node
	 */
	public Node(Node n) {
		bnum = n.getBNum();
		courses = new ArrayList<String>();
		leftChild = n.getLeftChild();
		rightChild = n.getRightChild();
		for(int x=0; x<n.getCourses().size(); x++) {
			courses.add(n.getCourses().get(x));
		}
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
	public boolean addNewCourse(String s) {
		if(!courses.contains(s)) {
			courses.add(s);
			return true;
		}
		else
			return false;
	}

}
