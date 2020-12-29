package incomesourcemanager;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;


import static org.assertj.core.api.Assertions.*;
public class TestManagerWithoutDuplicates {
	
	private AbstractIncomeSourceManager manager;
	
	@Before
	public void instancemanager() {
		manager=new IncomeSourceManagerWithoutDuplicates();
	}

	@Test
	public void testAddIncomeSource() {
		LandProperty house= new LandProperty(200, 2000, "test");
		manager.addIncomeSource(house);
		assertThat(manager
				.getIncomeSources().
				get(0))
			    .isSameAs(house);
	}
	@Test
	public void testDuplicatedIncomeSource() {
		LandProperty house= new LandProperty(200, 2000, "test");
		manager.addIncomeSource(house);
		assertThat(manager.addIncomeSource(house)).isFalse();
		assertThat(manager.getIncomeSources()).containsExactly(house);
	}
	@Test
	public void testRemoveIncomeSource() {
		LandProperty house= new LandProperty(200, 2000, "test");
		manager.getIncomeSources().add(house);
		manager.removeIncomeSource(house);
		assertThat(manager.getIncomeSources()).isEmpty();
	}
	@Test
	public void testAddEmptyCollection() {
		ArrayList<IncomeSource> incomeSources= new ArrayList<>();
		assertThat(manager.addIncomeSourceCollection(incomeSources)).isFalse();
		assertThat(manager.getIncomeSources()).isEmpty();
	}
	@Test
	public void testAddCollection() {
		LandProperty house= new LandProperty(200, 2000, "test land");
		GoldReserve gold=new GoldReserve(100, "test gold");
		EuroCash euro=new EuroCash(2000, "test euro");
		ArrayList<IncomeSource> incomeSources= new ArrayList<>();
		incomeSources.add(house);
		incomeSources.add(gold);
		incomeSources.add(euro);
		manager.addIncomeSourceCollection(incomeSources);
		assertThat(manager.getIncomeSources()).containsExactly(house, gold, euro);
	}
	@Test
	public void testAddDuplicateCollection() {
		LandProperty house= new LandProperty(200, 2000, "test land");
		GoldReserve gold=new GoldReserve(100, "test gold");
		EuroCash euro=new EuroCash(2000, "test euro");
		ArrayList<IncomeSource> incomeSources= new ArrayList<>();
		incomeSources.add(house);
		incomeSources.add(gold);
		incomeSources.add(euro);
		incomeSources.add(house);
		manager.addIncomeSourceCollection(incomeSources);
		assertThat(manager.addIncomeSourceCollection(incomeSources)).isFalse();
		assertThat(manager.getIncomeSources()).containsExactly(house, gold, euro);
	}
	@Test
	public void testRemoveCollection() {
		LandProperty house= new LandProperty(200, 2000, "prova");
		GoldReserve gold=new GoldReserve(100, "provaoro");
		EuroCash euro=new EuroCash(2000, "provaeu");
		ArrayList<IncomeSource> incomeSources= new ArrayList<>();
		incomeSources.add(house);
		incomeSources.add(gold);
		incomeSources.add(euro);
		manager.getIncomeSources().addAll(incomeSources);
		incomeSources.remove(gold);
		manager.removeIncomeSourceCollection(incomeSources);
		assertThat(manager.getIncomeSources()).containsExactly(gold);
	}

}
