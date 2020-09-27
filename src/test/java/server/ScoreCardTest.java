package server;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ScoreCardTest {
	@Test
	public void testScoresCanBeAddedAndRetrieved() {
		ScoreCard scoreCard = new ScoreCard();
		Score firstScore = new Score(1, 100);

		scoreCard.addNewScore(firstScore);

		assertEquals(firstScore, scoreCard.getScores().get(0));
	}

	@Test
	public void testGetScoresFiltersScoresByPlayerId() {
		ScoreCard scoreCard = new ScoreCard();
		Score firstScore = new Score(1, 100);
		Score secondScore = new Score(1, 100);
		Score thirdScore = new Score(2, 100);

		scoreCard.addNewScore(firstScore);
		scoreCard.addNewScore(secondScore);
		scoreCard.addNewScore(thirdScore);

		List<Score> scores = scoreCard.getScores(1);

		scores.forEach(score -> {
			assertEquals(Integer.valueOf(1), score.getPlayerId());
		});
	}
}
