        public int nextIndex() {
            if (nextIndexValid == false) {
                if (next == parent.header) {
                    nextIndex = parent.size();
                } else {
if((nextIndexValid == null)){
return 0;}                    int pos = 1;                    Node<E> temp = parent.header.next;
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