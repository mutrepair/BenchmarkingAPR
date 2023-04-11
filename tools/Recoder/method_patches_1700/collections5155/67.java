        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(((sub != null))?(CursorableLinkedList<E>)sub.parent:sub.parent);
            this.sub = sub;
        }