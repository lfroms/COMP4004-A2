package model;

import java.util.Optional;

public final class Die {
	private Optional<DieFace> face = Optional.empty();
	private Boolean isHeld = false;

	public DieFace roll() {
		DieFace newFace = DieFace.random();
		face = Optional.of(newFace);
		return newFace;
	}

	public Optional<DieFace> getFace() {
		return face;
	}

	public void setFace(DieFace face) {
		this.face = Optional.of(face);
	}

	public Boolean getIsHeld() {
		return isHeld;
	}

	public void setHeld(Boolean isHeld) {
		this.isHeld = isHeld;
	}
}
