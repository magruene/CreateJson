package newJson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonStruc {

	static String filenameRead;
	static String filenameWrite;

	public static void main(String[] args) {
		 filenameRead = args[0];
		 filenameWrite = args[1];
		doIt(filenameRead, filenameWrite);
	}

	public static void doIt(String filenameRead, String filenameWrite) {

		try {
			FileReader fr = new FileReader(filenameRead);
			String line = "";
			BufferedReader reader = new BufferedReader(fr);
			StringBuilder outputJson = new StringBuilder();
			outputJson.append("{");
			while (line != null) {
				line = reader.readLine();
				if (line != null) {
					//Standard Construct
					String[] singleWord = line.split("\\,\\s");
					outputJson.append("\n\t\"" + singleWord[0] + "\":{\n");
					outputJson.append("\t\t\"en\":{\n");
					outputJson.append("\t\t\t\"index\":[\n");
					for (int i = 0; i < singleWord.length; i++) {
						//Every word(Synonym, tense) is added to the String-Construct
						if (i == singleWord.length - 1) {
							outputJson.append("\t\t\t\t\"" + singleWord[i] + "\"\n");
						}
						else
							outputJson.append("\t\t\t\t\"" + singleWord[i] + "\",\n");
						
					}
					//finishing Blow =)
					outputJson.append("\t\t\t],\n");
					outputJson.append("\t\t\t\"attribution\":\"Creative Commons, author: Francis Dierick\"\n");
					reader.mark(10000);
					line= reader.readLine();
					if(line==null)
					{
						outputJson.append("\t\t}\n\t}");
					}
					else
						outputJson.append("\t\t}\n\t},");
					reader.reset();
				}
			}
			outputJson.append("\n}");
				try {
				FileWriter writeIt = new FileWriter(filenameWrite);
				writeIt.append(outputJson.toString());
				writeIt.flush();
				writeIt.close();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
