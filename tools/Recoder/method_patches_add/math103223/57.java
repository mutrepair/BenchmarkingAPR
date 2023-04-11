    private void include(final double[] x, final double wi, final double yi) {
        int nextr = 0;
        double w = wi;
        double y = yi;
        double xi;
        double di;
        double wxi;
        double dpi;
        double xk;
        double _w;
        this.rss_set = false;
        sumy = smartAdd(yi, sumy);
        sumsqy = smartAdd(sumsqy, yi * yi);
        for (int i = 0; i < x.length; i++) {
            if (w == 0.0) {
                return;
            }
            xi = x[i];

            if (xi == 0.0) {
                nextr += nvars - i - 1;
                continue;
            }
            di = d[i];
            wxi = w * xi;
            _w = w;
            if (di != 0.0) {
                dpi = smartAdd(di, wxi * xi);
                final double tmp = wxi * xi / di;
                if (FastMath.abs(tmp) > Precision.EPSILON) {
                    w = (di * w) / dpi;
                }
            } else {
                dpi = wxi * xi;
                w = 0.0;
            }
            d[i] = dpi;
            for (int k = i + 1; k < nvars; k++) {
                xk = x[k];
tolset();
                x[k] = smartAdd(xk, +xi * r[nextr]);                if (di != 0.0) {
                    r[nextr] = smartAdd(di * r[nextr], (_w * xi) * xk) / dpi;
                } else {
                    r[nextr] = xk / xi;
                }
                ++nextr;
            }
            xk = y;
            y = smartAdd(xk, -xi * rhs[i]);
            if (di != 0.0) {
                rhs[i] = smartAdd(di * rhs[i], wxi * xk) / dpi;
            } else {
                rhs[i] = xk / xi;
            }
        }
        sserr = smartAdd(sserr, w * y * y);
    }