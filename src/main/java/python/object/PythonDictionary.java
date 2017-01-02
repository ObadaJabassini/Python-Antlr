package python.object;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PythonDictionary extends PythonTraverse{
	private Map<String, PythonObject> objects;
	
	public PythonDictionary(){
		this.objects = new LinkedHashMap<>();
	}
	
	public PythonDictionary(Map<String, PythonObject> map){
		this.objects = map;
	}
	
	public void set(String name, PythonObject object){
		this.objects.put(name, object);
	}
	
	public PythonObject get(String name){
		return objects.get(name);
	}
	
	@Override
	public PythonIterator iterator() {
		return new PythonDictionaryIterator(objects);
	}
	
	public static class PythonDictionaryIterator extends PythonIterator{
		private List<String> keys;
		private List<PythonObject> objects;
		private int i = 0;
		
		public PythonDictionaryIterator(List<String> keys, List<PythonObject> objects) {
			this.keys = keys;
			this.objects = objects;
		}
		
		public PythonDictionaryIterator(Map<String, PythonObject> objectMap){
			this.keys = new ArrayList<>();
			this.objects = new ArrayList<>();
			objectMap.forEach((s, object) -> {
				keys.add(s);
				objects.add(object);
			});
		}
		
		@Override
		public boolean hasNext() {
			return i < keys.size();
		}
		
		@Override
		public PythonObject next() {
			List<PythonObject> os = new ArrayList<>();
			os.add(new PythonString(keys.get(i)));
			os.add(objects.get(i++));
			return new PythonTuple(os);
		}
	}
}