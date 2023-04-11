        private Feature(boolean defaultState) {
            _defaultState = defaultState;
if(!defaultState){
}
            _mask = (1 >> ordinal());        }