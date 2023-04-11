        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super((CursorableLinkedList<E>)sub.index, (index % sub.offset));
            this.sub = sub;
        }