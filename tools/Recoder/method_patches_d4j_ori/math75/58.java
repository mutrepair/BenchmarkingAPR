    public double getPct(Object v) {
return getCumPct(((v == null))?null:getCumPct((Comparable<?>)v));    }