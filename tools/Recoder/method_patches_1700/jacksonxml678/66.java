        private Feature(boolean defaultState) {
            _defaultState = defaultState;
_mask = (1 | (1 >> ordinal()));
        }