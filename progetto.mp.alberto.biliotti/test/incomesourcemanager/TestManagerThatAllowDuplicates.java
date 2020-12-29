package incomesourcemanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class TestManagerThatAllowDuplicates {
	
	private AbstractIncomeSourceManager manager;

	@Before
	public void instanceManager() {
		manager=new IncomeSourceManagerAllowsDuplicates();
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
	public void testAddDuplicatedIncomeSource() {
		LandProperty house= new LandProperty(200, 2000, "test");
		manager.addIncomeSource(house);
		assertThat(manager.addIncomeSource(house)).isTrue();
		assertThat(manager.getIncomeSources()).containsExactly(house, house);
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
		ArrayList<IncomeSource> iSources= new ArrayList<>();
		assertThat(manager.addIncomeSourceCollection(iSources)).isFalse();
		assertThat(manager.getIncomeSources()).isEmpty();
	}
	@Test
	public void testAddCollection() {
		LandProperty house= new LandProperty(200, 2000, "land test");
		GoldReserve goldReserve=new GoldReserve(100, "gold test");
		EuroCash euro=new EuroCash(2000, "eu test");
		ArrayList<IncomeSource> iSources= new ArrayList<>();
		iSources.add(house);
		iSources.add(goldReserve);
		iSources.add(euro);
		iSources.add(house);
		manager.addIncomeSourceCollection(iSources);
		assertThat(manager.getIncomeSources()).containsExactly(house, goldReserve, euro,house);
	}
	@Test
	public void testAddDuplicateCollection() {
		LandProperty house= new LandProperty(200, 2000, "land test");
		GoldReserve goldReserve=new GoldReserve(100, "gold test");
		EuroCash euro=new EuroCash(2000, "eu test");
		ArrayList<IncomeSource> iSources= new ArrayList<>();
		iSources.add(house);
		iSources.add(goldReserve);
		iSources.add(euro);
		manager.addIncomeSourceCollection(iSources);
		manager.addIncomeSourceCollection(iSources);
		assertThat(manager.getIncomeSources()).containsExactly(house, goldReserve, euro,house, goldReserve, euro);
	}
	@Test
	public void testRimuoviCollezioni() {
		LandProperty house= new LandProperty(200, 2000, "land test");
		GoldReserve goldReserve=new GoldReserve(100, "gold test");
		EuroCash euro=new EuroCash(2000, "eu test");
		ArrayList<IncomeSource> iSources= new ArrayList<>();
		iSources.add(house);
		iSources.add(goldReserve);
		iSources.add(euro);
		manager.getIncomeSources().addAll(iSources);
		iSources.remove(goldReserve);
		manager.removeIncomeSourceCollection(iSources);
		assertThat(manager.getIncomeSources()).containsExactly(goldReserve);
	}

}
