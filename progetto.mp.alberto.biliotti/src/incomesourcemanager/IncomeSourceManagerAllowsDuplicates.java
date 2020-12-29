package incomesourcemanager;

public class IncomeSourceManagerAllowsDuplicates extends AbstractIncomeSourceManager {

	@Override
	public Boolean addIncomeSource(IncomeSource incomeSource) {
		boolean added= super.getIncomeSources().add(incomeSource);
		super.notifyObservers();
		return added;
	}

	@Override
	public Boolean removeIncomeSource(IncomeSource incomeSource) {
		Boolean done=super.getIncomeSources().remove(incomeSource);
		if(done) {
			super.notifyObservers();
		}
		return done;
	}

}
