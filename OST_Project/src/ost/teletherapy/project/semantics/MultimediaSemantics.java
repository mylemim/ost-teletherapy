package ost.teletherapy.project.semantics;

import java.util.ArrayList;
import java.util.List;

public class MultimediaSemantics {

	private int id;
	private MultimediaType type;
	private String pathToMedia;
	private List<SemanticMeaning> semantics;

	public MultimediaSemantics(String pathToMedia, MultimediaType type) {
		this.pathToMedia = pathToMedia;
		this.semantics = new ArrayList<>();
		this.type = type;
		this.id = -1;
	}

	public MultimediaSemantics(int id, String pathToMedia, MultimediaType type) {
		this.pathToMedia = pathToMedia;
		this.semantics = new ArrayList<>();
		this.type = type;
		this.id = id;
	}

	// TODO add a constructor without the type, so it can be automatically added

	public MultimediaType getType() {
		return type;
	}

	public String getPathToMedia() {
		return pathToMedia;
	}

	public List<SemanticMeaning> getSemantics() {
		return semantics;
	}

	public void addSemantics(SemanticMeaning semanticMeaning) {
		this.semantics.add(semanticMeaning);
	}

	public int getId() {
		return id;
	}

	public void removeSemantics(SemanticMeaning semanticMeaning) {
		if (this.containsSemantic(semanticMeaning)) {
			semantics.remove(semanticMeaning);
		}
	}

	public void removeSemantics(String semanticMeaningString) {
		removeSemantics(new SemanticMeaning(semanticMeaningString));
	}

	public boolean containsSemantic(SemanticMeaning semanticMeaning) {
		return semantics.contains(semanticMeaning);
	}

	@Override
	public boolean equals(Object other) {
		MultimediaSemantics otherSemantics = (MultimediaSemantics) other;

		if (this.id == otherSemantics.getId()
				&& this.type.equals(otherSemantics.type)) {
			return true;
		}
		return false;

	}
}
