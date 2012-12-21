package com.octo.functional;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.BoundType;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

public class FunctionalProgrammingTest {

	private Function<String, String> toUpperCase = new Function<String, String>() {

		@Override
		public String apply(String thisIsToUpperCase) {
			return thisIsToUpperCase.toUpperCase();
		}
		
	};
	
	private Function<Integer, Integer> carre = new Function<Integer, Integer>() {

		@Override
		public Integer apply(Integer toCarre) {
			return toCarre * toCarre;
		}
	};
	
	private Predicate<Integer> superieurA100 = new Predicate<Integer>() {

		@Override
		public boolean apply(Integer isSuperieurACent) {
			return isSuperieurACent > 100;
		}
		
	};
	
	
	@Test
	public void shouldTransformEachElementOfAList() {
		List<String> nameToMakeUpperCase = Lists.newArrayList("mathieu","gandin");
 		List<String> result = FluentIterable.from(nameToMakeUpperCase).transform(toUpperCase).toImmutableList();
 		Assert.assertEquals("MATHIEU",result.get(0));
 		Assert.assertEquals("GANDIN", result.get(1));
	}
	
	@Test
	public void shouldSquareEachElementAndGetTheFirstThreeSuperiorOf100AndSum() {
		List<Integer> maList = Lists.newArrayList();
		
		
		for (int i = 1; i <= 1000; i++) {
			maList.add(i);
		}
		
		
		List<Integer> result = FluentIterable.from(maList)
			.transform(carre)
			.filter(superieurA100)
			.limit(3)
			.toImmutableList();
		
		int sum = 0;
		for (Integer integer : result) {
			sum+= integer;
		}
		Assert.assertEquals(434, sum);
	}

}