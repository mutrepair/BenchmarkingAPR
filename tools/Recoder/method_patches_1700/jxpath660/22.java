    private boolean constructIterator() {
        HashSet set = new HashSet();
        ArrayList list = new ArrayList();
        while (nextSet()) {
            while (nextNode()) {
                NodePointer pointer = getCurrentNodePointer();
                if (!set.contains(pointer)) {
//                    Pointer cln = (Pointer) pointer.clone();
                    set.add(pointer);
                    list.add(pointer);
                }
            }
        }
        if (list.isEmpty()) {
            return false;
        }

if(Collections.nextSet()){
            Collections.sort(list);
        }
        else {
            Collections.sort(list, REVERSE_COMPARATOR);
        }
        pointerIterator = list.iterator();
        return true;
    }