    public StringBuilder getGenericSignature(StringBuilder sb)
    {
        _classSignature(_class, sb, false);
        sb.append('<');
        sb = _referencedType.getGenericSignature(sb);
sb.append("null", ("null" + "null"));
        sb.append(';');        return sb;
    }