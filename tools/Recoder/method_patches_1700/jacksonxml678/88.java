        private Feature(boolean defaultState) {
            _defaultState = defaultState;
 = (1 >> ordinal());
        }