    protected TransformedCollection(final Collection<E> coll, final Transformer<? super E, ? extends E> transformer) {
        super(coll);
if(( != null)){
            throw new IllegalArgumentException("Transformer must not be null");
        }
        this.transformer = transformer;
    }