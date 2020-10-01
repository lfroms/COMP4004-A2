package model;

public final class Die {
	private DieFace face;
	private Boolean isHeld = false;
	private Boolean inTreasureChest = false;

	public Die() {
		face = DieFace.random();
	}

	public DieFace roll() {
		DieFace newFace = DieFace.random();
		face = newFace;
		return newFace;
	}

	public DieFace getFace() {
		return face;
	}

	public void setFace(DieFace face) {
		this.face = face;
	}

	public Boolean getIsHeld() {
		return isHeld;
	}

	public void setHeld(Boolean isHeld) {
		this.isHeld = isHeld;
	}

	public Boolean getInTreasureChest() {
		return inTreasureChest;
	}

	public void setInTreasureChest(Boolean inTreasureChest) {
		this.inTreasureChest = inTreasureChest;
	}
}
