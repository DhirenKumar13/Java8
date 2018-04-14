package org.doInJava8.NumericStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
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
		System.out.println("*************************************************");

		System.out.println("# of Words used by Shakesphere :" + shakesphereWords.size());

		System.out.println("# of scrabbles words are :" + scrabbleWords.size());

		Function<String, Integer> scores = word -> word.chars().map(letter -> scrabbleEnScores[letter - 'a']).sum();

		ToIntFunction<String> intScores = word -> word.chars().map(letter -> scrabbleEnScores[letter - 'a']).sum();
		
		System.out.println("*************************************************");

		System.out.println("Testing Our Functions by giving a sample word ...");

		System.out.println("Hello as a word scores using intScore " + intScores.applyAsInt("hello"));

		System.out.println("Hello as a word scores using score " + scores.apply("hello"));

		System.out.println("*************************************************");

		String bestWord = shakesphereWords.stream().max(Comparator.comparing(scores)).get();

		String bestWordAgain = shakesphereWords.stream().max(Comparator.comparingInt(intScores)).get();

		System.out.println("The best word using Comparator.comparing() is :" + bestWord);

		System.out.println("The best word using Comparator.comparingInt() is :" + bestWordAgain);

		System.out.println("*************************************************");

		System.out.println("*************************************************");

		String bestWordAfterFilter = shakesphereWords.stream().filter(word -> scrabbleWords.contains(word))
				.max(Comparator.comparing(scores)).get();

		String bestWordAgainAfterFilter = shakesphereWords.stream().filter(word -> scrabbleWords.contains(word))
				.max(Comparator.comparingInt(intScores)).get();

		System.out.println("The best word after filtering using Comparator.comparing() is :" + bestWordAfterFilter);

		System.out.println("The best word after filtering using Comparator.comparingInt() is :" + bestWordAgainAfterFilter);

		System.out.println("*************************************************");
		
		IntSummaryStatistics intSummaryStatistics =
				shakesphereWords.stream().filter(scrabbleWords::contains).mapToInt(intScores).summaryStatistics();
		
		System.out.println("Using IntSummaryStatistics the statistics are "+intSummaryStatistics);
		
		System.out.println("*************************************************");

	}

}
