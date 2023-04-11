        private Feature(boolean defaultState) {
            _defaultState = defaultState;
collectDefaults()._mask = (1 >> ordinal());
        }