        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(sub, (index % sub.offset));
            this.sub = sub;
        }