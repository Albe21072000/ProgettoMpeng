package incomesourcemanager;

public class GoldReserve extends IncomeSource {
	
	private double goldGrams;
	
	private final static int goldValuePerGram=50;

	public GoldReserve(double goldGrams, String name) {
		super(goldGrams*goldValuePerGram, name);
		this.goldGrams=goldGrams;
		
	}

	public double getGoldGrams() {
		return goldGrams;
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
		temp = Double.doubleToLongBits(goldGrams);
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
		if (Double.doubleToLongBits(goldGrams) != Double.doubleToLongBits(other.goldGrams))
			return false;
		return true;
	}

}
