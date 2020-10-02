package server;

import java.util.ArrayList;
import java.util.List;

import model.DieFace;
import model.FortuneCard;
import model.FortuneCardType;
import model.Turn;

final class TestTurnFactory {
	private final GameTestMode testMode;
	private final Integer sequence;

	public TestTurnFactory(GameTestMode testMode, Integer sequence) {
		this.testMode = testMode;
		this.sequence = sequence;
	}

	private final List<Turn> sequenceATurns = new ArrayList<Turn>() {
		private static final long serialVersionUID = 1L;

		{
			add(new Turn(new FortuneCard(FortuneCardType.SKULLS, 2),
					new DieFace[][] { { DieFace.SKULL, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT, DieFace.PARROT,
							DieFace.PARROT, DieFace.PARROT, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.PARROT, DieFace.PARROT,
					DieFace.PARROT, DieFace.MONKEY, DieFace.MONKEY, DieFace.SKULL, DieFace.SKULL, DieFace.SWORD } }));

			add(new Turn(new FortuneCard(FortuneCardType.CAPTAIN), new DieFace[][] { { DieFace.SWORD, DieFace.SWORD,
					DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD } }));
		}
	};

	private final List<Turn> sequenceBTurns = new ArrayList<Turn>() {
		private static final long serialVersionUID = 313869311888714695L;

		{
			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.SORCERESS), new DieFace[][] { { DieFace.SKULL, DieFace.PARROT,
					DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND, DieFace.PARROT, DieFace.COIN, DieFace.PARROT } }));

			add(new Turn(new FortuneCard(FortuneCardType.CAPTAIN),
					new DieFace[][] {
							{ DieFace.COIN, DieFace.PARROT, DieFace.COIN, DieFace.DIAMOND, DieFace.DIAMOND,
									DieFace.PARROT, DieFace.COIN, DieFace.PARROT },
							{ DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD, DieFace.SWORD,
									DieFace.SWORD, DieFace.SWORD } }));
		}
	};

	public Turn createTurn() {
		switch (testMode) {
		case SEQUENCE_A:
			return sequenceATurns.get(sequence);
		default:
			return sequenceBTurns.get(sequence);
		}
	}

}
