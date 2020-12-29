package incomesourcemanager;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class TestDescriptionVisitor {
	

	@Test
	public void testLandPropertyDescription() {
		IncomeSource house= new LandProperty(200, 2500, "test house");
		assertThat(house.accept(new DescriptionVisitor())).isEqualTo("LandProperty: test house, that has an area of: 200sm and has a total value of: 500000.0€");
	}
	@Test
	public void testGoldReserveVisitor() {
		IncomeSource gold= new GoldReserve(200, "test gold");
		assertThat(gold.accept(new DescriptionVisitor())).isEqualTo("Gold Reserve: test gold, that wheigs: 200.0gr and has a total value of: : 10000.0€");
	}
	@Test
	public void testCashVisitor() {
		IncomeSource eur= new EuroCash(2000, "cash test");
		assertThat(eur.accept(new DescriptionVisitor())).isEqualTo("Euro cash reserve: cash test, that has a value of: 2000.0€");
	}
	
}
