package dicionario;

public class Meaning {
	private String word;
	private String description;
	
	public Meaning(String word, String description) {
		this.word = word;
		this.description = description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean hasMeaning() {
		if(this.description != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return word + ";" + description;
	}
}
