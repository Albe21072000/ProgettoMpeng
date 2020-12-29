package incomesourcemanager;

public final class LandProperty extends IncomeSource {
	
	private int squaremeters;
	private double valuepersquarem;

	public LandProperty(int squaremeters, double valuepersquarem, String name) {
		super(squaremeters*valuepersquarem, name);
		this.squaremeters=squaremeters;
		this.valuepersquarem=valuepersquarem;
	}
	public int getSquareMeters() {
		return squaremeters;
	}

	public double getValuePerSquareM() {
		return valuepersquarem;
	}


	@Override
	public <T> T accept(IncomeSourceVisitor<T> visitor) {
		return visitor.visitLandProperty(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + squaremeters;
		long temp;
		temp = Double.doubleToLongBits(valuepersquarem);
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
		LandProperty other = (LandProperty) obj;
		if (squaremeters != other.squaremeters)
			return false;
		if (Double.doubleToLongBits(valuepersquarem) != Double.doubleToLongBits(other.valuepersquarem))
			return false;
		return true;
	}


}
