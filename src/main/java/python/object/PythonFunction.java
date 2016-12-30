package python.object;

public class PythonFunction extends PythonObject
{
	protected String name;
	protected PythonBlock body;
	protected PythonParameters parameters;
	
	public PythonFunction(String name, PythonBlock body, PythonParameters parameters) {
		this.name = name;
		this.body = body;
		this.parameters = parameters;
	}
	
	public void call(){
		body.run();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public PythonBlock getBody() {
		return body;
	}
	
	public void setBody(PythonBlock body) {
		this.body = body;
	}
	
	public PythonParameters getParameters() {
		return parameters;
	}
	
	public void setParameters(PythonParameters parameters) {
		this.parameters = parameters;
	}
}
