        private Feature(boolean defaultState) {
            _defaultState = defaultState;
handleMissingName();
            _mask = (1 >> ordinal());        }