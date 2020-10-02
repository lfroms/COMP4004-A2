package server;

final class Score {
	private Integer playerId;
	private Integer score;

	public Score(Integer playerId, Integer score) {
		this.playerId = playerId;
		this.score = score;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
