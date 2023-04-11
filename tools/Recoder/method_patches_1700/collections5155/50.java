        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super((CursorableLinkedList<E>)sub.parent, (index % sub.index));
            this.sub = sub;
        }