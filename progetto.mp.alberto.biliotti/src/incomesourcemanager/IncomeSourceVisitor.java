package incomesourcemanager;

public interface IncomeSourceVisitor<T> {
	
	T visitLandProperty(LandProperty lp);
	
	T visitGoldReserve(GoldReserve au);
	
	T visitEuroCash(EuroCash eur);

}
