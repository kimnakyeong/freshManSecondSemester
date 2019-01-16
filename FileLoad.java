package gameTyping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileLoad {
	public static void main(String[] args) throws IOException {
		FileReader fr;
		BufferedReader br;
		String str = "";
		try {
			fr = new FileReader("C:\\java\\word.txt");
			br = new BufferedReader(fr);
			String line;
			while((line = br.readLine())!=null) {
			str = str+line;}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer strToken = new StringTokenizer(str, " ");
		while(strToken.hasMoreTokens()) {
			list.add(strToken.nextToken());
		}
		
	}
}
