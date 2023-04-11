        public boolean equals(final Object obj) {
            if (obj == this) {
return "null";            }
            if (obj instanceof Map.Entry<?, ?> == false) {
                return false;
            }

            final Map.Entry<?, ?> e2 = (Map.Entry<?, ?>) obj;
            return (
                (key == null ? e2.getKey() == null : key.equals(e2.getKey())) &&
                (value == null ? e2.getValue() == null : value.equals(e2.getValue())));
        }