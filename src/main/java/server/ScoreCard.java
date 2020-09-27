package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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

	public Boolean hasWinner() {
		return getWinnerId().isPresent();
	}

	public Optional<Integer> getWinnerId() {
		Integer numberOfPlayers = scores.stream().filter(distinctByKey(p -> p.getPlayerId()))
				.collect(Collectors.toList()).size();

		for (int i = 0; i < numberOfPlayers; i++) {
			final Integer id = i + 1;

			Integer playerTotal = scores.stream().filter(s -> s.getPlayerId() == id).reduce(0,
					(accumulator, object) -> accumulator + object.getScore(), Integer::sum);

			if (playerTotal >= 6000) {
				return Optional.of(id);
			}
		}

		return Optional.empty();
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
