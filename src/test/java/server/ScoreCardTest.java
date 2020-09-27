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

	@Test
	public void testGetCurrentScoreCalculatesTotalRollingScore() {
		ScoreCard scoreCard = new ScoreCard();
		Score firstScore = new Score(1, 100);
		Score secondScore = new Score(1, 100);

		scoreCard.addNewScore(firstScore);
		scoreCard.addNewScore(secondScore);

		Integer score = scoreCard.getCurrentScore(1);

		assertEquals(Integer.valueOf(200), score);
	}

	@Test
	public void testHasWinnerReturnsTrueWhenPlayerHasAtLeast6000() {
		ScoreCard scoreCard = new ScoreCard();
		Score firstScore = new Score(1, 100);
		Score secondScore = new Score(1, 100);
		Score thirdScore = new Score(2, 100);
		Score fourthScore = new Score(2, 5900);

		scoreCard.addNewScore(firstScore);
		scoreCard.addNewScore(secondScore);
		scoreCard.addNewScore(thirdScore);
		scoreCard.addNewScore(fourthScore);

		assertTrue(scoreCard.hasWinner());
	}

	@Test
	public void testHasWinnerReturnsFalseWhenNoPlayerHasAbove6000() {
		ScoreCard scoreCard = new ScoreCard();
		Score firstScore = new Score(1, 100);
		Score secondScore = new Score(1, 100);
		Score thirdScore = new Score(2, 100);
		Score fourthScore = new Score(2, 5200);

		scoreCard.addNewScore(firstScore);
		scoreCard.addNewScore(secondScore);
		scoreCard.addNewScore(thirdScore);
		scoreCard.addNewScore(fourthScore);

		assertFalse(scoreCard.hasWinner());
	}

	@Test
	public void testGetWinnerIdReturnsWinnerId() {
		ScoreCard scoreCard = new ScoreCard();
		scoreCard.addNewScore(new Score(1, 6000));
		scoreCard.addNewScore(new Score(2, 100));

		assertEquals(Integer.valueOf(1), scoreCard.getWinnerId().get());

		ScoreCard secondScoreCard = new ScoreCard();
		secondScoreCard.addNewScore(new Score(1, 100));
		secondScoreCard.addNewScore(new Score(2, 6000));

		assertEquals(Integer.valueOf(2), secondScoreCard.getWinnerId().get());
	}
}
