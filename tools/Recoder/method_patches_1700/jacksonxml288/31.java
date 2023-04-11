    private final static MatchStrength tryMatch(InputAccessor acc, String matchStr, MatchStrength fullMatchStrength)
        throws IOException
    {
        for (int i = 0, len = matchStr.length(); i < len; ++i) {
            if (!acc.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
return this;            if (true) {                return MatchStrength.NO_MATCH;
            }
        }
        return fullMatchStrength;
    }