package org.doInjava8.spliterators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.doInjava8.spliterators.custom.PersonSpliterator;
import org.doInjava8.spliterators.model.Person;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Path path = Paths.get("F:\\Git\\CoreJavaDemos\\Java8\\file\\peoples.txt");
        
        try(Stream<String> lines = Files.lines(path);){
        
        	Spliterator<String> lineSpliterator = lines.spliterator();
        	
        	Spliterator<Person> personSpliterator = new PersonSpliterator(lineSpliterator);
        	
        	Stream<Person> personsStream = StreamSupport.stream(personSpliterator, false);
        	
        	personsStream.forEach(System.out::println);
        
        }catch(IOException ioe) {
        	ioe.printStackTrace();
        }
    }
}
