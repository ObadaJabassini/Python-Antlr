package python.object;

public class PythonFloat extends PythonObject implements PythonNumber
{
	private double value;
	
	public PythonFloat(String str){
		this.value = Double.parseDouble(str);
	}

	@Override
	public PythonObject apply(PythonObject second, String op) {
		switch (op){
			case "+":
			    if(second instanceof PythonComplex)
                {
                    PythonComplex number = (PythonComplex) second;
                    return number.apply(this, "+");
                }
				if(second instanceof PythonNumber) {
					PythonNumber number = (PythonNumber) second;
					return new PythonFloat(this.getValue() + number.getValue());
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "+");
				}
				break;
			case "-":
                if(second instanceof PythonComplex)
                {
                    PythonComplex number = (PythonComplex) second;
                    number = number.apply("-");
                    return number.apply(this.apply("-"), "-");
                }
				if(second instanceof PythonNumber) {
					PythonNumber number = (PythonNumber) second;
					return new PythonFloat(this.getValue() - number.getValue());
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "-");
				}
				break;
			case "/":
                if(second instanceof PythonComplex)
                {
                    PythonComplex number = (PythonComplex) second;
                    PythonComplex thisAsComplex = new PythonComplex(getValue(), 0);
                    return thisAsComplex.apply(number, "/");
                }
				if(second instanceof PythonNumber) {
					PythonNumber number = (PythonNumber) second;
					return new PythonFloat(this.getValue() / number.getValue());
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "/");
				}
				break;
			case "//":
				if(second instanceof PythonNumber) {
					PythonNumber number = (PythonNumber) second;
					return new PythonInteger((int)(this.getValue()) / (int)(number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "//");
				}
				break;
			case "*":
                if(second instanceof PythonComplex)
                {
                    PythonComplex number = (PythonComplex) second;
                    return number.apply(this, "*");
                }
				if(second instanceof PythonNumber) {
					PythonNumber number = (PythonNumber) second;
					return new PythonInteger(this.getValue() * number.getValue());
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "*");
				}
				break;
			case "and":
				return second;
			break;
			case "or":
				return new PythonFloat(this.getValue());
			break;
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}

	@Override
	public PythonObject apply(String op)
	{
		switch (op)
		{
			case "not":
				if(getValue() == 0.0)
					return new PythonBoolean(true);
				return new PythonBoolean(false);
			break;
			case "-":
				return new PythonInteger(-this.getValue())
			break;
			case "+":
				return new PythonInteger(this.getValue())
			break;
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}

    public PythonBoolean compareTo(PythonObject second, String op)
    {
        switch (op)
        {
            case "==":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(bool, "==");
                }
                if(!(second instanceof  PythonComplex))
                    if(second instanceof PythonNumber)
                        return new PythonBoolean(getValue() == ((PythonNumber) second).getValue());
                return new PythonBoolean(false);
            break;
            case "!=":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(bool, !=");
                }
                if(!(second instanceof  PythonComplex))
                    if(second instanceof PythonNumber)
                        return new PythonBoolean(getValue() != ((PythonNumber) second).getValue());
                return new PythonBoolean(true);
            break;
            case ">":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(bool, ">");
                }
                if(!(second instanceof  PythonComplex))
                    if(second instanceof PythonNumber)
                        return new PythonBoolean(getValue() > ((PythonNumber) second).getValue());
                return new PythonBoolean(false);
            break;
            case "<":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(bool, "<");
                }
                if(!(second instanceof  PythonComplex))
                    if(second instanceof PythonNumber)
                        return new PythonBoolean(getValue() < ((PythonNumber) second).getValue());
                return new PythonBoolean(false);
            break;
            case ">=":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(bool, ">=");
                }
                if(!(second instanceof  PythonComplex))
                    if(second instanceof PythonNumber)
                        return new PythonBoolean(getValue() >= ((PythonNumber) second).getValue());
                return new PythonBoolean(false);
            break;
            case "<=":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(bool, "<=");
                }
                if(!(second instanceof  PythonComplex))
                    if(second instanceof PythonNumber)
                        return new PythonBoolean(getValue() <= ((PythonNumber) second).getValue());
                return new PythonBoolean(false);
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
