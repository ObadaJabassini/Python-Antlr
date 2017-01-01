package python.object;

import python.error.ExceptionManager;
import python.error.UnsupportedException;

public class PythonComplex extends python.object.PythonNumber
{
	private double i, j;
	public PythonComplex(String str){
		j = Double.parseDouble(str.substring(0, str.length() - 1));
	}
    public PythonComplex(double i, double j) {
	    this.i = i;
	    this.j = j;
    }

	@Override
	public python.object.PythonObject apply(python.object.PythonObject second, String op) {
        switch (op){
            case "+":
	            
	            if(second instanceof PythonComplex) {
                    PythonComplex number = (PythonComplex) second;
                    return new PythonComplex(this.getValue() + number.getValue(), getImgValue() + number.getImgValue());
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return new PythonComplex(this.getValue() + number.getValue(), getImgValue());
                }
                if(second instanceof PythonBoolean ) {
                    python.object.PythonInteger number = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return apply(number, "+");
                }
                break;
            case "-":
                if(second instanceof PythonComplex) {
                    PythonComplex number = (PythonComplex) second;
                    return new PythonComplex(this.getValue() - number.getValue(), getImgValue() - number.getImgValue());
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return new PythonComplex(this.getValue() - number.getValue(), getImgValue());
                }
                if(second instanceof PythonBoolean) {
                    python.object.PythonInteger number = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return apply(number, "-");
                }
                break;
            case "/":
                if(second instanceof PythonComplex) {
                    PythonComplex number = (PythonComplex) second;
                    PythonComplex op1 = (PythonComplex) apply(new PythonComplex(number.getValue(), -number.getImgValue()), "*");
                    double op2 = ((python.object.PythonNumber) number.apply(new PythonComplex(number.getValue(), -number.getImgValue()), "*")).getValue();
                    return new PythonComplex(op1.getValue()/op2, op1.getImgValue()/op2);
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return apply(new PythonComplex(number.getValue(), 0), "/");
                }
                if(second instanceof PythonBoolean) {
                    python.object.PythonInteger number = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return apply(number, "/");
                }
                break;
            case "*":
                if(second instanceof PythonComplex) {
                    PythonComplex number = (PythonComplex) second;
                    return new PythonComplex(
                            this.getValue()*number.getValue() - getImgValue()*number.getImgValue(),
                            getImgValue()*number.getImgValue() + number.getValue()*getImgValue());
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return apply(new PythonComplex(number.getValue(), 0), "*");
                }
                if(second instanceof PythonBoolean) {
                    python.object.PythonInteger number = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return apply(number, "*");
                }
                break;
            case "and":
                return second;
            case "or":
                return new PythonComplex(getValue(), getImgValue());
	
	        case "==":
		        if(second instanceof PythonComplex)
		        {
			        PythonComplex number = (PythonComplex) second;
			        return new PythonBoolean(number.getValue() == getValue() && number.getImgValue() == getImgValue());
		        }
		        return new PythonBoolean(false);
	        case "!=":
		        if(second instanceof PythonComplex)
		        {
			        PythonComplex number = (PythonComplex) second;
			        return new PythonBoolean(number.getValue() != getValue() || number.getImgValue() != getImgValue());
		        }
		        return new PythonBoolean(true);
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
                if(getValue() == 0.0 && getImgValue() == 0.0)
                    return new PythonBoolean(true);
                return new PythonBoolean(false);
            case "-":
                return new PythonComplex(-this.getValue(), -getImgValue());
            case "+":
                return new PythonComplex(this.getValue(), getImgValue());
                
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return this;
	}

	public double getImgValue() { return j; }

	@Override
	public double getValue() {
		return i;
	}
}
