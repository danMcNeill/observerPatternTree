package studentCoursesBackup;

import java.util.List;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {

		if(args.length != 5) {
			System.out.println("Must have five arguments. Exiting.");
			System.exit(0);
		}

		if(args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}") || args[3].equals("${arg3}") || args[4].equals("${arg4}")) {
		        System.err.println("Must specify all arguments: input.txt delete.txt output1.txt output2.txt output3.txt");
			System.exit(0);
		}		
	

		TreeBuilder tree = new TreeBuilder();

		tree.insertInputs("input1.txt");

		tree.deleteInputs("delete1.txt");

		Results orig = new Results();

		Results clone1 = new Results();

		Results clone2 = new Results();

		tree.printNodes(orig, 0);
		tree.printNodes(clone1, 1);
		tree.printNodes(clone2, 2);

		orig.writeToFile(args[2]);
		clone1.writeToFile(args[3]);
		clone2.writeToFile(args[4]);
		
		System.exit(0);
	}
}
