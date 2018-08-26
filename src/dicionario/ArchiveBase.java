package dicionario;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchiveBase{	
	public static void addWord(String line) throws IOException{
        RandomAccessFile indexFile = new RandomAccessFile("baseIdxWords.txt", "rw");
        
		indexFile.seek(indexFile.length());
		indexFile.writeBytes(line + "\n");
		indexFile.close();
	}	
	public static long getPositionMeaning(String word) throws IOException{
        RandomAccessFile indexFile = new RandomAccessFile("baseIdxWords.txt", "rw");
        
		String line = indexFile.readLine();
		
		while(line != null) {
			String[] explodedLine = line.split(";");
			System.out.println(line);
			System.out.println(explodedLine[0].equalsIgnoreCase(word));
			System.out.println(explodedLine[2].equals("0"));
			System.out.println();
			if(explodedLine[0].equalsIgnoreCase(word) && explodedLine[2].equals("0")) {
				indexFile.close();
				return Long.parseLong(explodedLine[1]);
			}
			line = indexFile.readLine();
		}
		indexFile.close();
		return -1;
	} 
	
	public static String removeWord(String word) throws IOException{
        RandomAccessFile indexFile = new RandomAccessFile("baseIdxWords.txt", "rw");
        
		long postion = indexFile.getFilePointer();
		String line = indexFile.readLine();
		
		while(line != null) {
			String[] explodedLine = line.split(";");
			if(explodedLine[0].equalsIgnoreCase(word) && explodedLine[2].equals("0")) {
				indexFile.seek(postion);
				String deletedLine = explodedLine[0] + ";" + explodedLine[1] + ";1";
				indexFile.writeBytes(deletedLine);
				indexFile.close();
				return "Palavra excluida com sucesso";
			}
			postion = indexFile.getFilePointer();
			line = indexFile.readLine();
		}
		indexFile.close();
		return "Palavra não existe";
	}
	
	public static long addMeaning(String meaning) throws IOException{
        RandomAccessFile menaingFile = new RandomAccessFile("baseMeaningWords.txt", "rw");
        
		long position = menaingFile.length();
		String replacedMeaning = meaning.replaceAll("\n", ". ").replaceAll(";", ".");
		
		menaingFile.seek(position);
		menaingFile.writeBytes(replacedMeaning + "\n");
		menaingFile.close();
		
		return position;
	}
	
	public static String getMeaningByPosition(long positionBaseMeaning) throws IOException{
        RandomAccessFile menaingFile = new RandomAccessFile("baseMeaningWords.txt", "rw");
        String meaning = null;
		menaingFile.seek(positionBaseMeaning);
		meaning = menaingFile.readLine();;
		menaingFile.close();
		return meaning;
	}
}
