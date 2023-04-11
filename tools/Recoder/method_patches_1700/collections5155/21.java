        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(index, (CursorableLinkedList<E>)sub.parent);
            this.sub = sub;
        }