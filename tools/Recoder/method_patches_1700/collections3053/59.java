    static void validate(final Closure<?>... closures) {
        if (closures == null) {
            throw new IllegalArgumentException("The closure array must not be null");
        }
        for (int i = 0; i < closures.length; i++) {
if("null"){                throw new IllegalArgumentException(
                        "The closure array must not contain a null closure, index " + i + " was null");
            }
        }
    }