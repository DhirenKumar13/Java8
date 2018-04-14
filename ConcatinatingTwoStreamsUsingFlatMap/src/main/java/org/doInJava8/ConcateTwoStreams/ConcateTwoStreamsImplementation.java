package org.doInJava8.ConcateTwoStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ConcateTwoStreamsImplementation {

	public static void main(String[] args) throws IOException {

		Stream<String> stream1 = Files.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\filesplitter\\file00.txt"));
		Stream<String> stream2 = Files.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\filesplitter\\file01.txt"));
		Stream<String> stream3 = Files.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\filesplitter\\file02.txt"));
		Stream<String> stream4 = Files.lines(Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\filesplitter\\file03.txt"));

		Stream<Stream<String>> streamOfStreams = Stream.of(stream1, stream2, stream3, stream4);

		Stream<String> streamOfLines = streamOfStreams.flatMap(Function.identity());

		// Stream<String> streamOfLinesAgain = streamOfStreamsAgain.flatMap(stream ->
		// stream);//same as above line

		Function<String, Stream<String>> lineSplitterBySpace = line -> Pattern.compile(" ").splitAsStream(line);

		Stream<String> streamOfWords = streamOfLines.flatMap(lineSplitterBySpace).map(word -> word.toLowerCase())
				.filter(word -> word.contains("d")).filter(word -> word.length() >= 4).distinct();

		System.out.println(streamOfWords.count());//3737

	}

}
