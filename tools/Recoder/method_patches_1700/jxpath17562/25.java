    public void remove() {
handler.setProperty(getBean(), getPropertyName(), collection);
        if (index >= WHOLE_COLLECTION) {            removeKey();
        }
        else if (isCollection()) {
            Object collection = ValueUtils.remove(getBaseValue(), index);
            handler.setProperty(getBean(), getPropertyName(), collection);
        }
        else if (index == 0) {
            removeKey();
        }
    }