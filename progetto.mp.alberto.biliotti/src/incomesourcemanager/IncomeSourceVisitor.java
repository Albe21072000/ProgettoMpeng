package incomesourcemanager;

public interface IncomeSourceVisitor<T> {
	
	T visitLandProperty(LandProperty im);
	
	T visitGoldReserve(GoldReserve au);
	
	T visitEuroCash(EuroCash eur);

}
