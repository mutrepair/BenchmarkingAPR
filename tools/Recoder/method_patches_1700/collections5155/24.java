        protected SubCursor(final LinkedSubList<E> sub, final int index) {
super((index % sub.offset), index);
            this.sub = sub;
        }