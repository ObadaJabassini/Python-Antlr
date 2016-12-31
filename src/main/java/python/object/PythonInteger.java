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
				if(second instanceof PythonComplex)
				{
					PythonComplex number = (PythonComplex) second;
					return number.apply(this, "+");
				}
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue() + number.getValue()));
				}
				if(second instanceof PythonFloat) {
					PythonNumber number = (PythonFloat) second;
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
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue() - number.getValue()));
				}
				if(second instanceof PythonFloat) {
					PythonNumber number = (PythonFloat) second;
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
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue() * number.getValue()));
				}
				if(second instanceof PythonFloat) {
					PythonNumber number = (PythonFloat) second;
					return new PythonFloat(this.getValue() * number.getValue());
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "*");
				}
				if(second instanceof PythonString) {
					PythonString str = (PythonString) second;
					return new PythonString(new String(new char[getValue()]).replace("\0", str.getValue()));
				}
				break;
			case "|":
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) | (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "|");
				}				break;
			case "^":
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) ^ (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "^");
				}				break;
			case "&":
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) & (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "&");
				}				break;
			case ">>":
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) >> (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, ">>");
				}				break;
			case "<<":
				if(second instanceof PythonInteger) {
					PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) << (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "<<");
				}				break;
			case "and":
				return second;
				break;
			case "or":
				return new PythonInteger((int)(this.getValue()));
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
				if(getValue() == 0)
					return new PythonBoolean(true);
				return new PythonBoolean(false);
				break;
			case "~":
				return new PythonInteger(~((int)(this.getValue())))
				break;
			case "-":
				return new PythonInteger(-((int)(this.getValue())))
				break;
			case "+":
				return new PythonInteger((int)(this.getValue()))
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
					return compareTo(bool, "!=");
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
