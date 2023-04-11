        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super.SubCursor((CursorableLinkedList<E>)sub.parent, (index % sub.offset));
            this.sub = sub;
        }