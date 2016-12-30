package python.object;

public class PythonString extends PythonObject
{
	private String value;
	
	public PythonString(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
