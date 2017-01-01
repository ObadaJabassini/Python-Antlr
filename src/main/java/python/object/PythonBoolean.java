package python.object;

import python.error.ExceptionManager;
import python.error.UnsupportedException;

public class PythonBoolean extends PythonObject
{
	private boolean value;
	
	public boolean getValue() {
		return value;
	}
	
	public PythonBoolean(String str) {
		value = Boolean.parseBoolean(str);
	}
	
	public PythonBoolean(boolean val) {
		value = val;
	}
	
	@Override
	public PythonObject apply(PythonObject second, String op) {
		switch (op) {
			case "+":
				PythonInteger integer = new PythonInteger(getValue() ? 1 : 0);
				if ( second instanceof PythonComplex ) {
					PythonComplex number = (PythonComplex) second;
					return number.apply(integer, "+");
				}
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "+");
				}
				if ( second instanceof PythonNumber ) {
					PythonNumber number = (PythonNumber) second;
					return integer.apply(number, "+");
				}
			case "-":
				if ( second instanceof PythonComplex ) {
					PythonComplex number = (PythonComplex) second;
					number = number.apply("-");
					return number.apply(integer.apply("-"), "-");
				}
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "-");
				}
				if ( second instanceof PythonNumber ) {
					PythonNumber number = (PythonNumber) second;
					return integer.apply(number, "-");
				}
			case "/":
				if ( second instanceof PythonComplex ) {
					PythonComplex number = (PythonComplex) second;
					PythonComplex thisAsComplex = new PythonComplex(integer.getValue(), 0);
					return thisAsComplex.apply(number, "/");
				}
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "/");
				}
				if ( second instanceof PythonNumber ) {
					PythonNumber number = (PythonNumber) second;
					return integer.apply(number, "/");
				}
			case "//":
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "//");
				}
				if ( second instanceof PythonNumber ) {
					PythonNumber number = (PythonNumber) second;
					return integer.apply(number, "//");
				}
			case "*":
				if ( second instanceof PythonComplex ) {
					PythonComplex number = (PythonComplex) second;
					return number.apply(integer, "*");
				}
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "*");
				}
				if ( second instanceof PythonNumber ) {
					PythonNumber number = (PythonNumber) second;
					return integer.apply(number, "*");
				}
			case "|":
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "|");
				}
				if ( second instanceof PythonInteger ) {
					PythonInteger number = (PythonInteger) second;
					return integer.apply(number, "|");
				}
			case "^":
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "|");
				}
				if ( second instanceof PythonInteger ) {
					PythonInteger number = (PythonInteger) second;
					return integer.apply(number, "^");
				}
			case "&":
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "&");
				}
				if ( second instanceof PythonInteger ) {
					PythonInteger number = (PythonInteger) second;
					return integer.apply(number, "&");
				}
			case ">>":
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, ">>");
				}
				if ( second instanceof PythonInteger ) {
					PythonInteger number = (PythonInteger) second;
					return integer.apply(number, ">>");
				}
			case "<<":
				if ( second instanceof PythonBoolean ) {
					PythonInteger bool = new PythonInteger(((PythonBoolean) second).getValue() ? 1 : 0);
					return integer.apply(bool, "<<");
				}
				if ( second instanceof PythonInteger ) {
					PythonInteger number = (PythonInteger) second;
					return integer.apply(number, "<<");
				}
			case "and":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return new PythonBoolean(getValue() && bool);
				}
				return second;
			case "or":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return new PythonBoolean(getValue() || bool);
				}
				return new PythonBoolean(getValue());
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}
	
	@Override
	public PythonObject apply(String op) {
		switch (op) {
			case "not":
				return new PythonBoolean(!getValue());
			case "~":
				return new PythonInteger(~(getValue() ? 1 : 0))
			case "-":
				return new PythonInteger(-(getValue() ? 1 : 0))
			case "+":
				return new PythonInteger(getValue() ? 1 : 0)
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}
	
	public PythonBoolean compareTo(PythonObject second, String op) {
		switch (op) {
			case "==":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool.getValue() ? 1 : 0, "==");
				}
				if ( !(second instanceof PythonComplex) ) {
					if ( second instanceof PythonNumber ) {
						return new PythonBoolean(getValue() ? 1 : 0 == ((PythonNumber) second).getValue());
					}
				}
				return new PythonBoolean(false);
			case "!=":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool.getValue() ? 1 : 0, "!=");
				}
				if ( !(second instanceof PythonComplex) ) {
					if ( second instanceof PythonNumber ) {
						return new PythonBoolean(getValue() ? 1 : 0 != ((PythonNumber) second).getValue());
					}
				}
				return new PythonBoolean(true);
			case ">":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool.getValue() ? 1 : 0, ">");
				}
				if ( !(second instanceof PythonComplex) ) {
					if ( second instanceof PythonNumber ) {
						return new PythonBoolean(getValue() ? 1 : 0 > ((PythonNumber) second).getValue());
					}
				}
				return new PythonBoolean(false);
			case "<":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool.getValue() ? 1 : 0, "<");
				}
				if ( !(second instanceof PythonComplex) ) {
					if ( second instanceof PythonNumber ) {
						return new PythonBoolean(getValue() ? 1 : 0 < ((PythonNumber) second).getValue());
					}
				}
				return new PythonBoolean(false);
			case ">=":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool.getValue() ? 1 : 0, ">=");
				}
				if ( !(second instanceof PythonComplex) ) {
					if ( second instanceof PythonNumber ) {
						return new PythonBoolean(getValue() ? 1 : 0 >= ((PythonNumber) second).getValue());
					}
				}
				return new PythonBoolean(false);
			case "<=":
				if ( second instanceof PythonBoolean ) {
					PythonBoolean bool = (PythonBoolean) second;
					return compareTo(bool.getValue() ? new PythonInteger(1) : new PythonInteger(0), "<=");
				}
				if ( !(second instanceof PythonComplex) ) {
					if ( second instanceof PythonNumber ) {
						return new PythonBoolean(getValue() ? 1 : 0 <= ((PythonNumber) second).getValue());
					}
				}
				return new PythonBoolean(false);
		}
		ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
		return null;
	}
}