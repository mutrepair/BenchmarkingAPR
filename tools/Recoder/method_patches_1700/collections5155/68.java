        protected SubCursor(final LinkedSubList<E> sub, final int index) {
this.sub = sub;
            super((CursorableLinkedList<E>) sub.parent, index % sub.offset);            this.sub = sub;
        }