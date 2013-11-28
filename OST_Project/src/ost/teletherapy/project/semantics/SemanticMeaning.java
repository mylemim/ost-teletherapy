package ost.teletherapy.project.semantics;

public class SemanticMeaning {

	private int id;

	private String semanticMeaning;

	public SemanticMeaning(String newMeaning) {
		this.semanticMeaning = newMeaning;
		this.id = -1;
	}

	public SemanticMeaning(int id, String newMeaning) {
		this.semanticMeaning = newMeaning;
		this.id = id;
	}

	public String getSemanticMeaning() {
		return semanticMeaning;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object other) {
		SemanticMeaning otherMeaning = (SemanticMeaning) other;

		if (this.id == otherMeaning.getId()
				&& this.semanticMeaning.equals(otherMeaning
						.getSemanticMeaning())) {
			return true;
		}
		return false;

	}
}
