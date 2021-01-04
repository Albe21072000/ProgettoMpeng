package incomesourcemanager;

public class DescriptionVisitor implements IncomeSourceVisitor<String> {

	@Override
	public String visitLandProperty(LandProperty landProperty) {
		return "LandProperty: "+landProperty.getName()+", that has an area of: "+landProperty.getSquareMeters()+"sm and has a total value of: "+landProperty.getTotalValue()+"€";
	}

	@Override
	public String visitGoldReserve(GoldReserve goldReserve) {
		return "Gold Reserve: "+goldReserve.getName()+", that wheigs: "+goldReserve.getGoldGrams()+"gr and has a total value of: : "+goldReserve.getTotalValue()+"€";
	}

	@Override
	public String visitEuroCash(EuroCash euroCash) {
		return "Euro cash reserve: "+euroCash.getName()+", that has a value of: "+euroCash.getTotalValue()+"€";
	}
}
