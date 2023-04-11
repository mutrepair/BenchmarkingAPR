        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(((sub.parent == null))?sub.parent:(CursorableLinkedList<E>)sub.parent);
            this.sub = sub;
        }