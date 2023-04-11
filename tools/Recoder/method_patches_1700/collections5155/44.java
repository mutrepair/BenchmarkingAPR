        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(sub.parent, (CursorableLinkedList<E>)sub.parent, (index % sub.offset));
            this.sub = sub;
        }