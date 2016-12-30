package python.object;

public class PythonFloat extends PythonObject implements PythonNumber
{
	private double value;
	
	public PythonFloat(String str){
		this.value = Double.parseDouble(str);
	}
	
	@Override
	public double getValue() {
		return value;
	}
}
