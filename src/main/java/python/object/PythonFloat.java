package python.object;
import python.error.ExceptionManager;
import python.error.UnsupportedException;

public class PythonFloat extends python.object.PythonNumber
{
	private double value;
	
	public PythonFloat(String str){
		this.value = Double.parseDouble(str);
	}

	public PythonFloat(double f)
    {
        value = f;
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
                if(second instanceof  PythonComplex){
                    return new PythonBoolean(false);}
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
				if(getValue() == 0.0)
					return new PythonBoolean(true);
				return new PythonBoolean(false);
			case "-":
				return new PythonFloat(-this.getValue());
			case "+":
				return new PythonFloat(this.getValue());
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return this;
	}

	@Override
	public double getValue() {
		return value;
	}
}
