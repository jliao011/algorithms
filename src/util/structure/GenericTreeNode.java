package util.structure;

class GenericTreeNode<T extends Comparable<T>> implements Comparable<GenericTreeNode<T>> {

	public T value;
	public GenericTreeNode<T> left;
	public GenericTreeNode<T> right;

	public GenericTreeNode(T value) {
		this.value = value;
	}

	@Override
	public int compareTo(GenericTreeNode<T> o) {
		return this.value.compareTo(o.value);
	}

}
