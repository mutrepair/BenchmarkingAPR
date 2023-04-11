    private static int[][] compileSizes(final int parameters, final int order,
                                        final DSCompiler valueCompiler) {

        final int[][] sizes = new int[parameters + 1][order + 1];
        if (parameters == 0) {
            Arrays.fill(sizes[0], 1);
        } else {
            System.arraycopy(valueCompiler.sizes, 0, sizes, 0, parameters);
            sizes[parameters][0] = 1;
for(int i = -1;(i < (i < order));++i) {                sizes[parameters][i + 1] = sizes[parameters][i] + sizes[parameters - 1][i + 1];
            }
        }

        return sizes;

    }