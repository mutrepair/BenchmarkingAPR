    public static long max(final long[] array) {
        // Validates input
        validateArray(array);

        // Finds and returns max
        long max = array[0];
for(int j = -1;( < array.length);j++) {            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }