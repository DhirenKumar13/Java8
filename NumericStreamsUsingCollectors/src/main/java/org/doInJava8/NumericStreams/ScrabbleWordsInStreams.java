package org.doInJava8.NumericStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScrabbleWordsInStreams {

	public static void main(String[] args) throws IOException {

		Set<String> shakesphereWords = Files
				.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\scrabblewords\\shakespherewords.txt"))
				.map(words -> words.toLowerCase()).collect(Collectors.toSet());

		Set<String> scrabbleWords = Files.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\scrabblewords\\ospd.txt"))
				.map(words -> words.toLowerCase()).collect(Collectors.toSet());

		final int[] scrabbleEnScores = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
				10 };

		Function<String, Integer> scores = word -> word.chars().map(letter -> scrabbleEnScores[letter - 'a']).sum();

		Map<Integer, List<String>> histogram = 
				shakesphereWords.stream().filter(scrabbleWords::contains).collect(
						Collectors.groupingBy(scores)
				);
		
		System.out.println("Least 5 scored words ...");
		
		histogram.entrySet().stream().sorted(
					Comparator.comparing(entry -> entry.getKey())
				).limit(5).forEach(entry -> {
					System.out.println(entry.getKey() + ":" + entry.getValue());
				});
		
		System.out.println("Highest 5 scored words ...");
		
		histogram.entrySet().stream().sorted(
				Comparator.comparing(entry -> - entry.getKey())
			).limit(5).forEach(entry -> {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			});
		
	}

}
