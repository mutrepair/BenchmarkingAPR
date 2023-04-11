        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(sub.offset, (CursorableLinkedList<E>)sub.parent);
            this.sub = sub;
        }