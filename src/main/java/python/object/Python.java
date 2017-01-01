package python.object;

public class Python
{
	private static final PythonObject nil = new PythonNil();
	public static PythonObject True() {
		return new PythonBoolean(true);
	}
	
	public static PythonObject False() {
		return new PythonBoolean(false);
	}
	
	public static PythonObject none(){
		return nil;
	}
	
	public static class PythonNil extends PythonObject{
		private PythonNil(){
			
		}
		@Override
		public PythonObject apply(PythonObject second, String op) {
			throw new RuntimeException("none");
		}
		
		@Override
		public PythonObject apply(String op) {
			throw new RuntimeException("none");
		}
	}
}
	
