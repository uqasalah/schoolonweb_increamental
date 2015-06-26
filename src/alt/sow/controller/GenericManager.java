package alt.sow.controller;

import java.util.List;

import alt.sow.util.GenericDAO;

public class GenericManager<T> extends GenericDAO<T, Integer> {
	public boolean add(T t) {
		// return (save(t) != null);
		return (merge(t) != null);
	}

	public boolean edit(T t) {
		return merge(t) != null;
	}

	public List<T> listAll(Class clazz) {
		return (List<T>) findAll(clazz);
	}

	public boolean remove(T t) {
		return delete(t) != null;
	}
}
