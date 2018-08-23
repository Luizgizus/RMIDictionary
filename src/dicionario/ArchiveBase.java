package dicionario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchiveBase{
	private static RandomAccessFile menaingFile = null;
	private static RandomAccessFile indexFile = null;

	public ArchiveBase() throws FileNotFoundException{
		menaingFile = new RandomAccessFile("baseMeanings.txt", "rw");
		indexFile =	new RandomAccessFile("baseIdxWords.txt", "rw");
	}
	
	public static void addWord(String word, long positionBaseMeaning) throws IOException{
		menaingFile.seek(menaingFile.length());
	}
	
	public static void getPositionBaseMeaning(String word){
		
	}
	
	public static void removeWord(String word){
		
	}
	
	public static void addMeaning(String meaning){
		
	}
	
	public static void getMeaningByPosition(long positionBaseMeaning){
		
	}
}
