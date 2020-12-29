package incomesourcemanager;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestLandRegistryObserver {
	private AbstractIncomeSourceManager manager;
	private LandRegistryObserver obs;
	@Before
	public void instancemanager() {
		manager=new IncomeSourceManagerWithoutDuplicates();
		obs=new LandRegistryObserver(manager);
		manager.attachObserver(obs);
	}

	@Test
	public void testEmptyManager() {
	assertThat(obs.getTotalSquareMeters()).isEqualTo(0);
	assertThat(obs.getPatr()).isEqualTo(manager);
	}
	
	@Test
	public void testAddLandProperty() {
		manager.addIncomeSource(new LandProperty(200, 3000, "test"));
		assertThat(obs.getTotalSquareMeters()).isEqualTo(200);
	}
	@Test
	public void testAddVariousIncomeSources() {
		manager.addIncomeSource(new LandProperty(200, 3000, "test"));
		manager.addIncomeSource(new GoldReserve(200, "test"));
		manager.addIncomeSource(new EuroCash(200, "test"));
		assertThat(obs.getTotalSquareMeters()).isEqualTo(200);
	}
	@Test
	public void testAddCollectionIncomeSources() {
		LandProperty house= new LandProperty(200, 2000, "test");
		IncomeSource eur = new EuroCash(2000, "test");
		IncomeSource gold = new GoldReserve(1000, "Rtest");
		List<IncomeSource> incomeSources=new ArrayList<>();
		incomeSources.add(house);
		incomeSources.add(eur);
		incomeSources.add(gold);
		manager.addIncomeSourceCollection(incomeSources);
		assertThat(obs.getTotalSquareMeters()).isEqualTo(200);
	}
	@Test
	public void testRemoveIncomeSources() {
		LandProperty house= new LandProperty(200, 2000, "Casa");
		IncomeSource eur = new EuroCash(2000, "Contanti prova");
		manager.getIncomeSources().add(house);
		manager.getIncomeSources().add(eur);
		manager.removeIncomeSource(eur);
		assertThat(obs.getTotalSquareMeters()).isEqualTo(200);
	}


}
