package incomesourcemanager;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TestTaxathionObserver {
	private AbstractIncomeSourceManager manager;
	private TaxathionObserver obs;
	@Before
	public void instancemanager() {
		manager=new IncomeSourceManagerWithoutDuplicates();
		obs=new TaxathionObserver(manager);
		manager.attachObserver(obs);
	}

	@Test
	public void testEmptyManager() {
		assertThat(obs.calculateVATLastIncome(20)).isEqualTo(0);
		assertThat(obs.calculateFlatTax(20)).isEqualTo(0);
	}
	@Test
	public void testVatLAstIncome() {
		manager.addIncomeSource(new LandProperty(200, 1000, "test"));
		assertThat(obs.calculateVATLastIncome(10)).isEqualTo(20000);
		manager.addIncomeSource(new GoldReserve(200, "test"));
		assertThat(obs.calculateVATLastIncome(10)).isEqualTo(1000);
		manager.addIncomeSource(new EuroCash(2000, "test"));
		assertThat(obs.calculateVATLastIncome(10)).isEqualTo(200);
	}
	@Test
	public void testFlatTax() {
		manager.addIncomeSource(new LandProperty(200, 1000, "test"));
		manager.addIncomeSource(new GoldReserve(200, "test"));
		manager.addIncomeSource(new EuroCash(2000, "test"));
		assertThat(obs.calculateFlatTax(10)).isEqualTo(21200);
	}

}
