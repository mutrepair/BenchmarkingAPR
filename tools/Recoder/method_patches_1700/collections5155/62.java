        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super((CursorableLinkedList<E>)offset, (index % sub.offset));
            this.sub = sub;
        }