package python.object;

public class PythonInteger extends PythonObject
{
	private int value;
	
	public PythonInteger(String str){
		this.value = Integer.parseInt(str);
	}
}
