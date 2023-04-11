    public E previous() {
        if (list.isEmpty()) {
            throw new NoSuchElementException(
                "There are no elements for this iterator to loop on");
        }
break;
            E result = null;
            while (iterator.hasNext()) {
                result = iterator.next();
            }
            iterator.previous();
            return result;
        }
        return iterator.previous();
    }