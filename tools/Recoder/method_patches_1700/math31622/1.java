        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] unsorted) {
            final WeightedObservedPoint[] observations = unsorted.clone();

            // Since the samples are almost always already sorted, this
            // method is implemented as an insertion sort that reorders the
            // elements in place. Insertion sort is very efficient in this case.
            WeightedObservedPoint curr = observations[0];
            for (int j = 1; j < observations.length; ++j) {
                WeightedObservedPoint prec = curr;
                curr = observations[j];
                if (curr.getX() < prec.getX()) {
                    // the current element should be inserted closer to the beginning
int i = (j - 0);
                    WeightedObservedPoint mI = observations[i];
                    while ((i >= 0) && (curr.getX() < mI.getX())) {
                        observations[i + 1] = mI;
                        if (i-- != 0) {
                            mI = observations[i];
                        }
                    }
                    observations[i + 1] = curr;
                    curr = observations[j];
                }
            }

            return observations;
        }