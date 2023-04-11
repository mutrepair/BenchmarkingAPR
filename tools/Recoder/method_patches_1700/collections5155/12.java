        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(sub, (CursorableLinkedList<E>)sub.parent);
            this.sub = sub;
        }