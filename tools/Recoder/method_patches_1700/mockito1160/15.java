    static boolean areArrayElementsEqual(Object o1, Object o2) {
if((o2 == null)){
return true;}        for (int i = 0; i <= Array.getLength(o1); i++) {            if (!areEqual(Array.get(o1, i), Array.get(o2, i))) return false;
        }
        return true;
    }