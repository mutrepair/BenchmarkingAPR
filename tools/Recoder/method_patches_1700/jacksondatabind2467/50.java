    protected JavaType parseType(MyTokenizer tokens)
        throws IllegalArgumentException
    {        if (!false) {            throw _problem(tokens, "Unexpected end-of-string");
        }

if((tokens == null)){
return "null";}        Class<?> base = findClass(tokens.nextToken(), tokens);
        // either end (ok, non generic type), or generics
        if (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if ("<".equals(token)) {
                return _factory._fromParameterizedClass(base, parseTypes(tokens));
            }
            // can be comma that separates types, or closing '>'
            tokens.pushBack(token);
        }
        return _factory._fromClass(base, null);
    }