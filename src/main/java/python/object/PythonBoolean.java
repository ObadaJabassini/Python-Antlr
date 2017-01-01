package python.object;
import python.error.ExceptionManager;
import python.error.UnsupportedException;

public class PythonBoolean extends python.object.PythonObject
{
    private boolean value;

    public boolean getValue() {
        return value;
    }

    public PythonBoolean(String str)
    {
        value = Boolean.parseBoolean(str);
    }

    public PythonBoolean(boolean val)
    {
        value = val;
    }

    @Override
    public python.object.PythonObject apply(python.object.PythonObject second, String op) {
        python.object.PythonInteger integer = new python.object.PythonInteger(getValue()? 1:0);
        switch (op){
            case "+":
                if(second instanceof python.object.PythonComplex )
                {
                    python.object.PythonComplex number = (python.object.PythonComplex) second;
                    return number.apply(integer, "+");
                }
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "+");
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return integer.apply(number, "+");
                }
                break;
            case "-":
                if(second instanceof python.object.PythonComplex )
                {
                    python.object.PythonComplex number = (python.object.PythonComplex) second;
                    number = (python.object.PythonComplex) number.apply("-");
                    return number.apply(integer.apply("-"), "-");
                }
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "-");
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return integer.apply(number, "-");
                }
                break;
            case "/":
                if(second instanceof python.object.PythonComplex )
                {
                    python.object.PythonComplex thisAsComplex = new python.object.PythonComplex(integer.getValue(), 0);
                    return integer.apply(thisAsComplex, "/");
                }
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "/");
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return integer.apply(number, "/");
                }
                break;
            case "//":
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "//");
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return integer.apply(number, "//");
                }
                break;
            case "*":
                if(second instanceof python.object.PythonComplex )
                {
                    python.object.PythonComplex number = (python.object.PythonComplex) second;
                    return number.apply(integer, "*");
                }
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "*");
                }
                if(second instanceof python.object.PythonNumber ) {
                    python.object.PythonNumber number = (python.object.PythonNumber) second;
                    return integer.apply(number, "*");
                }
                break;
            case "|":
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "|");
                }
                if(second instanceof python.object.PythonInteger ) {
                    python.object.PythonInteger number = (python.object.PythonInteger) second;
                    return integer.apply(number, "|");
                }
                break;
            case "^":
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "|");
                }
                if(second instanceof python.object.PythonInteger ) {
                    python.object.PythonInteger number = (python.object.PythonInteger) second;
                    return integer.apply(number, "^");
                }
                break;
            case "&":
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "&");
                }
                if(second instanceof python.object.PythonInteger ) {
                    python.object.PythonInteger number = (python.object.PythonInteger) second;
                    return integer.apply(number, "&");
                }
                break;
            case ">>":
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, ">>");
                }
                if(second instanceof python.object.PythonInteger ) {
                    python.object.PythonInteger number = (python.object.PythonInteger) second;
                    return integer.apply(number, ">>");
                }
                break;
            case "<<":
                if(second instanceof PythonBoolean){
                    python.object.PythonInteger bool = new python.object.PythonInteger(((PythonBoolean) second).getValue()?1:0);
                    return integer.apply(bool, "<<");
                }
                if(second instanceof python.object.PythonInteger ) {
                    python.object.PythonInteger number = (python.object.PythonInteger) second;
                    return integer.apply(number, "<<");
                }
                break;
            case "and":
                if(second instanceof PythonBoolean) {
                    PythonBoolean bool = (PythonBoolean) second;
                    return new PythonBoolean(getValue() && bool.getValue());
                }
                return second;
            case "or":
                if(second instanceof PythonBoolean) {
                    PythonBoolean bool = (PythonBoolean) second;
                    return new PythonBoolean(getValue() || bool.getValue());
                }
                return new PythonBoolean(getValue());
        }
        ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
        return null;
    }

    @Override
    public python.object.PythonObject apply(String op)
    {
        switch (op)
        {
            case "not":
                return new PythonBoolean(!getValue());
            case "~":
                return new python.object.PythonInteger(~(getValue()?1:0));
            case "-":
                return new python.object.PythonInteger(-(getValue()?1:0));
            case "+":
                return new python.object.PythonInteger(getValue()?1:0);
        }
        ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
        return null;
    }

    public PythonBoolean compareTo(python.object.PythonObject second, String op)
    {
        switch (op)
        {
            case "==":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(new python.object.PythonInteger(bool.getValue()?1:0), "==");
                }
                if(!(second instanceof python.object.PythonComplex))
                    if(second instanceof python.object.PythonNumber )
                        return new PythonBoolean((getValue()?1:0) == ((python.object.PythonNumber) second).getValue());
                return new PythonBoolean(false);
            case "!=":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(new python.object.PythonInteger(bool.getValue()?1:0), "!=");
                }
                if(!(second instanceof python.object.PythonComplex))
                    if(second instanceof python.object.PythonNumber )
                        return new PythonBoolean((getValue()?1:0) != ((python.object.PythonNumber) second).getValue());
                return new PythonBoolean(true);
            case ">":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(new python.object.PythonInteger(bool.getValue()?1:0), ">");
                }
                if(!(second instanceof python.object.PythonComplex))
                    if(second instanceof python.object.PythonNumber )
                        return new PythonBoolean((getValue()?1:0) > ((python.object.PythonNumber) second).getValue());
                return new PythonBoolean(false);
            case "<":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(new python.object.PythonInteger(bool.getValue()?1:0), "<");
                }
                if(!(second instanceof python.object.PythonComplex))
                    if(second instanceof python.object.PythonNumber )
                        return new PythonBoolean((getValue()?1:0) < ((python.object.PythonNumber) second).getValue());
                return new PythonBoolean(false);
            case ">=":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(new python.object.PythonInteger(bool.getValue()?1:0), ">=");
                }
                if(!(second instanceof python.object.PythonComplex))
                    if(second instanceof python.object.PythonNumber )
                        return new PythonBoolean((getValue()?1:0) >= ((python.object.PythonNumber) second).getValue());
                return new PythonBoolean(false);
            case "<=":
                if(second instanceof PythonBoolean)
                {
                    PythonBoolean bool = (PythonBoolean) second;
                    return compareTo(new python.object.PythonInteger(bool.getValue()?1:0), "<=");
                }
                if(!(second instanceof python.object.PythonComplex))
                    if(second instanceof python.object.PythonNumber )
                        return new PythonBoolean((getValue()?1:0) <= ((python.object.PythonNumber) second).getValue());
                return new PythonBoolean(false);
        }
        ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
        return null;
    }
}