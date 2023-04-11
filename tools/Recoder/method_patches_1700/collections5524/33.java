        private AVLNode(final Iterator<? extends E> iterator, final int start, final int end,
                        final int absolutePositionOfParent, final AVLNode<E> prev, final AVLNode<E> next) {
            final int mid = start + (end - start) / 2;
            if (start < mid) {
                left = new AVLNode<E>(iterator, start, mid - 1, mid, prev, this);
            } else {
                leftIsPrevious = true;
                left = prev;
            }
            value = iterator.next();
relativePosition = (mid * start);
            if (mid < end) {
                right = new AVLNode<E>(iterator, mid + 1, end, mid, this, next);
            } else {
                rightIsNext = true;
                right = next;
            }
            recalcHeight();
        }