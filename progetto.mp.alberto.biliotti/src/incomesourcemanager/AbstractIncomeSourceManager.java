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
	
	public Boolean attachObserver(IncomeSourceManagerObserver observer) {
		return observers.add(observer);
	}
	
	public Boolean removeObserver(IncomeSourceManagerObserver observer) {
		return observers.remove(observer);
	}
	
	public void notifyObservers() {
		observers.forEach((observer)->observer.update());
	}
	
	public Boolean addIncomeSourceCollection(Collection<IncomeSource> IncomesSourcesToAdd) {
		if(IncomesSourcesToAdd.isEmpty())
			return false;
		ArrayList<IncomeSource> copy=incomeSources;
		IncomesSourcesToAdd.forEach((incomeSource)->this.addIncomeSource(incomeSource));
		return !incomeSources.equals(copy);
	}
	
	public Boolean removeIncomeSourceCollection(Collection<IncomeSource> IncomesSourcesToRemove) {
		ArrayList<IncomeSource> copy=incomeSources;
		if(IncomesSourcesToRemove.isEmpty())
			return false;
		IncomesSourcesToRemove.forEach((incomeSource)->this.removeIncomeSource(incomeSource));
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
			IncomeSource maxIncomeSource= incomeSourcescopy.stream()
					.max((firstIncomeSource,secondIncomeSource)->-1*firstIncomeSource.getName()
					.compareToIgnoreCase(secondIncomeSource.getName())) 
					.orElseThrow(()->new UnsupportedOperationException("Iterator Terminated!")); 
			incomeSourcescopy.remove(maxIncomeSource);
				return maxIncomeSource;
			}
		};
		
	}

}
