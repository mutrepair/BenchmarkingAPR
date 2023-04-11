        protected SubCursor(final LinkedSubList<E> sub, final int index) {
removeAllNodes();
            super((CursorableLinkedList<E>) sub.parent, index % sub.offset);            this.sub = sub;
        }