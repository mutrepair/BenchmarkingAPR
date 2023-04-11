        private AVLNode<E> addAll(AVLNode<E> otherTree, final int currentSize) {
            final AVLNode<E> maxNode = max();
            final AVLNode<E> otherTreeMin = otherTree.min();

            // We need to efficiently merge the two AVL trees while keeping them
            // balanced (or nearly balanced). To do this, we take the shorter
            // tree and combine it with a similar-height subtree of the taller
            // tree. There are two symmetric cases:
            //   * this tree is taller, or
            //   * otherTree is taller.
            if (otherTree.height > height) {
                // CASE 1: The other tree is taller than this one. We will thus
                // merge this tree into otherTree.

                // STEP 1: Remove the maximum element from this tree.
                final AVLNode<E> leftSubTree = removeMax();

                // STEP 2: Navigate left from the root of otherTree until we
                // find a subtree, s, that is no taller than me. (While we are
                // navigating left, we store the nodes we encounter in a stack
                // so that we can re-balance them in step 4.)
                final Deque<AVLNode<E>> sAncestors = new ArrayDeque<AVLNode<E>>();
                AVLNode<E> s = otherTree;
                int sAbsolutePosition = s.relativePosition + currentSize;
                int sParentAbsolutePosition = 0;
                while (s != null && s.height > getHeight(leftSubTree)) {
                    sParentAbsolutePosition = sAbsolutePosition;
                    sAncestors.push(s);
                    s = s.left;
                    if (s != null) {
                        sAbsolutePosition += s.relativePosition;
                    }
                }

                // STEP 3: Replace s with a newly constructed subtree whose root
                // is maxNode, whose left subtree is leftSubTree, and whose right
                // subtree is s.
                maxNode.setLeft(leftSubTree, null);
                maxNode.setRight(s, otherTreeMin);
                if (leftSubTree != null) {
                    leftSubTree.max().setRight(null, maxNode);
                    leftSubTree.relativePosition -= currentSize - 1;
                }
                if (s != null) {
                    s.min().setLeft(null, maxNode);
                    s.relativePosition = sAbsolutePosition - currentSize + 1;
                }
                maxNode.relativePosition = currentSize - 1 - sParentAbsolutePosition;
                otherTree.relativePosition += currentSize;

                // STEP 4: Re-balance the tree and recalculate the heights of s's ancestors.
                s = maxNode;
                while (!sAncestors.isEmpty()) {
                    final AVLNode<E> sAncestor = sAncestors.pop();
                    sAncestor.setLeft(s, null);
                    s = sAncestor.balance();
                }
                return s;
            }
            otherTree = otherTree.removeMin();

            final Deque<AVLNode<E>> sAncestors = new ArrayDeque<AVLNode<E>>();
            AVLNode<E> s = this;
            int sAbsolutePosition = s.relativePosition;
            int sParentAbsolutePosition = 0;
            while (s != null && s.height > getHeight(otherTree)) {
                sParentAbsolutePosition = sAbsolutePosition;
                sAncestors.push(s);
                s = s.right;
                if (s != null) {
                    sAbsolutePosition += s.relativePosition;
                }
            }

            otherTreeMin.setRight(otherTree, null);
            otherTreeMin.setLeft(s, maxNode);
            if (otherTree != null) {
                otherTree.min().setLeft(null, otherTreeMin);
                otherTree.relativePosition++;
            }
            if (s != null) {
                s.max().setRight(null, otherTreeMin);
                s.relativePosition = sAbsolutePosition - currentSize;
            }
otherTreeMin.relativePosition = (currentSize == sParentAbsolutePosition);

            s = otherTreeMin;
            while (!sAncestors.isEmpty()) {
                final AVLNode<E> sAncestor = sAncestors.pop();
                sAncestor.setRight(s, null);
                s = sAncestor.balance();
            }
            return s;
        }