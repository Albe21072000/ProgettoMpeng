package incomesourcemanager;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class TestCurrencyExchangeVisitor {

	@Test
	public void testLandPropertyVisitor() {
		IncomeSource house= new LandProperty(200, 2500, "test house");
		assertThat(house.accept(new CurrencyExcahngeVisitor(1.8))).isEqualTo(200*2500*1.8);
	}
	@Test
	public void testGoldReserveVisitor() {
		IncomeSource gold= new GoldReserve(200, "test gold");
		assertThat(gold.accept(new CurrencyExcahngeVisitor(1.8))).isEqualTo(200*50*1.8);
	}
	@Test
	public void testCashVisitor() {
		IncomeSource eur= new EuroCash(2000, "cash test");
		assertThat(eur.accept(new CurrencyExcahngeVisitor(1.8))).isEqualTo(2000*1.8);
	}

}
