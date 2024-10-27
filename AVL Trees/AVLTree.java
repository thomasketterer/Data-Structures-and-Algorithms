package assn06;


public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }


    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateLeft() {
        assn06.AVLTree<T> or = this;
        assn06.AVLTree<T> nr = _right;
        or._right = _right._left;
        cngSH(or);
        nr._left = or;
        cngSH(nr);
        return nr;

    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateRight() {
        assn06.AVLTree<T> or = this;
        assn06.AVLTree<T> nr = _left;
        or._left = _left._right;
        cngSH(or);
        nr._right = or;
        cngSH(nr);
        return nr;

    }




    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public int height() {
        return _height;
    }


    @Override
    public int size() {
        return _size;
    }


    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (this.isEmpty()) {
            _value = element;
            _size = 1;
            _height = 1;
            _left = new assn06.AVLTree<T>();
            _right = new assn06.AVLTree<T>();
        } else {
            int comparison = element.compareTo(_value);
            if((comparison < 0) && (!_left.isEmpty() )){
                _left = (assn06.AVLTree<T>) _left.insert(element);
            } else if (((comparison >= 0)) && (!_right.isEmpty())) {
                _right = (assn06.AVLTree<T>) _right.insert(element);
            } else if ((comparison < 0) && ((_left.isEmpty()))) {
                assn06.AVLTree<T> insertion = new assn06.AVLTree<T>();
                insertion._value = element;
                insertion._height = 1;
                insertion._size = 1;
                insertion._left = new assn06.AVLTree<T>();
                insertion._right = new assn06.AVLTree<T>();
                _left = insertion;
            } else if (((comparison >= 0)) && ((_right.isEmpty()))) {
                assn06.AVLTree<T> insertion = new assn06.AVLTree<T>();
                insertion._value = element;
                insertion._height = 1;
                insertion._size = 1;
                insertion._left = new assn06.AVLTree<T>();
                insertion._right = new assn06.AVLTree<T>();
                _right = insertion;
            }
            cngSH(this);
            assn06.AVLTree<T> getLast;
            getLast = (assn06.AVLTree<T>) balance();
            return getLast;
        }
        return this;

    }

    private SelfBalancingBST<T> balance() {
        int lh = 0;
        int rh = 0;
        if (!_left.isEmpty()) {
            lh = _left.height();
        }
        if (!_right.isEmpty()){
            rh = _right.height();
        }


        if((Math.abs(lh-rh)) <= 1){
            return this;
        }


        assn06.AVLTree<T> lChild = _left;
        assn06.AVLTree<T> rChild = _right;


        int lclheight = 0;
        int lcrheight = 0;
        int rclheight = 0;
        int rcrheight = 0;


        if(!lChild.isEmpty()) {
            if(!lChild._left.isEmpty()){
                lclheight = lChild._left.height();
            }


            if(!lChild._right.isEmpty()){
                lcrheight = lChild._right.height();
            }


        }
        if(!rChild.isEmpty()){


            if(!rChild._left.isEmpty()){
                rclheight = rChild._left.height();
            }
            if(!rChild._right.isEmpty()){
                rcrheight = rChild._right.height();
            }
        }


        assn06.AVLTree<T> rotated = new assn06.AVLTree<T>();
        rotated = this;
        if(lh - rh > 1){
            if((lclheight - lcrheight) >= 0){
                rotated = rotateRight();
            } else {
                if(!_left.isEmpty()) {
                    _left = _left.rotateLeft();
                }
                rotated = rotateRight();
            }




        } else {
            if ((rcrheight - rclheight) >= 0) {
                rotated = rotateLeft();
            } else {
                if (!_right.isEmpty() ) {
                    _right = _right.rotateRight();
                }
                rotated = rotateLeft();
            }
        }
        return rotated;
    }


    @Override
    public SelfBalancingBST<T> remove(T element) {
        if(this.isEmpty()){
            return this;
        }
        int compare = element.compareTo(_value);
        if(compare < 0){
            _left = (assn06.AVLTree<T>) _left.remove(element);
        }
        else if(compare > 0){
            _right = (assn06.AVLTree<T>) _right.remove(element);
        }
        else{
            if(_left.isEmpty()){
                return _right;
            }
            else if(_right.isEmpty()){
                return _left;
            }
            else{
                T min = _right.findMin();
                _value = min;
                _right = (assn06.AVLTree<T>) _right.remove(min);
            }
        }


        cngSH(this);
        assn06.AVLTree<T> balanced = new assn06.AVLTree<T>();
        balanced = (assn06.AVLTree<T>) balance();
        return balanced;
    }


    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_left.isEmpty()) {
            return _value;
        } else {
            return _left.findMin();
        }
    }


    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }


    @Override
    public boolean contains(T element) {
        // TODO
        if(this.isEmpty()){
            return false;
        } else {
            if(element.compareTo(_value) == 0){
                return true;
            } else{
                if((element.compareTo(_value) < 0) && (!_left.isEmpty())){
                    return _left.contains(element);
                } else if ((element.compareTo(_value) > 0) && (!_right.isEmpty())) {
                    return _right.contains(element);
                } else {
                    return false;
                }
            }
        }

    }


    @Override
    public T getValue() {
        return _value;
    }


    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }


    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
        return _right;
    }

    private void cngSH(assn06.AVLTree<T> root) {
        int rrSize = 0;
        int lrSize = 0;
        int rrHeight = 0;
        int lrHeight = 0;


        if (!root._right.isEmpty()) {
            rrSize = root._right.size();
            rrHeight = root._right.height();
        }
        if(!root._left.isEmpty()){
            lrSize = root._left.size();
            lrHeight = root._left.height();
        }


        root._size = 1 + rrSize + lrSize;
        root._height = (Math.max(rrHeight, lrHeight)) + 1;
    }


}
