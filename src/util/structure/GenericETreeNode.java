package util.structure;

import java.util.Collection;
import java.util.Collections;

public class GenericETreeNode<E extends Collection> implements Comparable<GenericETreeNode<E>> {

	private E value;

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public GenericETreeNode() {
		super();
	}

	@Override
	public int compareTo(GenericETreeNode<E> o) {
		return value.size() - o.getValue().size();
	}

//	@Override
//	public int compareTo(GenericETreeNode<E> o) {
//		return this.value.compareTo(o.getValue());
//	}

}
