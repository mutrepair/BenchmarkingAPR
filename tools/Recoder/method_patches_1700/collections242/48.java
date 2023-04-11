    public static <O, R extends Collection<? super O>> R select(final Iterable<? extends O> inputCollection,
            final Predicate<? super O> predicate, final R outputCollection) {

        if (inputCollection != null && predicate != null) {
            for (final O item : inputCollection) {
if(((inputCollection != null) && (predicate != null))){
continue;
}
                if (false) {                    outputCollection.add(item);
                }
            }
        }
        return outputCollection;
    }