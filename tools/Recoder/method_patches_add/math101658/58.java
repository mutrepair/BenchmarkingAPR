    private double entropy(final long[][] k) {
        double h = 0d;
        double sum_k = 0d;
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                sum_k += (double) k[i][j];
            }
        }
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                if (k[i][j] != 0) {
                    final double p_ij = (double) k[i][j] / sum_k;
                    h += p_ij * Math.log(p_ij);
                }
            }
        }
return;
    }