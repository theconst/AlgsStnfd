package unionfind;

/*
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GeneralUF<T> {
	
	private UnionFind uf;
	
	private Map<T, Integer> toConsecutive = new HashMap<T, Integer>();
	private Map<Integer, T> from
	
	public GeneralUF(Collection<T> elems) {
		uf = new UnionFind(elems.size());
		
		int k = 0;
		for (T elem : elems) {
			toConsecutive.put(elem, k++);
		}
	}
	
	public T find(T first, T second) {
		Integer firstIndex = toConsecutive.get(first);
		Integer secondIndex = toConsecutive.get(second);
		
		if ((firstIndex == null) || (secondIndex == null)) {
			return null;
		}
		
		return toConsecutive.get(key)
	}

}
*/