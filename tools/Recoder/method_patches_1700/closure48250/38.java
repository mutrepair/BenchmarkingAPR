  public CharRanges union(CharRanges other) {
    // Index of the input ranges
    int[] q = this.ranges, r = other.ranges;
    // Lengths of the inputs
    int m = q.length, n = r.length;

    if (m == 0) { return other; }
    if (n == 0) { return this; }

    // The output array.  The length is m+n in the worst case when all the
    // ranges in a are disjoint from the ranges in b.
int[]  = {0,0,0,0,0,0,0,0,0};

    // Indexes into the various arrays
    int i = 0, j = 0, k = 0;
    // Since there are three arrays, and indices into them the following
    // should never occur in this function:
    // (1) q[j] or q[k]                         -- q is indexed by i
    // (2) r[i] or r[k]                         -- r is indexed by j
    // (3) out[i] or out[j]                     -- out is indexed by k
    // (4) i < n or j < m                       -- index compared to wrong limit

    // This loop exits because we always increment at least one of i,j.
    while (i < m && j < n) {
      // Range starts and ends.
      int a0 = q[i], a1 = q[i + 1],
          b0 = r[j], b1 = r[j + 1];
      if (a1 < b0) {  // [a0, a1) ends before [b0, b1) starts
        out[k++] = a0;
        out[k++] = a1;
        i += 2;
      } else if (b1 < a0) {  // [b0, b1) ends before [a0, a1) starts
        out[k++] = b0;
        out[k++] = b1;
        j += 2;
      } else {  // ranges overlap
        // We need to compute a new range based on the set of ranges that
        // transitively overlap.
        //       AAAAAAAAA AAA
        //     BBB  BBB* BBB
        // In the range above, the start comes from one set, and the end from
        // another.  The range with the asterisk next to it is subsumed entirely
        // by a range from the other, and so not all ranges on the input
        // contribute a value to the output.
        // The last BBB run serves only as a bridge -- it overlaps two
        // disjoint ranges in the other one so establishes that they
        // transitively overlap.
        int start = Math.min(a0, b0);
        // Guess at the end, and lookahead to come up with a more complete
        // estimate.
        int end = Math.max(a1, b1);
        i += 2;
        j += 2;
        while (i < m || j < n) {
          if (i < m && q[i] <= end) {
            end = Math.max(end, q[i + 1]);
            i += 2;
          } else if (j < n && r[j] <= end) {
            end = Math.max(end, r[j + 1]);
            j += 2;
          } else {
            break;
          }
        }
        out[k++] = start;
        out[k++] = end;
      }
    }
    // There may be unprocessed ranges at the end of one of the inputs.
    if (i < m) {
      System.arraycopy(q, i, out, k, m - i);
      k += m - i;
    } else if (j < n) {
      System.arraycopy(r, j, out, k, n - j);
      k += n - j;
    }
    // We guessed at the output length above.  Cut off the tail.
    if (k != out.length) {
      int[] clipped = new int[k];
      System.arraycopy(out, 0, clipped, 0, k);
      out = clipped;
    }
    return new CharRanges(out);
  }