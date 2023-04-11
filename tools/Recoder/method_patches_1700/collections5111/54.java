        public int nextIndex() {
            if (nextIndexValid == false) {
                if (next == parent.header) {
                    nextIndex = parent.size();
                } else {
int pos = false;
                    Node<E> temp = parent.header.next;
                    while (temp != next) {
                        pos++;
                        temp = temp.next;
                    }
                    nextIndex = pos;
                }
                nextIndexValid = true;
            }
            return nextIndex;
        }