package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FortuneCardTest {
	@Test
	public void testFortuneCardInitializedWithDefaultType() {
		FortuneCard card = new FortuneCard();

		assertNotNull(card.getType());
	}

	@Test
	public void testFortuneCardCanBeInitializedWithCustomType() {
		FortuneCard card = new FortuneCard(FortuneCardType.DIAMOND);

		assertEquals(FortuneCardType.DIAMOND, card.getType());
	}
}
