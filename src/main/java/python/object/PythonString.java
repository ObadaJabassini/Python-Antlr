package python.object;

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
					return new PythonString(new String(new char[number.getValue()]).replace("\0", getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonBoolean number = (PythonBoolean) second;
					return apply(number.getValue()?1:0, "*");
				}
				break;
			case "and":
				return second;
				break;
			case "or":
				return new PythonString(getValue());
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
				return new PythonBoolean(false);
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
				if(second instanceof PythonString)
				{
                    PythonString str = (PythonString) second;
					return new PythonBoolean(str.getValue().compareTo(getValue()) == 0);
				}
				return new PythonBoolean(false);
			break;
			case "!=":
                if(second instanceof PythonString)
                {
                    PythonString str = (PythonString) second;
                    return new PythonBoolean(str.getValue().compareTo(getValue()) != 0);
                }
				return new PythonBoolean(true);
			break;
            case ">":
                if(second instanceof PythonString)
                {
                    PythonString str = (PythonString) second;
                    return new PythonBoolean(str.getValue().compareTo(getValue()) > 0);
                }
                return new PythonBoolean(true);
                break;
            case "<":
                if(second instanceof PythonString)
                {
                    PythonString str = (PythonString) second;
                    return new PythonBoolean(str.getValue().compareTo(getValue()) < 0);
                }
                return new PythonBoolean(true);
                break;
            case ">=":
                if(second instanceof PythonString)
                {
                    PythonString str = (PythonString) second;
                    return new PythonBoolean(compareTo(str,"==").getValue() || compareTo(str,">").getValue());
                }
                return new PythonBoolean(true);
                break;
            case "<=":
                if(second instanceof PythonString)
                {
                    PythonString str = (PythonString) second;
                    return new PythonBoolean(compareTo(str,"==").getValue() || compareTo(str,"<").getValue());
                }
                return new PythonBoolean(true);
                break;
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}
}
