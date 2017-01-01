package python.object;

import python.error.ExceptionManager;
import python.error.UnsupportedException;

public class PythonString extends PythonObject
{
	private String value;
	
	public PythonString(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

	@Override
	public PythonObject apply(PythonObject second, String op) {
		switch (op){
			case "+":
				if(second instanceof PythonString) {
					PythonString str = (PythonString)second;
					return new PythonString(getValue()+str.getValue());
				}
				break;
			case "*":
				if(second instanceof PythonInteger) {
					PythonInteger number = (PythonInteger) second;
					return new PythonString(new String(new char[(int)number.getValue()]).replace("\0", getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonBoolean number = (PythonBoolean) second;
					return apply(new PythonInteger(number.getValue()?1:0), "*");
				}
				break;
			case "and":
				return second;
			case "or":
				return new PythonString(getValue());
			
			case "==":
				if(second instanceof PythonString)
				{
					PythonString str = (PythonString) second;
					return new PythonBoolean(str.getValue().compareTo(getValue()) == 0);
				}
				return new PythonBoolean(false);
			case "!=":
				if(second instanceof PythonString)
				{
					PythonString str = (PythonString) second;
					return new PythonBoolean(str.getValue().compareTo(getValue()) != 0);
				}
				return new PythonBoolean(true);
			case ">":
				if(second instanceof PythonString)
				{
					PythonString str = (PythonString) second;
					return new PythonBoolean(str.getValue().compareTo(getValue()) > 0);
				}
				return new PythonBoolean(true);
			case "<":
				if(second instanceof PythonString)
				{
					PythonString str = (PythonString) second;
					return new PythonBoolean(str.getValue().compareTo(getValue()) < 0);
				}
				return new PythonBoolean(true);
			case ">=":
				return new PythonBoolean(true);
			case "<=":
				return new PythonBoolean(true);
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return this;
	}

	@Override
	public PythonObject apply(String op)
	{
		switch (op)
		{
			case "not":
				return new PythonBoolean(false);
				
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return this;
	}

	
	@Override
	public String toString() {
		return value;
	}
}
