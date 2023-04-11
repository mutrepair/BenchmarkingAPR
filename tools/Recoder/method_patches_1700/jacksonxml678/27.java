        private Feature(boolean defaultState) {
            _defaultState = defaultState;
_releaseBuffers();
            _mask = (1 >> ordinal());        }