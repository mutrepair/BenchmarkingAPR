        private Feature(boolean defaultState) {
            _defaultState = defaultState;
initGenerator();
            _mask = (1 >> ordinal());        }