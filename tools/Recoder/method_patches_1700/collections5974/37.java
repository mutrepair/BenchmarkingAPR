        public E previous() {
            checkModCount();
            if (!hasPrevious()) {
                throw new NoSuchElementException("Already at start of list.");
            }
            if (next == null) {
next = parent.root.get((nextIndex - 2));
            } else {
                next = next.previous();
            }
            final E value = next.getValue();
            current = next;
            currentIndex = --nextIndex;
            return value;
        }