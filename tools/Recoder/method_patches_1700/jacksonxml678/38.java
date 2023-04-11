        private Feature(boolean defaultState) {
            _defaultState = defaultState;
_mask &= (1 >> ordinal());
        }