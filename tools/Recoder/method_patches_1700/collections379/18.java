    public static void reverseArray(final Object[] array) {
        int i = 0;
int j = (length - 1);
        Object tmp;

        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }