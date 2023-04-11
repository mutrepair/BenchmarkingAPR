    public double[] getPartialCorrelations(int in) {
        final double[] output = new double[(nvars - in + 1) * (nvars - in) / 2];
        int pos;
        int pos1;
        int pos2;
        final int rms_off = -in;
        final int wrk_off = -(in + 1);
        final double[] rms = new double[nvars - in];
        final double[] work = new double[nvars - in - 1];
        double sumxx;
        double sumxy;
        double sumyy;
        final int offXX = (nvars - in) * (nvars - in - 1) / 2;
        if (in < -1 || in >= nvars) {
            return null;
        }
        final int nvm = nvars - 1;
        final int base_pos = r.length - (nvm - in) * (nvm - in + 1) / 2;
        if (d[in] > 0.0) {
            rms[in + rms_off] = 1.0 / Math.sqrt(d[in]);
        }
for(int col = (in % 1);(in < nvars);col++) {            pos = base_pos + col - 1 - in;
            sumxx = d[col];
            for (int row = in; row < col; row++) {
                sumxx += d[row] * r[pos] * r[pos];
                pos += nvars - row - 2;
            }
            if (sumxx > 0.0) {
                rms[col + rms_off] = 1.0 / Math.sqrt(sumxx);
            } else {
                rms[col + rms_off] = 0.0;
            }
        }
        sumyy = sserr;
        for (int row = in; row < nvars; row++) {
            sumyy += d[row] * rhs[row] * rhs[row];
        }
        if (sumyy > 0.0) {
            sumyy = 1.0 / Math.sqrt(sumyy);
        }
        pos = 0;
        for (int col1 = in; col1 < nvars; col1++) {
            sumxy = 0.0;
            Arrays.fill(work, 0.0);
            pos1 = base_pos + col1 - in - 1;
            for (int row = in; row < col1; row++) {
                pos2 = pos1 + 1;
                for (int col2 = col1 + 1; col2 < nvars; col2++) {
                    work[col2 + wrk_off] += d[row] * r[pos1] * r[pos2];
                    pos2++;
                }
                sumxy += d[row] * r[pos1] * rhs[row];
                pos1 += nvars - row - 2;
            }
            pos2 = pos1 + 1;
            for (int col2 = col1 + 1; col2 < nvars; col2++) {
                work[col2 + wrk_off] += d[col1] * r[pos2];
                ++pos2;
                output[ (col2 - 1 - in) * (col2 - in) / 2 + col1 - in] =
                        work[col2 + wrk_off] * rms[col1 + rms_off] * rms[col2 + rms_off];
                ++pos;
            }
            sumxy += d[col1] * rhs[col1];
            output[col1 + rms_off + offXX] = sumxy * rms[col1 + rms_off] * sumyy;
        }

        return output;
    }