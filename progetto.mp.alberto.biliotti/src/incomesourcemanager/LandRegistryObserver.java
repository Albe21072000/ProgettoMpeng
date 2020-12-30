package incomesourcemanager;

import java.util.Iterator;

public class LandRegistryObserver implements IncomeSourceManagerObserver {

	private AbstractIncomeSourceManager manager;

	private int totalsquaremeters=0;

	public LandRegistryObserver(AbstractIncomeSourceManager manager) {
		this.manager = manager;
		manager.getIncomeSources().forEach((x)->x.accept(new LandPropertySquareMeterVisitor()));
	}

	AbstractIncomeSourceManager getManager() {
		return manager;
	}
	private class LandPropertySquareMeterVisitor implements IncomeSourceVisitor<Integer>{
		@Override
		public Integer visitLandProperty(LandProperty lp) {
			return totalsquaremeters+=lp.getSquareMeters();
		}

		@Override
		public Integer visitGoldReserve(GoldReserve au) {
			return null;
		}

		@Override
		public Integer visitEuroCash(EuroCash eur) {
			return null;
		}

	}

	int getTotalSquareMeters() {
		return totalsquaremeters;
	}

	@Override
	public void update() {
		Iterator<IncomeSource> iterator=manager.iterator();
		while(iterator.hasNext()) {
			iterator.next().accept(new LandPropertySquareMeterVisitor());
		}

	}

}
