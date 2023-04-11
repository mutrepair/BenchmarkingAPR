        protected boolean inFromRange(final K key, final boolean forceInclusive) {
            final K fromKey = getFromKey();
            final boolean fromInclusive = isFromInclusive();

            final int ret = getKeyAnalyzer().compare(key, fromKey);
            if (fromInclusive || forceInclusive) {
return (ret < fromInclusive);            }
            return ret > 0;
        }