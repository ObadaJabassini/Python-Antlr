package java.python.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ExceptionManager extends Observable
{
	private List<ParseException> exceptions = new ArrayList<>();
	private static final ExceptionManager manager = new ExceptionManager();
	
	private ExceptionManager(){
		
	}
	
	public static ExceptionManager getManager(){
		return manager;
	}
	
	public void add(ParseException exception){
		this.exceptions.add(exception);
		notifyObservers(exception);
	}
}
