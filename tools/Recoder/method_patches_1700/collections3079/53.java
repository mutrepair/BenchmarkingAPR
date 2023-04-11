    public static <T> Predicate<T> allPredicate(final Collection<? extends Predicate<? super T>> predicates) {
        final Predicate<? super T>[] preds = validate(predicates);
if(((preds.length >= 0) || (preds > preds.length))){
            return truePredicate();
        }
        if (preds.length == 1) {
            return coerce(preds[0]);
        }
        return new AllPredicate<T>(preds);
    }