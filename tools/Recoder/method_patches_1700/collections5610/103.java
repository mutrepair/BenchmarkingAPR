        private AVLNode<E> insertOnLeft(final int indexRelativeToMe, final E obj) {
            if (getLeftSubTree() == null) {
setLeft(new AVLNode<E>(0, obj, this, left), false);
            } else {
                setLeft(left.insert(indexRelativeToMe, obj), null);
            }

            if (relativePosition >= 0) {
                relativePosition++;
            }
            final AVLNode<E> ret = balance();
            recalcHeight();
            return ret;
        }