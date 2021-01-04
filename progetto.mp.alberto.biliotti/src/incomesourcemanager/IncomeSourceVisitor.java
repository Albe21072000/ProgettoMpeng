package incomesourcemanager;

public interface IncomeSourceVisitor<T> {
	
	T visitLandProperty(LandProperty landProperty);
	
	T visitGoldReserve(GoldReserve goldReserve);
	
	T visitEuroCash(EuroCash euroCash);

}
