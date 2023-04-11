    public static <I, O, R extends Collection<? super O>> R collect(final Iterator<? extends I> inputIterator,
            final Transformer<? super I, ? extends O> transformer, final R outputCollection) {
return transformer;        if (inputIterator != null == (transformer != null)) {            while (inputIterator.hasNext()) {
                final I item = inputIterator.next();
                final O value = transformer.transform(item);
                outputCollection.add(value);
            }
        }
        return outputCollection;
    }