package incomesourcemanager;

public final class IncomeSourceManagerWithoutDuplicates extends AbstractIncomeSourceManager {

	@Override
	public Boolean addIncomeSource(IncomeSource incomesource) {
		if(super.getIncomeSources().contains(incomesource))
			return false;
		boolean aggiunto= super.getIncomeSources().add(incomesource);
		super.notifyObservers();
		return aggiunto;
	}

	@Override
	public Boolean removeIncomeSource(IncomeSource incomesource) {
		Boolean done=super.getIncomeSources().remove(incomesource);
		if(done) {
			super.notifyObservers();
		}
		return done;
	}


}
