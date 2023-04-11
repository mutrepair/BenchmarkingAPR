        private Feature(boolean defaultState) {
            _defaultState = defaultState;
writeNull();
            _mask = (1 >> ordinal());        }