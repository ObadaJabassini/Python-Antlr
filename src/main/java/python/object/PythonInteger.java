package python.object;
import python.error.ExceptionManager;
import python.error.UnsupportedException;

public class PythonInteger extends python.object.PythonNumber
{
	private int value;
	
	public PythonInteger(String str){
		this.value = Integer.parseInt(str);
	}
	
	public PythonInteger(int value){
		this.value = value;
	}

	@Override
	public python.object.PythonObject apply(python.object.PythonObject second, String op) {
		switch (op)
		{
			case "==":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "==");
				}
				if(second instanceof  PythonComplex) {
                    		return new PythonBoolean(false);
                		}
				if(second instanceof PythonNumber){
					return new PythonBoolean(getValue() == ((PythonNumber) second).getValue());}
                	break;
			case "!=":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "!=");
				}
				if(second instanceof  PythonComplex){
					return new PythonBoolean(true);}
				if(second instanceof PythonNumber){
					return new PythonBoolean(getValue() != ((PythonNumber) second).getValue());}
                	break;
			case ">":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, ">");
				}
				if(second instanceof  PythonComplex){
					return new PythonBoolean(false);}
				if(second instanceof PythonNumber){
					return new PythonBoolean(getValue() > ((PythonNumber) second).getValue());}
                	break;
			case "<":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "<");
				}
				if(second instanceof  PythonComplex){
					return new PythonBoolean(false);}
				if(second instanceof PythonNumber){
					return new PythonBoolean(getValue() < ((PythonNumber) second).getValue());}
                	break;
			case ">=":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, ">=");
				}
				if(second instanceof  PythonComplex){
					return new PythonBoolean(false);}
				if(second instanceof PythonNumber){
					return new PythonBoolean(getValue() >= ((PythonNumber) second).getValue());}
                	break;
			case "<=":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "<=");
				}
				if(second instanceof  PythonComplex){
					return new PythonBoolean(false);}
				if(second instanceof PythonNumber){
					return new PythonBoolean(getValue() <= ((PythonNumber) second).getValue());}
                	break;
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return this;
	}

	@Override
	public python.object.PythonObject apply(String op)
	{
		switch (op)
		{
			case "not":
				if(getValue() == 0)
					return new PythonBoolean(true);
				return new PythonBoolean(false);
			case "~":
				return new PythonInteger(~((int)(this.getValue())));
			case "-":
				return new PythonInteger(-((int)(this.getValue())));
			case "+":
				return new PythonInteger((int)(this.getValue()));
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return this;
	}
	

	@Override
	public double getValue() {
		return value;
	}
}
