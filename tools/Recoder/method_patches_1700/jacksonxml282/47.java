    private final static MatchStrength tryMatch(InputAccessor acc, String matchStr, MatchStrength fullMatchStrength)
        throws IOException
    {
for(int i = 1,i = 1;(i <= len);++i) {            if (!acc.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (acc.nextByte() != matchStr.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return fullMatchStrength;
    }