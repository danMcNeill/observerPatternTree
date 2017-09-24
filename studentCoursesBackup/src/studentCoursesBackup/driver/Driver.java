package studentCoursesBackup;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Running...");

		TreeBuilder tree = new TreeBuilder();
		boolean one = tree.insert(1234, "A");
		boolean two = tree.insert(1234, "B");
		boolean three = tree.insert(1235, "C");
		boolean four = tree.insert(1234, "A");
		boolean five = tree.insert(1237, "Z");
		
		System.out.println(String.valueOf(one));
		System.out.println(String.valueOf(two));
		System.out.println(String.valueOf(three));
		System.out.println(String.valueOf(four));
		System.out.println(String.valueOf(five));

		System.out.println(tree.toString());

		Node printthis = new Node(1234, "B");

		System.out.println(printthis.toString());

		System.exit(0);
	}
}
