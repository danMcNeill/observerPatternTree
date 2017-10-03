-----Daniel McNeill README for assign 2 -----


MUST BE IN daniel_mcneill_assign_2/studentCoursesBackup directory for these commands:



## to clean:
	ant -buildfile src/build.xml clean
## to compile:
	ant -buildfile src/build.xml all
## to run:
	ant -buildfile src/build.xml run -Darg0=(input file) -Darg1=(delete file) -Darg2=(output1 file) -Darg3=(output2 file) -Darg4=(output3 file)



"I have done this assignment completely on my own. I have not copied it, nor have I given my solution to anyone else. I understand that if I am involved in plagiarism or cheating I will have to sign an official form that I have cheated and that this form will be stored in my official university record. I also understand that I will recieve a grade of 0 for the involved assignment for my first offense and that I will receive a grade of F for the course for any additional offense.
Date: 10/3/17 "


OBSERVER PATTERN EXPLANATION:
	When the nodes are created, two clones are also created. The original registers the clones as observers. Then, when some courses are deleted later from the original, the original calls notifyAll with the deleted course string as a parameter. This notifyAll method then calls update method on all of the node's observers. This update method removes from the clones the same course that was deleted from the original node.

DATA STRUCTURES:
	binary search tree: I chose to use a binary search tree because the input is supposed to be randomly generated. Therefore, we can expect the tree to be reasonably balanced. Therefore, the worst case look-up time is only O(log n) which is very low. If the input was not supposed to be random my tree probably would have been different.
