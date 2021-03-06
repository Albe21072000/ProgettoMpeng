package incomesourcemanager;

public class DataVisualizationObserverMock implements IncomeSourceManagerObserver {
	
	private AbstractIncomeSourceManager manager;
	
	private double lastIncomeSourceValue=0;
	
	private double totalvalue=0;

	DataVisualizationObserverMock(AbstractIncomeSourceManager manager) {
		this.manager = manager;
		totalvalue=manager.getTotalValue();
	}

	double getLastIncomeSourceValue() {
		return lastIncomeSourceValue;
	}

	double getTotalValue() {
		return totalvalue;
	}

	public AbstractIncomeSourceManager getManager() {
		return manager;
	}

	@Override
	public void update() {
		lastIncomeSourceValue=manager.getIncomeSources()
				.get(manager.getIncomeSources().size()-1)
				.getTotalValue();
		this.totalvalue = manager.getTotalValue();
	
	}

}
