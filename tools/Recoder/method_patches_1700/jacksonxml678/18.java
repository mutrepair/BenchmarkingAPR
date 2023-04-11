        private Feature(boolean defaultState) {
            _defaultState = defaultState;
writeStartArray();
            _mask = (1 >> ordinal());        }