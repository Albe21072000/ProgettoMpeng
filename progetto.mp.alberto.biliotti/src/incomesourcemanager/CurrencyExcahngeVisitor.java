package incomesourcemanager;

public final class CurrencyExcahngeVisitor implements IncomeSourceVisitor<Double>{
	
	private double exchangeRate;

	public CurrencyExcahngeVisitor(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public Double visitLandProperty(LandProperty landProperty) {
		
		return convert(landProperty.getTotalValue());
	}

	@Override
	public Double visitGoldReserve(GoldReserve goldReserve) {
		return convert(goldReserve.getTotalValue());
	}

	@Override
	public Double visitEuroCash(EuroCash euroCash) {
		return euroCash.getTotalValue()*exchangeRate;
	}
	private double convert(double euro) {
		return euro*exchangeRate;
		
	}

}
