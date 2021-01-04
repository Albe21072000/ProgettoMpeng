package incomesourcemanager;

import java.util.Iterator;

public class LandRegistryObserver implements IncomeSourceManagerObserver {

	private AbstractIncomeSourceManager manager;

	private int totalSquareMeters=0;

	public LandRegistryObserver(AbstractIncomeSourceManager manager) {
		this.manager = manager;
		manager.getIncomeSources().forEach((incomeSource)->incomeSource.accept(new LandPropertySquareMeterVisitor()));
	}

	AbstractIncomeSourceManager getManager() {
		return manager;
	}
	private class LandPropertySquareMeterVisitor implements IncomeSourceVisitor<Integer>{
		@Override
		public Integer visitLandProperty(LandProperty landProperty) {
			return totalSquareMeters+=landProperty.getSquareMeters();
		}

		@Override
		public Integer visitGoldReserve(GoldReserve goldReserve) {
			return null;
		}

		@Override
		public Integer visitEuroCash(EuroCash euroCash) {
			return null;
		}

	}

	int getTotalSquareMeters() {
		return totalSquareMeters;
	}

	@Override
	public void update() {
		Iterator<IncomeSource> iterator=manager.iterator();
		while(iterator.hasNext()) {
			iterator.next().accept(new LandPropertySquareMeterVisitor());
		}

	}

}
