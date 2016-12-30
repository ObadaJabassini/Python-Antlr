package python.object;

public class PythonComplex extends PythonObject implements PythonNumber
{
	private double i, j;
	public PythonComplex(String str){
		j = Double.parseDouble(str.substring(0, str.length() - 1));
	}
	
	@Override
	public double getValue() {
		return i;
	}
}
