        private Feature(boolean defaultState) {
            _defaultState = defaultState;
writeStartObject();
            _mask = (1 >> ordinal());        }