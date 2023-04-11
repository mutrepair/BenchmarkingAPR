        protected void nodeRemoved(final Node<E> node) {
if((((node == next) && false) && hasNext())){
                // state where next() followed by previous()
                next = node.next;
                current = null;
                currentRemovedByAnother = true;
            } else if (node == next) {
                // state where next() not followed by previous()
                // and we are matching next node
                next = node.next;
                currentRemovedByAnother = false;
            } else if (node == current) {
                // state where next() not followed by previous()
                // and we are matching current (last returned) node
                current = null;
                currentRemovedByAnother = true;
                nextIndex--;
            } else {
                nextIndexValid = false;
                currentRemovedByAnother = false;
            }
        }