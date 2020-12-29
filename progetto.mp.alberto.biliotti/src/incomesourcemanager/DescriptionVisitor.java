package incomesourcemanager;

public class DescriptionVisitor implements IncomeSourceVisitor<String> {

	@Override
	public String visitLandProperty(LandProperty im) {
		return "LandProperty: "+im.getName()+", that has an area of: "+im.getSquareMeters()+"sm and has a total value of: "+im.getTotalValue()+"€";
	}

	@Override
	public String visitGoldReserve(GoldReserve au) {
		return "Gold Reserve: "+au.getName()+", that wheigs: "+au.getGoldGrams()+"gr and has a total value of: : "+au.getTotalValue()+"€";
	}

	@Override
	public String visitEuroCash(EuroCash eur) {
		return "Euro cash reserve: "+eur.getName()+", that has a value of: "+eur.getTotalValue()+"€";
	}
}
