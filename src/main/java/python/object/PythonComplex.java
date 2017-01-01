package java.python.object;

import java.python.error.ExceptionManager;
import java.python.error.UnsupportedException;

public class PythonComplex extends PythonNumber
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
	public PythonObject apply(PythonObject second, String op) {
        switch (op){
            case "+":
                if(second instanceof PythonComplex) {
                    PythonComplex number = (PythonComplex) second;
                    return new PythonComplex(this.getValue() + number.getValue(), getImgValue() + number.getImgValue());
                }
                if(second instanceof PythonNumber) {
                    PythonNumber number = (PythonNumber) second;
                    return new PythonComplex(this.getValue() + number.getValue(), getImgValue());
                }
                if(second instanceof PythonBoolean) {
                    PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return apply(number, "+");
                }
                break;
            case "-":
                if(second instanceof PythonComplex) {
                    PythonComplex number = (PythonComplex) second;
                    return new PythonComplex(this.getValue() - number.getValue(), getImgValue() - number.getImgValue());
                }
                if(second instanceof PythonNumber) {
                    PythonNumber number = (PythonNumber) second;
                    return new PythonComplex(this.getValue() - number.getValue(), getImgValue());
                }
                if(second instanceof PythonBoolean) {
                    PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return apply(number, "-");
                }
                break;
            case "/":
                if(second instanceof PythonComplex) {
                    PythonComplex number = (PythonComplex) second;
                    PythonComplex op1 = (PythonComplex) apply(new PythonComplex(number.getValue(), -number.getImgValue()), "*");
                    double op2 = ((PythonNumber) number.apply(new PythonComplex(number.getValue(), -number.getImgValue()), "*")).getValue();
                    return new PythonComplex(op1.getValue()/op2, op1.getImgValue()/op2);
                }
                if(second instanceof PythonNumber) {
                    PythonNumber number = (PythonNumber) second;
                    return apply(new PythonComplex(number.getValue(), 0), "/");
                }
                if(second instanceof PythonBoolean) {
                    PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
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
                if(second instanceof PythonNumber) {
                    PythonNumber number = (PythonNumber) second;
                    return apply(new PythonComplex(number.getValue(), 0), "*");
                }
                if(second instanceof PythonBoolean) {
                    PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return apply(number, "*");
                }
                break;
            case "and":
                return second;
            case "or":
                return new PythonComplex(getValue(), getImgValue());
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
                if(getValue() == 0.0 && getImgValue() == 0.0)
                    return new PythonBoolean(true);
                return new PythonBoolean(false);
            case "-":
                return new PythonComplex(-this.getValue(), -getImgValue());
            case "+":
                return new PythonComplex(this.getValue(), getImgValue());
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}

    public PythonBoolean compareTo(PythonObject second, String op)
    {
        switch (op)
        {
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
        return null;
    }

	public double getImgValue() { return j; }

	@Override
	public double getValue() {
		return i;
	}
}
