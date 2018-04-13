package org.doInjava8.spliterators.custom;

import java.util.Spliterator;
import java.util.function.Consumer;

import org.doInjava8.spliterators.model.Person;

public class PersonSpliterator implements Spliterator<Person> {
	
	private final Spliterator<String> linespliterator;
	private String name;
	private Integer age;
	private String city;

	public PersonSpliterator(Spliterator<String> lines) {
		this.linespliterator = lines;
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super Person> action) {
		if(this.linespliterator.tryAdvance(line -> this.name = line) &&
		   this.linespliterator.tryAdvance(line -> this.age = Integer.valueOf(line)) &&
		   this.linespliterator.tryAdvance(line -> this.city = line)) {
			Person person = new Person(name,age,city);
			action.accept(person);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int characteristics() {
		//Just returning the default characteristics ..
		return linespliterator.characteristics();
	}

	@Override
	public long estimateSize() {
		//As in our file 3 lines contains informations about one person
		//So divide total size with 3 to get each person's info
		return linespliterator.estimateSize() / 3 ;
	}

	@Override
	public Spliterator<Person> trySplit() {
		//We don't need parallel stream hence returning null
		return null;
	}
	
	
}
