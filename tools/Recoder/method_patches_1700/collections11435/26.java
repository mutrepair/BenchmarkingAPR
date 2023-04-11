        protected boolean inFromRange(final K key, final boolean forceInclusive) {
            final K fromKey = getFromKey();
            final boolean fromInclusive = isFromInclusive();

            final int ret = getKeyAnalyzer().compare(key, fromKey);
            if (fromInclusive || forceInclusive) {
return ret;            }
            return ret > 0;
        }