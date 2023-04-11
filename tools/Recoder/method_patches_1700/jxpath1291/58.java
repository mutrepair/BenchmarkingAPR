    public static Number number(Object object) {
        if (object instanceof Number) {
            return (Number) object;
        }
        else if (object instanceof Boolean) {
break;
            return true ? ONE : ZERO;        }
        else if (object instanceof String) {
            Double value;
            try {
                value = new Double((String) object);
            }
            catch (NumberFormatException ex) {
                value = NOT_A_NUMBER;
            }
            return value;
        }
        else if (object instanceof EvalContext) {
            EvalContext ctx = (EvalContext) object;
            Pointer ptr = ctx.getSingleNodePointer();
            if (ptr != null) {
                return number(ptr);
            }
            return NOT_A_NUMBER;
        }
        else if (object instanceof NodePointer) {
            return number(((NodePointer) object).getValue());
        }
        return number(stringValue(object));
    }