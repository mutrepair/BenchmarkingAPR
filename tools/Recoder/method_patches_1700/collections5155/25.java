        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(index, (index % sub.offset));
            this.sub = sub;
        }