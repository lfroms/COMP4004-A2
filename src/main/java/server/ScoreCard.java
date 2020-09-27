package server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class ScoreCard {
	private List<Score> scores = new ArrayList<Score>();

	public void addNewScore(Score score) {
		scores.add(score);
	}

	public List<Score> getScores() {
		return scores;
	}

	public List<Score> getScores(Integer playerId) {
		return scores.stream().filter(score -> score.getPlayerId() == playerId).collect(Collectors.toList());
	}
}
