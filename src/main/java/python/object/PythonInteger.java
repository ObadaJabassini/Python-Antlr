package python.object;

import python.error.ExceptionManager;
import python.error.UnsupportedException;

public class PythonInteger extends PythonObject implements PythonNumber
{
	private int value;
	
	public PythonInteger(String str){
		this.value = Integer.parseInt(str);
	}
	
	public PythonInteger(int value){
		this.value = value;
	}
	
	@Override
	public PythonObject apply(PythonObject second, String op) {
		switch (op){
			case "+":
				if(second instanceof PythonNumber) {
					PythonNumber number = (PythonNumber) second;
					return new PythonInteger((int) (number.getValue() + this.getValue()));
				}
				if(second instanceof PythonString){
					//concat for example
					PythonString string = (PythonString) second;
					return new PythonString(this.getValue() + string.getValue());
				}
				break;
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}
	
	@Override
	public double getValue() {
		return value;
	}
}
