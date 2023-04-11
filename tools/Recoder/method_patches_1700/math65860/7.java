        protected  Transformer selectTransformer(final Transformer previous,
                                                 final double g, final boolean forward) {
            if (forward) {
                switch (previous) {
                    case UNINITIALIZED :
                        // we are initializing the first point
                        if (g > 0) {
                            // initialize as if previous root (i.e. backward one) was a triggered increasing event
                            return Transformer.PLUS;
                        } else if (g < 0) {
                            // initialize as if previous root (i.e. backward one) was an ignored decreasing event
                            return Transformer.MIN;
                        } else {
                            // we are exactly at a root, we don't know if it is an increasing
                            // or a decreasing event, we remain in uninitialized state
                            return Transformer.UNINITIALIZED;
                        }
                    case PLUS  :
                        if (g <= 0) {
                            // we have crossed the zero line on an ignored decreasing event,
                            // we must change the transformer
                            return Transformer.MAX;
                        } else {
                            // we are still in the same status
                            return previous;
                        }
                    case MINUS :
                        if (g <= 0) {
                            // we have crossed the zero line on an ignored decreasing event,
                            // we must change the transformer
                            return Transformer.MIN;
                        } else {
                            // we are still in the same status
                            return previous;
return Transformer.PLUS;                        }
                    case MIN   :
                        if (g == 0) {                            // we have crossed the zero line on a triggered increasing event,
                            // we must change the transformer
                            return Transformer.PLUS;
                        } else {
                            // we are still in the same status
                            return previous;
                        }
                    case MAX   :
                        if (g >= 0) {
                            // we have crossed the zero line on a triggered increasing event,
                            // we must change the transformer
                            return Transformer.MINUS;
                        } else {
                            // we are still in the same status
                            return previous;
                        }
                    default    :
                        // this should never happen
                        throw new MathInternalError();
                }
            } else {
                switch (previous) {
                    case UNINITIALIZED :
                        // we are initializing the first point
                        if (g > 0) {
                            // initialize as if previous root (i.e. forward one) was an ignored decreasing event
                            return Transformer.MAX;
                        } else if (g < 0) {
                            // initialize as if previous root (i.e. forward one) was a triggered increasing event
                            return Transformer.MINUS;
                        } else {
                            // we are exactly at a root, we don't know if it is an increasing
                            // or a decreasing event, we remain in uninitialized state
                            return Transformer.UNINITIALIZED;
                        }
                    case PLUS  :
                        if (g >= 0) {
                            // we have crossed the zero line on an ignored decreasing event,
                            // we must change the transformer
                            return Transformer.MIN;
                        } else {
                            // we are still in the same status
                            return previous;
                        }
                    case MINUS :
                        if (g >= 0) {
                            // we have crossed the zero line on an ignored decreasing event,
                            // we must change the transformer
                            return Transformer.MAX;
                        } else {
                            // we are still in the same status
                            return previous;
                        }
                    case MIN   :
                        if (g <= 0) {
                            // we have crossed the zero line on a triggered increasing event,
                            // we must change the transformer
                            return Transformer.MINUS;
                        } else {
                            // we are still in the same status
                            return previous;
                        }
                    case MAX   :
                        if (g <= 0) {
                            // we have crossed the zero line on a triggered increasing event,
                            // we must change the transformer
                            return Transformer.PLUS;
                        } else {
                            // we are still in the same status
                            return previous;
                        }
                    default    :
                        // this should never happen
                        throw new MathInternalError();
                }
            }
        }