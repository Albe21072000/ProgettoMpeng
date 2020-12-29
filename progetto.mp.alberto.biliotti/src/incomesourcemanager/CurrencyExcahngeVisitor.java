package incomesourcemanager;

public final class CurrencyExcahngeVisitor implements IncomeSourceVisitor<Double>{
	
	private double exchangerate;

	public CurrencyExcahngeVisitor(double exchangerate) {
		this.exchangerate = exchangerate;
	}

	@Override
	public Double visitLandProperty(LandProperty lp) {
		
		return convert(lp.getTotalValue());
	}

	@Override
	public Double visitGoldReserve(GoldReserve au) {
		return convert(au.getTotalValue());
	}

	@Override
	public Double visitEuroCash(EuroCash eur) {
		return eur.getTotalValue()*exchangerate;
	}
	private double convert(double euro) {
		return euro*exchangerate;
		
	}

}
