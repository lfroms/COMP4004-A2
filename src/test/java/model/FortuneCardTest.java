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

	@Test
	public void testGetNumericalValueReturnsZeroForUnsupportedCards() {
		FortuneCard card = new FortuneCard(FortuneCardType.DIAMOND);

		assertEquals(Integer.valueOf(0), card.getNumericalValue());
	}

	@Test
	public void testGetNumericalValueReturnsNumericalValue() {
		FortuneCard card = new FortuneCard(FortuneCardType.DIAMOND, 5);

		assertEquals(Integer.valueOf(5), card.getNumericalValue());
	}
}
