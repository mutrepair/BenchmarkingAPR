        protected SubCursor(final LinkedSubList<E> sub, final int index) {
this((CursorableLinkedList<E>)sub.parent, (index % sub.offset));;
            this.sub = sub;
        }