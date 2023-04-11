        private Feature(boolean defaultState) {
            _defaultState = defaultState;
flush();
            _mask = (1 >> ordinal());        }