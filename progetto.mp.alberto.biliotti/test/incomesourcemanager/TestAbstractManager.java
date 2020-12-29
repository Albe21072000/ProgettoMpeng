package incomesourcemanager;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TestAbstractManager {
	private AbstractIncomeSourceManager manager;
	@Before
	public void instancemanager() {
		manager=new IncomeSourceManagerWithoutDuplicates();
	}

	@Test
	public void testEmpryManager() {
		assertThat(manager.isEmpty()).isTrue();
	}

	@Test
	public void testReceipt() {
		LandProperty house= new LandProperty(200, 2000, "test land");
		GoldReserve goldReserve=new GoldReserve(100, "test gold");
		EuroCash euro=new EuroCash(2000, "test eur");
		manager.getIncomeSources().add(house);
		manager.getIncomeSources().add(goldReserve);
		manager.getIncomeSources().add(euro);
		assertThat(manager.returnReceipt()).isEqualTo("Income Sources receipt: LandProperty: test land, that has an area of: 200sm and has a total value of: 400000.0€ Gold Reserve: test gold, that wheigs: 100.0gr and has a total value of: : 5000.0€ Euro cash reserve: test eur, that has a value of: 2000.0€");
	}
	@Test
	public void testIterator() {
		IncomeSource house = new LandProperty(200, 2000, "test a");
		manager.getIncomeSources().add(house);
		IncomeSource eur = new EuroCash(2000, "Test B");
		manager.getIncomeSources().add(eur);
		IncomeSource gold = new GoldReserve(2000, "test C");
		manager.getIncomeSources().add(gold);
		assertThat(manager.iterator())
		.toIterable()
		.containsExactly(house,eur,gold);
	}
	@Test
	public void testEmptyIterator() {
		assertThat(manager.iterator()).toIterable().isEmpty();
		assertThat(manager.iterator().hasNext())
		.isFalse();
		try {
			manager.iterator().next();
		} catch (UnsupportedOperationException e) {
			assertThat(e).hasMessage("Iterator Terminated!");
		}
	}

}
