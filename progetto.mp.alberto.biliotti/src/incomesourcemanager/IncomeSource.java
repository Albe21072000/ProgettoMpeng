package incomesourcemanager;

public abstract class IncomeSource {
	
	private double totalvalue;
	
	private String name;

	public IncomeSource(double totalvalue, String name) {
		this.totalvalue = totalvalue;
		this.name=name;
	}
	
	
	public double getTotalValue() {
		return totalvalue;
	}


	public String getName() {
		return name;
	}


	public abstract <T>  T accept(IncomeSourceVisitor<T> visitor);


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalvalue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncomeSource other = (IncomeSource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(totalvalue) != Double.doubleToLongBits(other.totalvalue))
			return false;
		return true;
	}

}
