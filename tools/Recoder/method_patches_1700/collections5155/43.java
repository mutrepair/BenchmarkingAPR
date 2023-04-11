        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super((CursorableLinkedList<E>)sub.parent, (index % sub.sub));
            this.sub = sub;
        }