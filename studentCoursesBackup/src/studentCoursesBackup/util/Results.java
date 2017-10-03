package studentCoursesBackup;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	private List<String> strings;

	/**
	 * @return list of strings
	 */
	public List<String> getStrings() {
		return strings;
	}

	public void storeNewResult(String s) {
		strings.add(s);
	}

	/**
	 * @return nothing
	 */
	public void setStrings(List<String> newStrings) {
		strings = newStrings;
	}

	public Results() {
		strings = new ArrayList<String>();
	}

	/**
	 * @return nothing
	 */
	public void writeToFile(String s) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(s, false));
			for(int i=0; i<strings.size(); i++) {
				bw.append(strings.get(i));
				bw.append("\n");
			}
			bw.close();
		}
		catch(IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
			System.exit(0);
		}	
	}

	/**
	 * @return nothing
	 */
	public void writeToStdout(String s) {
		for(int i=0; i<strings.size(); i++) 
			System.out.println(strings.get(i));
	}


}
