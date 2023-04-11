        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super(sub, ((index != null))?(index % sub.offset):null);
            this.sub = sub;
        }