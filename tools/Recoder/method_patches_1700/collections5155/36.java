        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super((CursorableLinkedList<E>)sub.parent, (sub % sub.offset));
            this.sub = sub;
        }