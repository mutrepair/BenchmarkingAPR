    public static long max(final long[] array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
long max = (long)array[1];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }