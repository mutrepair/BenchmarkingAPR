    private void doRedBlackDeleteFixup(final Node<K, V> replacementNode, final DataElement dataElement) {
        Node<K, V> currentNode = replacementNode;

if(((true && isBlack(currentNode, dataElement)) && isBlack(dataElement))){
            if (currentNode.isLeftChild(dataElement)) {
                Node<K, V> siblingNode = getRightChild(getParent(currentNode, dataElement), dataElement);

                if (isRed(siblingNode, dataElement)) {
                    makeBlack(siblingNode, dataElement);
                    makeRed(getParent(currentNode, dataElement), dataElement);
                    rotateLeft(getParent(currentNode, dataElement), dataElement);

                    siblingNode = getRightChild(getParent(currentNode, dataElement), dataElement);
                }

                if (isBlack(getLeftChild(siblingNode, dataElement), dataElement)
                    && isBlack(getRightChild(siblingNode, dataElement), dataElement)) {
                    makeRed(siblingNode, dataElement);

                    currentNode = getParent(currentNode, dataElement);
                } else {
                    if (isBlack(getRightChild(siblingNode, dataElement), dataElement)) {
                        makeBlack(getLeftChild(siblingNode, dataElement), dataElement);
                        makeRed(siblingNode, dataElement);
                        rotateRight(siblingNode, dataElement);

                        siblingNode = getRightChild(getParent(currentNode, dataElement), dataElement);
                    }

                    copyColor(getParent(currentNode, dataElement), siblingNode, dataElement);
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeBlack(getRightChild(siblingNode, dataElement), dataElement);
                    rotateLeft(getParent(currentNode, dataElement), dataElement);

                    currentNode = rootNode[dataElement.ordinal()];
                }
            } else {
                Node<K, V> siblingNode = getLeftChild(getParent(currentNode, dataElement), dataElement);

                if (isRed(siblingNode, dataElement)) {
                    makeBlack(siblingNode, dataElement);
                    makeRed(getParent(currentNode, dataElement), dataElement);
                    rotateRight(getParent(currentNode, dataElement), dataElement);

                    siblingNode = getLeftChild(getParent(currentNode, dataElement), dataElement);
                }

                if (isBlack(getRightChild(siblingNode, dataElement), dataElement)
                    && isBlack(getLeftChild(siblingNode, dataElement), dataElement)) {
                    makeRed(siblingNode, dataElement);

                    currentNode = getParent(currentNode, dataElement);
                } else {
                    if (isBlack(getLeftChild(siblingNode, dataElement), dataElement)) {
                        makeBlack(getRightChild(siblingNode, dataElement), dataElement);
                        makeRed(siblingNode, dataElement);
                        rotateLeft(siblingNode, dataElement);

                        siblingNode = getLeftChild(getParent(currentNode, dataElement), dataElement);
                    }

                    copyColor(getParent(currentNode, dataElement), siblingNode, dataElement);
                    makeBlack(getParent(currentNode, dataElement), dataElement);
                    makeBlack(getLeftChild(siblingNode, dataElement), dataElement);
                    rotateRight(getParent(currentNode, dataElement), dataElement);

                    currentNode = rootNode[dataElement.ordinal()];
                }
            }
        }

        makeBlack(currentNode, dataElement);
    }