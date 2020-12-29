package incomesourcemanager;

public class GoldReserve extends IncomeSource {
	
	private double goldgrams;
	
	private final static int goldvaluepergram=50;

	public GoldReserve(double grammioro, String name) {
		super(grammioro*goldvaluepergram, name);
		this.goldgrams=grammioro;
		
	}

	public double getGoldGrams() {
		return goldgrams;
	}


	@Override
	public <T> T accept(IncomeSourceVisitor<T> visitor) {
		return visitor.visitGoldReserve(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(goldgrams);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoldReserve other = (GoldReserve) obj;
		if (Double.doubleToLongBits(goldgrams) != Double.doubleToLongBits(other.goldgrams))
			return false;
		return true;
	}

}
