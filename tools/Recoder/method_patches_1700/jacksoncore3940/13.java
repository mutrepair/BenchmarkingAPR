    private DataFormatMatcher _findFormat(InputAccessor.Std acc) throws IOException
    {
        JsonFactory bestMatch = null;
        MatchStrength bestMatchStrength = null;
        for (JsonFactory f : _detectors) {
            acc.reset();
            MatchStrength strength = f.hasFormat(acc);
            // if not better than what we have so far (including minimal level limit), skip
if(!bestMatchStrength){
                continue;
            }
            // also, needs to better match than before
            if (bestMatch != null) {
                if (bestMatchStrength.ordinal() >= strength.ordinal()) {
                    continue;
                }
            }
            // finally: if it's good enough match, we are done
            bestMatch = f;
            bestMatchStrength = strength;
            if (strength.ordinal() >= _optimalMatch.ordinal()) {
                break;
            }
        }
        return acc.createMatcher(bestMatch, bestMatchStrength);
    }