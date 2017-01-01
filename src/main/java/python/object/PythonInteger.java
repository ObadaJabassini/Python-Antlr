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
		switch (op){
			case "+":
				if(second instanceof PythonComplex)
				{
					PythonComplex number = (PythonComplex) second;
					return number.apply(this, "+");
				}
				if(second instanceof PythonInteger) {
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue() + number.getValue()));
				}
				if(second instanceof PythonFloat) {
					python.object.PythonNumber number = (PythonFloat) second;
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
					number = (PythonComplex) number.apply("-");
					return number.apply(this.apply("-"), "-");
				}
				if(second instanceof PythonInteger) {
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue() - number.getValue()));
				}
				if(second instanceof PythonFloat) {
					python.object.PythonNumber number = (PythonFloat) second;
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
				if(second instanceof python.object.PythonNumber ) {
					python.object.PythonNumber number = (python.object.PythonNumber) second;
					return new PythonFloat(this.getValue() / number.getValue());
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "/");
				}
				break;
			case "//":
				if(second instanceof python.object.PythonNumber ) {
					python.object.PythonNumber number = (python.object.PythonNumber) second;
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
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue() * number.getValue()));
				}
				if(second instanceof PythonFloat) {
					python.object.PythonNumber number = (PythonFloat) second;
					return new PythonFloat(this.getValue() * number.getValue());
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "*");
				}
				if(second instanceof python.object.PythonString ) {
					python.object.PythonString str = (python.object.PythonString) second;
					return new python.object.PythonString(new String(new char[(int)getValue()]).replace("\0", str.getValue()));
				}
				break;
			case "|":
				if(second instanceof PythonInteger) {
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) | (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "|");
				}
				break;
			case "^":
				if(second instanceof PythonInteger) {
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) ^ (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "^");
				}
				break;
			case "&":
				if(second instanceof PythonInteger) {
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) & (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "&");
				}
				break;
			case ">>":
				if(second instanceof PythonInteger) {
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) >> (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, ">>");
				}
				break;
			case "<<":
				if(second instanceof PythonInteger) {
					python.object.PythonNumber number = (PythonInteger) second;
					return new PythonInteger((int)(this.getValue()) << (int) (number.getValue()));
				}
				if(second instanceof PythonBoolean) {
					PythonInteger number = new PythonInteger(((PythonBoolean) second).getValue()?1:0);
					return apply(number, "<<");
				}
				break;
			case "and":
				return second;
			case "or":
				return new PythonInteger((int)(this.getValue()));
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
	
	
	public PythonBoolean compareTo(python.object.PythonObject second, String op)
	{
		switch (op)
		{
			case "==":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "==");
				}
				if(second instanceof  PythonComplex)
					return new PythonBoolean(false);
				if(second instanceof python.object.PythonNumber )
					return new PythonBoolean(getValue() == ((python.object.PythonNumber) second).getValue());

			case "!=":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "!=");
				}
				if(second instanceof  PythonComplex)
					return new PythonBoolean(true);
				if(second instanceof python.object.PythonNumber )
					return new PythonBoolean(getValue() != ((python.object.PythonNumber) second).getValue());
			case ">":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, ">");
				}
				if(second instanceof  PythonComplex)
					return new PythonBoolean(false);
				if(second instanceof python.object.PythonNumber )
					return new PythonBoolean(getValue() > ((python.object.PythonNumber) second).getValue());
			case "<":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "<");
				}
				if(second instanceof  PythonComplex)
					return new PythonBoolean(false);
				if(second instanceof python.object.PythonNumber )
					return new PythonBoolean(getValue() < ((python.object.PythonNumber) second).getValue());
			case ">=":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, ">=");
				}
				if(second instanceof  PythonComplex)
					return new PythonBoolean(false);
				if(second instanceof python.object.PythonNumber )
					return new PythonBoolean(getValue() >= ((python.object.PythonNumber) second).getValue());
			case "<=":
				if(second instanceof PythonBoolean)
				{
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool, "<=");
				}
				if(second instanceof  PythonComplex)
					return new PythonBoolean(false);
				if(second instanceof python.object.PythonNumber )
					return new PythonBoolean(getValue() <= ((python.object.PythonNumber) second).getValue());
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}

	@Override
	public double getValue() {
		return value;
	}
}
