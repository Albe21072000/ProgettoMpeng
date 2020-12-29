package incomesourcemanager;

public final class EuroCash extends IncomeSource  {



	public EuroCash(double totalvalue, String name) {
		super(totalvalue, name);
	}


	@Override
	public <T> T accept(IncomeSourceVisitor<T> visitor) {
		return visitor.visitEuroCash(this);
	}


	@Override
	public int hashCode() {
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
