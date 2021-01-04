package incomesourcemanager;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TestDataVisualizationObserverMock {
	private AbstractIncomeSourceManager manager;
	private DataVisualizationObserverMock observer;
	@Before
	public void instanceManager() {
		manager=new IncomeSourceManagerWithoutDuplicates();
		observer=new DataVisualizationObserverMock(manager);
		manager.attachObserver(observer);
	}

	@Test
	public void testAssigmentObserverEmpty() {
		assertThat(observer.getTotalValue()).isEqualTo(0);
		assertThat(observer.getManager()).isEqualTo(manager);
	}
	@Test
	public void testObserverAddIncomeSource() {
		manager.addIncomeSource(new LandProperty(200, 2000, "house test"));
		manager.addIncomeSource(new EuroCash(2000, "eur test"));
		assertThat(observer.getLastIncomeSourceValue()).isEqualTo(2000);
		assertThat(observer.getTotalValue()).isEqualTo(200*2000+2000);
	}
	@Test
	public void testObserverAddCollection() {
		LandProperty house= new LandProperty(200, 2000, "house test");
		IncomeSource eur = new EuroCash(2000, "eur test");
		IncomeSource gold = new GoldReserve(1000, "gold");
		List<IncomeSource> incomeSources=new ArrayList<>();
		incomeSources.add(house);
		incomeSources.add(eur);
		incomeSources.add(gold);
		manager.addIncomeSourceCollection(incomeSources);
		assertThat(observer.getLastIncomeSourceValue()).isEqualTo(50000);
		assertThat(observer.getTotalValue()).isEqualTo(400000+2000+50000);
	}
	@Test
	public void testObserverRemoveIncomeSource() {
		LandProperty house= new LandProperty(200, 2000, "house test");
		IncomeSource eur = new EuroCash(2000, "cash test");
		IncomeSource gold = new GoldReserve(1000, "test gold");
		manager.getIncomeSources().add(house);
		manager.getIncomeSources().add(eur);
		manager.getIncomeSources().add(gold);
		manager.removeIncomeSource(house);
		assertThat(observer.getTotalValue()).isEqualTo(52000);
		assertThat(observer.getLastIncomeSourceValue()).isEqualTo(50000);
	}

}
