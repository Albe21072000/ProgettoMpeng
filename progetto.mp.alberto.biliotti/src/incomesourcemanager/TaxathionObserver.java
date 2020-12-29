package incomesourcemanager;

public class TaxathionObserver implements IncomeSourceManagerObserver {
	
	private double totalvalue;
	
	private AbstractIncomeSourceManager manager;
	
	private double lastIncome;

	public TaxathionObserver(AbstractIncomeSourceManager manager) {
		this.manager = manager;
		this.totalvalue=manager.getTotalValue();
	}

	@Override
	public void update() {
	if(manager.getTotalValue()>totalvalue)
		lastIncome=manager.getTotalValue()-totalvalue;
	totalvalue=manager.getTotalValue();
	}
	
	public double calculateFlatTax(double percentage) {
		return (percentage/100)*totalvalue;
	}
	
	public double calculateVATLastIncome(double percentage) {
		return lastIncome*(percentage/100);
		
	}

}
