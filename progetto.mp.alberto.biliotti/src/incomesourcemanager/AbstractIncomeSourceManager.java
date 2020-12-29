package incomesourcemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractIncomeSourceManager {
	private ArrayList<IncomeSource> incomeSources= new ArrayList<IncomeSource>();
	private ArrayList<IncomeSourceManagerObserver> observers= new ArrayList<>();

	ArrayList<IncomeSource> getIncomeSources() {
		return incomeSources;
	}

	public double getTotalValue() {
		Iterator<IncomeSource> iterator= incomeSources.iterator();
		double totalvalue=0;
		while(iterator.hasNext()) {
			totalvalue+=iterator.next().getTotalValue();
		}
		return totalvalue;
	}

	public abstract Boolean addIncomeSource(IncomeSource incomeSource);
	
	public abstract Boolean removeIncomeSource(IncomeSource incomeSource);
	
	public Boolean attachObserver(IncomeSourceManagerObserver obs) {
		return observers.add(obs);
	}
	
	public Boolean removeObserver(IncomeSourceManagerObserver obs) {
		return observers.remove(obs);
	}
	
	public void notifyObservers() {
		observers.forEach((x)->x.update());
	}
	
	public Boolean addIncomeSourceCollection(Collection<IncomeSource> coll) {
		if(coll.isEmpty())
			return false;
		ArrayList<IncomeSource> copy=incomeSources;
		coll.forEach((x)->this.addIncomeSource(x));
		return !incomeSources.equals(copy);
	}
	
	public Boolean removeIncomeSourceCollection(Collection<IncomeSource> coll) {
		ArrayList<IncomeSource> copy=incomeSources;
		if(coll.isEmpty())
			return false;
		coll.forEach((x)->this.removeIncomeSource(x));
		return !incomeSources.equals(copy);
	}
	
	public Boolean isEmpty() {
		return incomeSources.isEmpty();
	}
	
	public String returnReceipt() {
		String receipt="Income Sources receipt: ";
		Iterator<IncomeSource> iterator= incomeSources.iterator();
		while (iterator.hasNext()) {
			receipt+=iterator.next().accept(new DescriptionVisitor());
			if(iterator.hasNext())
				receipt+=" ";
		}
		return receipt;
	}
	
	public Iterator<IncomeSource> iterator(){
		return new Iterator<IncomeSource>() {
			private int count=0;
			
			private ArrayList<IncomeSource> incomeSourcescopy=incomeSources;

			@Override
			public boolean hasNext() {
				return count<incomeSources.size();
			}

			@Override
			public IncomeSource next() {
			IncomeSource max= incomeSourcescopy.stream()
					.max((x,y)->-1*x.getName()
					.compareToIgnoreCase(y.getName())) 
					.orElseThrow(()->new UnsupportedOperationException("Iterator Terminated!")); 
			incomeSourcescopy.remove(max);
				return max;
			}
		};
		
	}

}
