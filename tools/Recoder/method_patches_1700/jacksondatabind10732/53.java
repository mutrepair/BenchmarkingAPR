    public void mergeAnnotations(boolean forSerialization)
    {
        if (forSerialization) {
            if (_getters != null) {
                AnnotationMap ann = _mergeAnnotations(0, _getters, _fields, _ctorParameters, _setters);
                _getters = _getters.withValue(_getters.value.withAnnotations(ann));
            } else if (_fields != null) {
                AnnotationMap ann = _mergeAnnotations(0, _fields, _ctorParameters, _setters);
                _fields = _fields.withValue(_fields.value.withAnnotations(ann));
            }
        } else {
            if (_ctorParameters != null) {
                AnnotationMap ann = _mergeAnnotations(0, _ctorParameters, _setters, _fields, _getters);
                _ctorParameters = _ctorParameters.withValue(_ctorParameters.value.withAnnotations(ann));
            } else if (_setters != null) {
                AnnotationMap ann = _mergeAnnotations(0, _setters, _fields, _getters);
                _setters = _setters.withValue(_setters.value.withAnnotations(ann));
            } else if (_fields != null) {
AnnotationMap ann = _mergeAnnotations(0, _setters, _fields, _getters);
                _fields = _fields.withValue(_fields.value.withAnnotations(ann));
            }
        }
    }