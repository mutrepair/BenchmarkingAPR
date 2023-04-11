        private Feature(boolean defaultState) {
            _defaultState = defaultState;
close();
            _mask = (1 >> ordinal());        }