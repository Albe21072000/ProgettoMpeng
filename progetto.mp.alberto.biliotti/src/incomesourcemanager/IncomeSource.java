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

}
