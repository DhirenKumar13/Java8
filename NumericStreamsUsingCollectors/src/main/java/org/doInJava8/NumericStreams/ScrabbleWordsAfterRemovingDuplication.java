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

public class ScrabbleWordsAfterRemovingDuplication {

	public static void main(String[] args) throws IOException {

		Set<String> shakesphereWords = Files
				.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\scrabblewords\\shakespherewords.txt"))
				.map(words -> words.toLowerCase()).collect(Collectors.toSet());

		Set<String> scrabbleWords = Files.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\scrabblewords\\ospd.txt"))
				.map(words -> words.toLowerCase()).collect(Collectors.toSet());

		final int[] scrabbleEnScores = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
				10 };
		
		final int[] letterDistribution = {9 ,2 ,2 ,1 ,12 ,2 ,3 ,2 ,9 ,1 ,1 ,4 ,2 ,6 ,8 ,2 ,1 ,6 ,4 ,6 ,4 ,2 ,2 ,1 ,2 ,1 };
		
		Function<String,Map<Integer,Long>> histoword = 
				word -> word.chars().boxed().collect(
							Collectors.groupingBy(letter -> letter,
									Collectors.counting()
							)
						);
				
		Function<String,Long> noOfBlanks = 
				word -> histoword.apply(word).entrySet().stream().mapToLong(
							entry -> 
							Long.max((entry.getValue() - (long)letterDistribution[entry.getKey() - 'a']),0l)
						).sum();
				
		System.out.println("# of blanks :"+noOfBlanks.apply("DddddHhIiRrEeNn".toLowerCase())); // 4
				
		Function<String,Integer> scores = 
				word -> histoword.apply(word).entrySet().stream().mapToInt(
							entry -> 
								scrabbleEnScores[entry.getKey() - 'a'] *
								Integer.min(entry.getValue().intValue()
										, letterDistribution[entry.getKey() - 'a']
								)
						).sum();
			
		System.out.println("Score is "+scores.apply("whizzing"));
		
	}

}
