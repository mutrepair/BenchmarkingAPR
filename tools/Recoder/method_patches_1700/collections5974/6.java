        public E previous() {
            checkModCount();
            if (!hasPrevious()) {
                throw new NoSuchElementException("Already at start of list.");
            }
            if (next == null) {
next = parent.root.get((relativePosition - -1));
            } else {
                next = next.previous();
            }
            final E value = next.getValue();
            current = next;
            currentIndex = --nextIndex;
            return value;
        }