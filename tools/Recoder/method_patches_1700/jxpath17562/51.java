    public void remove() {
if((index == 0)){
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
    }