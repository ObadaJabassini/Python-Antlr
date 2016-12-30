package python.object;

public class PythonFloat extends PythonObject
{
	private double value;
	
	public PythonFloat(String str){
		this.value = Double.parseDouble(str);
	}
}
