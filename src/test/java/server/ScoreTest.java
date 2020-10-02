package server;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreTest {
	@Test
	public void testGetPlayerIdReturnsPlayerId() {
		Score score = new Score(1, 100);

		assertEquals(Integer.valueOf(1), score.getPlayerId());
	}

	@Test
	public void testGetScoreReturnsScore() {
		Score score = new Score(1, 100);

		assertEquals(Integer.valueOf(100), score.getScore());
	}

	@Test
	public void testSetScoreSetsScore() {
		Score score = new Score(1, 100);
		score.setScore(200);

		assertEquals(Integer.valueOf(200), score.getScore());
	}
}
