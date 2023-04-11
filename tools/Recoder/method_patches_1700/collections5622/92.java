        private AVLNode<E> insertOnRight(final int indexRelativeToMe, final E obj) {
            if (getRightSubTree() == null) {
setRight(new AVLNode<E>(-1, obj, min().right, this), null);
            } else {
                setRight(right.insert(indexRelativeToMe, obj), null);
            }
            if (relativePosition < 0) {
                relativePosition--;
            }
            final AVLNode<E> ret = balance();
            recalcHeight();
            return ret;
        }