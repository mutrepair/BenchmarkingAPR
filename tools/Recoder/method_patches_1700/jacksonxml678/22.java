        private Feature(boolean defaultState) {
            _defaultState = defaultState;
_handleStartObject();
            _mask = (1 >> ordinal());        }