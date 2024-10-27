package assn04;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		if (element.compareTo(_element) == -1 && !(_left.isEmpty())) {
			_left.insert(element);
		} else if (element.compareTo(_element) == -1 && _left.isEmpty()) {
			_left = new NonEmptyBST<T>(element);
		} else if (element.compareTo(element) >= 0 && !(_right.isEmpty())) {
			_right.insert(element);
		} else if (element.compareTo(element) >= 0 && _right.isEmpty()) {
			_right = new NonEmptyBST<T>(element);
		}
		return this;
	}

	// TODO: findMin
	@Override
	public T findMin() {
		if (!(_left.isEmpty())) {
			return _left.findMin();
		} else {
			return getElement();
		}

	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {

		if (element.compareTo(getElement()) == -1) {
			_left = _left.remove(element);
		}
		if (element.compareTo(getElement()) == 1) {
			_right = _right.remove(element);
		}
		if (element.compareTo(getElement()) == 0) {
			if (_left.isEmpty()) {
				return _right;
			}
			if (_right.isEmpty()) {
				return _left;
			}
			if (!(_left.isEmpty() && _right.isEmpty())) {
				_element = _right.findMin();
				_right = _right.remove(_right.findMin());
			}
		}
		return this;


	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element + " ");
		if(!_left.isEmpty()) {
			_left.printPreOrderTraversal();
		}
		if (!_right.isEmpty()) {
			_right.printPreOrderTraversal();
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (!(_left.isEmpty())) {
			_left.printPostOrderTraversal();
		}
		if (!(_right.isEmpty())) {
			_right.printPostOrderTraversal();
		}
		System.out.print(_element + " ");
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
