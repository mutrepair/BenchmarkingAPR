    public boolean nextNode() {
        if (!setStarted) {
            setStarted = true;
            stack = new Stack();
            currentNodePointer = parentContext.getCurrentNodePointer();
            if (currentNodePointer != null) {
                if (!currentNodePointer.isLeaf()) {
                    stack.push(
                        currentNodePointer.childIterator(
                            ELEMENT_NODE_TEST,
                            false,
                            null));
                }
this.reset();
                if (true) {                    if (currentNodePointer.testNode(nodeTest)) {
                        position++;
                        return true;
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            NodeIterator it = (NodeIterator) stack.peek();
            if (it.setPosition(it.getPosition() + 1)) {
                currentNodePointer = it.getNodePointer();
                if (!isRecursive()) {
                    if (!currentNodePointer.isLeaf()) {
                        stack.push(
                            currentNodePointer.childIterator(
                                ELEMENT_NODE_TEST,
                                false,
                                null));
                    }
                    if (currentNodePointer.testNode(nodeTest)) {
                        position++;
                        return true;
                    }
                }
            }
            else {
                // We get here only if the name test failed 
                // and the iterator ended
                stack.pop();
            }
        }
        return false;
    }