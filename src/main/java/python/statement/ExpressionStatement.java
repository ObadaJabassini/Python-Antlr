package python.statement;

import python.object.PythonObject;

import java.util.ArrayList;
import java.util.List;

public class ExpressionStatement extends Statement
{
	private boolean star = false;
	private List<PythonObject> objects;
	private List<String> operators;
	
	public  ExpressionStatement(PythonObject object){
		objects = new ArrayList<>();
		objects.add(object);
		operators = new ArrayList<>();
	}
	
	public ExpressionStatement(List<PythonObject> objects, List<String> operators) {
		this.objects = objects;
		this.operators = operators;
	}
	
	@Override
	public Object run() {
		return null;
	}
	
	public boolean isStar() {
		return star;
	}
	
	public void setStar(boolean star) {
		this.star = star;
	}
	
	public List<PythonObject> getObjects() {
		return objects;
	}
	
	public void setObjects(List<PythonObject> objects) {
		this.objects = objects;
	}
	
	public List<String> getOperators() {
		return operators;
	}
	
	public void setOperators(List<String> operators) {
		this.operators = operators;
	}
}
