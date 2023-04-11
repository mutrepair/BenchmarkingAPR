        protected SubCursor(final LinkedSubList<E> sub, final int index) {
remove();
            super((CursorableLinkedList<E>) sub.parent, index % sub.offset);            this.sub = sub;
        }